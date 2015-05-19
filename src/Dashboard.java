
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Steffen
 */
public class Dashboard {
    
    private JFrame frame;
    
    private JPanel panel1,panel2;
    
    private JLabel label1,label2;
    
    private JButton but1,but2;
    
    private JList list1;
    
    private JScrollPane pane1;
    
    private String username;
    
    private DefaultListModel model1;
    
    private JMenuBar menuBar;
    
    private JMenu menu, submenu;
    
    private JMenuItem menuItem;
    
    
    //List<Project> projects = new ArrayList<Project>();
    List<Employee> employees = new ArrayList<Employee>();

    public Dashboard(String username) {
        
                this.username = username;
                frame = new JFrame("KXT - Projektstyring");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1024, 768);
                frame.add(mainMenu(),BorderLayout.NORTH);
                frame.add(leftPanel(),BorderLayout.WEST);
                frame.add(rightPanel(),BorderLayout.EAST);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

    }
    
    


    private JPanel rightPanel() {
        
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        
        TitledBorder medarbejdere = BorderFactory.createTitledBorder("Medarbejdere");
        medarbejdere.setTitlePosition(TitledBorder.BELOW_TOP);
        medarbejdere.setTitleJustification(TitledBorder.CENTER);
        panel1.setBorder(medarbejdere);
        
        
        panel1.add(jList());
        return panel1;
    }
    
    
    private JPanel leftPanel() {
        
        
        
        JSONObject employees = Database.query("SELECT * FROM `projektstyring_projects` WHERE 1");
        //Reads the results from the query
	JSONArray tmp = employees.getJSONArray("results");
        
        int N = tmp.length();
        
        TitledBorder projects = BorderFactory.createTitledBorder("Projekter");
        projects.setTitlePosition(TitledBorder.BELOW_TOP);
        projects.setTitleJustification(TitledBorder.CENTER);;
        
        panel2 = new JPanel(new GridLayout(N,N));
        panel2.setPreferredSize(new Dimension(800, 4*100));
        
        
        
    for (int row = 0; row < N; row++){
        for (int col = 0; col < 3; col++){
            
            JButton b = new JButton (tmp.getJSONObject(0).getString("name"));
            b.setMargin(new Insets(0, 0, 0, 0));
            b.setBorder(null);
            //b.setPreferredSize(new Dimension(40, 40));
            panel2.add(b).setLocation(row, col);
        }
    }
        panel2.setBorder(projects);
        
        
        return panel2;
    }
    
    
    private JMenuBar mainMenu()
    {
        //Create the menu bar.
        menuBar = new JMenuBar();
        //Build the first menu.
menu = new JMenu(username);
menu.setMnemonic(KeyEvent.VK_A);
menu.getAccessibleContext().setAccessibleDescription(
        "The only menu in this program that has menu items");
menuBar.add(menu);

//a group of JMenuItems
menuItem = new JMenuItem("A text-only menu item",
                         KeyEvent.VK_T);
menuItem.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_1, ActionEvent.ALT_MASK));
menuItem.getAccessibleContext().setAccessibleDescription(
        "This doesn't really do anything");
menu.add(menuItem);
        return menuBar;
        
    }
    
    
    private JScrollPane jList()
    {
        list1 = new JList();
        model1 = new DefaultListModel();
        getEmployees();
        list1.setFixedCellWidth(150);
        list1.setModel(model1);
        pane1 = new JScrollPane(list1);
        
        
        return pane1;
        
    }
    
    
    
    
    
    private void getEmployees()
    {
        
        JSONObject employees = Database.query("SELECT * FROM `projektstyring_users` WHERE 1");
        //Reads the results from the query
	JSONArray tmp = employees.getJSONArray("results");
        		for(int i = 0; i < tmp.length(); i++){
			JSONObject obj = tmp.getJSONObject(i);
                        model1.addElement(obj.getString("username") /*+ "(" + getInitials(obj.getString("firstname"),obj.getString("lastname")) + ")"*/);
                        
		}
                        
                        //System.out.println(list);
        
        
    }
    
    private String getInitials(String firstname, String lastname)
    {
        String fullname = firstname + " " + lastname;
        Pattern p = Pattern.compile("((^| )[A-Za-z])");
        Matcher m = p.matcher(fullname);
        String inititals="";
        while(m.find()){
            inititals+=m.group().trim();
        }
        return inititals.toUpperCase();
    }
    
}
