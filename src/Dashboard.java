
import java.awt.BorderLayout;
import java.awt.PopupMenu;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    
    private JList list1;
    
    private JScrollPane pane1;
    
    private String username;
    
    
    //List<Project> projects = new ArrayList<Project>();
    List<Employee> employees = new ArrayList<Employee>();

    public Dashboard(String username) {
        
                this.username = username;
                frame = new JFrame("KXT - Projektstyring");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1024, 768);
                frame.add(leftPanel(),BorderLayout.WEST);
                frame.add(rightPanel(),BorderLayout.EAST);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
    }

    private JPanel rightPanel() {
        
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        String categories[] = { "Household", "Office", "Extended Family",
        "Company (US)", "Company (World)", "Team", "Will",
        "Birthday Card List", "High School", "Country", "Continent",
        "Planet" };
        list1 = new JList(getEmployees().toArray());
        
        pane1 = new JScrollPane(list1);
        
        TitledBorder medarbejdere = BorderFactory.createTitledBorder("Medarbejdere");
        medarbejdere.setTitlePosition(TitledBorder.BELOW_TOP);
        medarbejdere.setTitleJustification(TitledBorder.CENTER);
        getEmployees();       
        panel1.add(pane1);
        panel1.setBorder(medarbejdere);
        
        return panel1;
    }
    
    
    private JPanel leftPanel() {
        
        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        label2 = new JLabel(username);
        
        panel2.add(label2,BorderLayout.CENTER);
        
        return panel2;
    }
    
    
    
    public static String[] getStringArray(JSONArray jsonArray){
    String[] stringArray = null;
    int length = jsonArray.length();
    if(jsonArray!=null){
        stringArray = new String[length];
        for(int i=0;i<length;i++){
            stringArray[i]= jsonArray.optString(i);
        }
    }
    return stringArray;
}
    
    
    
    private List getEmployees()
    {
        
        JSONObject employees = Database.query("SELECT * FROM `projektstyring_users` WHERE 1");
        //Reads the results from the query
	JSONArray tmp = employees.getJSONArray("results");
        List<String> list = new ArrayList<String>();
        		for(int i = 0; i < tmp.length(); i++){
			JSONObject obj = tmp.getJSONObject(i);
                        list.add( obj.getString("username") );
		}
                        //System.out.println(list);
        return list;
        
        
    }
    
}
