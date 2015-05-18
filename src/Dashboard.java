
import java.awt.BorderLayout;
import java.awt.PopupMenu;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
    
    private JLabel label1;
    
    
    //List<Project> projects = new ArrayList<Project>();
    //List<Employee> employees = new ArrayList<Employee>();

    Dashboard(String username) {
            frame.setTitle("KXT - Projektstyring");
            frame.setSize(1024, 768);
            frame.setLayout(new BorderLayout());
            frame.add(leftPanel(),BorderLayout.EAST);
            frame.add(rightPanel(),BorderLayout.WEST);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            JOptionPane.showMessageDialog(panel1, username);
    }

    private JPanel rightPanel() {
        
        panel1 = new JPanel();
        label1 = new JLabel("Medarbejdere");
        
        panel1.add(label1);
        
        return panel1;
    }
    
    
    private JPanel leftPanel() {
        
        panel2 = new JPanel();
        
        return panel2;
    }
    
}
