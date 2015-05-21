import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Steffen
 */
public class Dashboard {

    private JFrame frame;

    private JPanel mainpanel, panel1, panel2, panel3;

    private JLabel label1, label2;

    private JButton but1, but2;

    private JList list1;

    private JScrollPane pane1, pane2;

    private String username;

    private DefaultListModel model1;

    private DefaultTableModel model2;

    private JMenuBar menuBar;

    private JMenu menu, projectMenu, opgaveMenu, submenu;

    private JMenuItem UserMenuItem, ProjectMenuItem1,ProjectMenuItem2,ProjectMenuItem3, opgMenuItem1,opgMenuItem2,opgMenuItem3;

    private JTable mainTable;


    List<Project> projects = new ArrayList<Project>();
    List<Employee> employees = new ArrayList<Employee>();

    public Dashboard(String username) {

        this.username = username;
        frame = new JFrame("KXT - Projektstyring");
        frame.setIconImage(new ImageIcon(getClass().getResource("/icon_kxt.png")).getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.add(mainMenu(), BorderLayout.NORTH);
        frame.add(leftPanel(), BorderLayout.WEST);
        frame.add(rightPanel(), BorderLayout.EAST);
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
        projects.setTitleJustification(TitledBorder.CENTER);
        ;

        panel2 = new JPanel(new GridLayout(N, N));
        panel2.setPreferredSize(new Dimension(845, 4 * 100));

        panel2.add(jTable());

        panel2.setBorder(projects);


        return panel2;
    }
    
    
    private void createNewProject() {
        String[] status = {"Tilbud", "Ekstern", "Intern"};
        JComboBox combo = new JComboBox(status);
        JTextField navn = new JTextField();
        JTextField faser = new JTextField();
        JTextField opgaver = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        
        panel.add(new JLabel("Navn:"));
        panel.add(navn);
        panel.add(new JLabel("Faser:"));
        panel.add(faser);
        panel.add(new JLabel("Opgaver:"));
        panel.add(opgaver);
        panel.add(new JLabel("Status:"));
        panel.add(combo);
        int result = JOptionPane.showConfirmDialog(null, panel, "Opret nyt projekt",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION && !navn.getText().isEmpty() && !faser.getText().isEmpty() && !opgaver.getText().isEmpty()) {
            //JSONObject duplicate = Database.query("SELECT * FROM projektstyring_users WHERE username = '"+view.getUsername()+"'");
            JSONObject sql = Database.query("INSERT into projektstyring_projects (name,status,phases,tasks) VALUES('"+navn.getText()+"','"+combo.getSelectedIndex()+"','"+faser.getText()+"','"+opgaver.getText()+"')");
            model2.setRowCount(0);
            getProjects();
            
        } else {
                JOptionPane.showMessageDialog(null, "Navn, faser eller opgaver er tom.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }


    private JMenuBar mainMenu() {
        //Create the menu bar.
        menuBar = new JMenuBar();
        //Build the first menu.
        menu = new JMenu(username);

        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        UserMenuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        UserMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        UserMenuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(UserMenuItem);





        projectMenu = new JMenu("Projekt");
        projectMenu.setMnemonic(KeyEvent.VK_A);
        projectMenu.getAccessibleContext().setAccessibleDescription("Sådan min ven");
        menuBar.add(projectMenu);
        ProjectMenuItem1 = new JMenuItem("Opret Projekt");

        ProjectMenuItem1.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        ProjectMenuItem1.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        ProjectMenuItem2 = new JMenuItem("Redigere Projekt");
        ProjectMenuItem3 = new JMenuItem("Slet Projekt");


        ProjectMenuItem1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               createNewProject(); 
            }
        });
        ProjectMenuItem2.addActionListener(null);
        ProjectMenuItem3.addActionListener(null);


        projectMenu.add(ProjectMenuItem1);
        projectMenu.add(ProjectMenuItem2);
        projectMenu.add(ProjectMenuItem3);


        opgaveMenu = new JMenu("Opgave");
        opgaveMenu.setMnemonic(KeyEvent.VK_A);
        opgaveMenu.getAccessibleContext().setAccessibleDescription("Sådan min ven");
        menuBar.add(opgaveMenu);
        opgMenuItem1 = new JMenuItem("Opret Opgave",
                KeyEvent.VK_T);
        opgMenuItem2 = new JMenuItem("Redigere Opgave");
        opgMenuItem3 = new JMenuItem("Slet Projekt");

        opgMenuItem1.addActionListener(null);
        opgMenuItem2.addActionListener(null);
        opgMenuItem3.addActionListener(null);


        opgaveMenu.add(opgMenuItem1);
        opgaveMenu.add(opgMenuItem2);
        opgaveMenu.add(opgMenuItem3);

        return menuBar;

    }

    
    
    





    private JScrollPane jList() {
        list1 = new JList();
        model1 = new DefaultListModel();
        getEmployees();
        list1.setFixedCellWidth(150);
        list1.setModel(model1);
        pane1 = new JScrollPane(list1);

        return pane1;

    }

    private JScrollPane jTable() {


        model2 = new DefaultTableModel(){
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};
        
        
        

        mainTable = new JTable();

        
        addColumnHeaders();

        mainTable.setModel(model2);
        
        getProjects();

        pane2 = new JScrollPane(mainTable);

        mainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (mainTable.getSelectedRow() > -1) {
            // print first column value from selected row
            JFrame test = new JFrame();
            test.setVisible(true);
            test.setTitle(username);
        }
            }
        });
        return pane2;

    }


    private void getEmployees() {

        JSONObject employees = Database.query("SELECT * FROM `projektstyring_users` WHERE 1");
        //Reads the results from the query
        JSONArray tmp = employees.getJSONArray("results");
        for (int i = 0; i < tmp.length(); i++) {
            JSONObject obj = tmp.getJSONObject(i);
            model1.addElement(obj.getString("username") /*+ "(" + getInitials(obj.getString("firstname"),obj.getString("lastname")) + ")"*/);

        }


    }


    private void getProjects() {
        
        JSONObject projects = Database.query("SELECT * FROM `projektstyring_projects` WHERE 1");
        //Reads the results from the query
        JSONArray tmp = projects.getJSONArray("results");

        String id;
        String name;
        String status;
        String phases;
        String tasks;
        
        
        
        
        //int numberOfCols = tmp.getJSONObject(0).length();
        for (int i = 0; i < tmp.length(); i++) {
            JSONObject obj = tmp.getJSONObject(i);
           
            id = obj.getString("id");
            name = obj.getString("name");
            status = obj.getString("status");
            phases = obj.getString("phases");
            tasks = obj.getString("tasks");
            
            //name,id,phases,tasks,status
            
            Object[] rowData = {name, id, phases, tasks,status};
            

            model2.addRow(rowData);
        }


        //  model2.addRow(rowData);


    }


    private String getInitials(String firstname, String lastname) {
        String fullname = firstname + " " + lastname;
        Pattern p = Pattern.compile("((^| )[A-Za-z])");
        Matcher m = p.matcher(fullname);
        String inititals = "";
        while (m.find()) {
            inititals += m.group().trim();
        }
        return inititals.toUpperCase();
    }

    private void addColumnHeaders() {
        
        JSONObject projects = Database.query("SELECT * FROM `projektstyring_projects` WHERE 1");
        
        //Reads the results from the query
        JSONArray tmp = projects.getJSONArray("results");
        
        for(int i = 0; i < tmp.getJSONObject(0).length(); i++)
        {
        model2.addColumn(tmp.getJSONObject(0).names().get(i));
        }
        
        
    }

}
