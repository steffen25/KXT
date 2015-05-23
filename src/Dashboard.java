import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    private JPanel panel1, panel2, panel3, panel4;

    private JLabel label1, label2;

    private JButton but1, but2;

    private JList list1, list2;

    private JScrollPane pane1, pane2;

    private String username;

    private DefaultListModel model1, model3, model4;

    private DefaultTableModel model2;

    private JMenuBar menuBar;

    private JMenu menu, projectMenu, opgaveMenu, FaseMenu;

    private JMenuItem UserMenuItem, ProjectMenuItem1, ProjectMenuItem2, ProjectMenuItem3, opgMenuItem1, opgMenuItem2, opgMenuItem3, faseMenuItem1,faseMenuItem2,faseMenuItem3;

    private JTable mainTable;

    //List<Project> projects = new ArrayList<Project>();
    //List<Employee> employees = new ArrayList<Employee>();

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

        panel2 = new JPanel(new BorderLayout());

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
            JSONObject sql = Database.query("INSERT into projektstyring_project1 (name,status,phases,tasks) VALUES('" + navn.getText() + "','" + combo.getSelectedIndex() + "','" + faser.getText() + "','" + opgaver.getText() + "')");
            model2.setRowCount(0);
            getProjects();

        } else {
            JOptionPane.showMessageDialog(null, "Navn, faser eller opgaver er tom.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void createNewAssignment() {
        String[] status = {"Tilbud", "Ekstern", "Intern"};
        JComboBox combo = new JComboBox(status);
        JTextField title = new JTextField();
        JTextField description = new JTextField();
        JTextField faser = new JTextField();
        JTextField ansvar = new JTextField();
        JTextField kommentar = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));


        panel.add(new JLabel("Titel:"));
        panel.add(title);
        panel.add(new JLabel("Beskrivelse:"));
        panel.add(description);
        panel.add(new JLabel("Faser:"));
        panel.add(faser);
        panel.add(new JLabel("Ansvar:"));
        panel.add(ansvar);
        panel.add(new JLabel("Kommentar"));
        panel.add(kommentar);

        int result = JOptionPane.showConfirmDialog(null, panel, "Opret nyt projekt",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION && !title.getText().isEmpty() && !description.getText().isEmpty() && !faser.getText().isEmpty() && !ansvar.getText().isEmpty() && !kommentar.getText().isEmpty()) {
            //JSONObject duplicate = Database.query("SELECT * FROM projektstyring_users WHERE username = '"+view.getUsername()+"'");
            //JSONObject sql = Database.query("INSERT into projektstyring_projects (name,status,phases,tasks) VALUES('" + navn.getText() + "','" + combo.getSelectedIndex() + "','" + faser.getText() + "','" + opgaver.getText() + "')");
            //model2.setRowCount(0);

        } else {
            JOptionPane.showMessageDialog(null, "Titel, Beskrivelse, faser, ansvar eller kommentar er tom.", "Error", JOptionPane.ERROR_MESSAGE);

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

        opgMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewAssignment();
            }
        });
        opgMenuItem2.addActionListener(null);
        opgMenuItem3.addActionListener(null);


        opgaveMenu.add(opgMenuItem1);
        opgaveMenu.add(opgMenuItem2);
        opgaveMenu.add(opgMenuItem3);



        FaseMenu = new JMenu("Fase");
        FaseMenu.setMnemonic(KeyEvent.VK_A);
        FaseMenu.getAccessibleContext().setAccessibleDescription("Sådan min ven");
        menuBar.add(FaseMenu);

         faseMenuItem1 = new JMenuItem("Opret fase",
                KeyEvent.VK_T);
        faseMenuItem2 = new JMenuItem("Rediger fase");
        faseMenuItem3 = new JMenuItem("slet fase");

        faseMenuItem1.addActionListener(null);
        faseMenuItem2.addActionListener(null);
        faseMenuItem3.addActionListener(null);

        FaseMenu.add(faseMenuItem1);
        FaseMenu.add(faseMenuItem2);
        FaseMenu.add(faseMenuItem3);


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


        model2 = new DefaultTableModel() {
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
        but1 = new JButton("Tilbage");
        panel4 = new JPanel(new GridLayout(0, 3));
        panel4.setBackground(Color.BLUE);

        mainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (mainTable.getSelectedRow() > -1) {


                    int row = mainTable.getSelectedRow();
                    panel2.removeAll();
                    panel2.revalidate();
                    panel2.repaint();

                    TitledBorder projekt = BorderFactory.createTitledBorder("Projektnavn: " + mainTable.getValueAt(row, 0).toString() + " Projekt ID: " + mainTable.getValueAt(row, 1));
                    projekt.setTitlePosition(TitledBorder.BELOW_TOP);
                    projekt.setTitleJustification(TitledBorder.CENTER);
                    panel2.setBorder(projekt);


                    JSONObject phases = Database.query("SELECT * FROM projektstyring_phase1 WHERE p_id = '" + mainTable.getValueAt(row, 1) + "'");
                    //Reads the results from the query
                    JSONArray tmp = phases.getJSONArray("results");


                    int antalJlister = tmp.length();
                    JList jlister[] = new JList[antalJlister];
                    DefaultListModel jlistmodeller[] = new DefaultListModel[antalJlister];

                    panel2.setLayout(new BorderLayout());
                    panel2.setBackground(Color.GREEN);


                    for (int i = 0; i < antalJlister; i++) {

                        TitledBorder fasenavn = BorderFactory.createTitledBorder("Fase: " + tmp.getJSONObject(i).getString("phase"));


                        fasenavn.setTitlePosition(TitledBorder.BELOW_TOP);
                        fasenavn.setTitleJustification(TitledBorder.CENTER);
                        jlister[i] = new JList();

                        jlistmodeller[i] = new DefaultListModel();
                        jlister[i].setModel(jlistmodeller[i]);
                        //jlistmodeller[i].add(i, obj.getString("phase"));

                        JScrollPane jlistscroll = new JScrollPane(jlister[i]);
                        jlistscroll.setBorder(fasenavn);

                        jlistmodeller[i].addElement(tmp.getJSONObject(i).getString("phase"));

                        panel4.add(jlistscroll);

                    }


                    JScrollPane scroll = new JScrollPane(panel4);
                    panel2.add(scroll);
                    panel2.add(but1, BorderLayout.SOUTH);

                    //System.out.println(tmp.length());
                    //model3.addElement("test");
                    // print first column value from selected row
                    //leftPanel().add(list2);
                }
            }
        });
        but1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Tilbage")) {

                    panel2.removeAll();
                    panel2.revalidate();
                    panel2.repaint();
                    panel2.setLayout(new BorderLayout());
                    TitledBorder projekt = BorderFactory.createTitledBorder("Projekter");
                    projekt.setTitlePosition(TitledBorder.BELOW_TOP);
                    projekt.setTitleJustification(TitledBorder.CENTER);
                    panel2.add(jTable());

                    panel2.setBorder(projekt);
                }
            }
        });

        return pane2;

    }


    private void getEmployees() {

        JSONObject employees = Database.query("SELECT * FROM `projektstyring_user1` WHERE 1");
        //Reads the results from the query
        JSONArray tmp = employees.getJSONArray("results");
        for (int i = 0; i < tmp.length(); i++) {
            JSONObject obj = tmp.getJSONObject(i);
            model1.addElement(obj.getString("username") /*+ "(" + getInitials(obj.getString("firstname"),obj.getString("lastname")) + ")"*/);

        }
    }


    private void getProjects() {

        JSONObject projects = Database.query("SELECT * FROM `projektstyring_project1` WHERE 1");
        //Reads the results from the query
        JSONArray tmp = projects.getJSONArray("results");

        String id;
        String name;
        String status;


        //int numberOfCols = tmp.getJSONObject(0).length();
        for (int i = 0; i < tmp.length(); i++) {
            JSONObject obj = tmp.getJSONObject(i);
            id = obj.getString("p_id");
            name = obj.getString("name");
            status = obj.getString("status");

            Object[] rowData = {name, id, status};
            model2.addRow(rowData);
        }
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

        JSONObject projects = Database.query("SELECT * FROM `projektstyring_project1` WHERE 1");
        //Reads the results from the query
        JSONArray tmp = projects.getJSONArray("results");
        for (int i = 0; i < tmp.getJSONObject(0).length(); i++) {
            model2.addColumn(tmp.getJSONObject(0).names().get(i));
        }


    }

}
