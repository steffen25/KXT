

/**
 * Created by danniwu on 11/05/15.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class LoginView {
    
    
    private JFrame frame;
    
    private JPanel loginPanel;
    
    private JButton loginButton, cancelButton;
        
    private JLabel usernameLabel,passwordLabel;
    
    private JTextField username;

    private JPasswordField password;
        
    private LoginModel model;

    
    public LoginView(LoginModel model) {
       
        this.model = model;
        try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                frame = new JFrame("KXT Login");
                frame.setIconImage(new ImageIcon(getClass().getResource("/icon_kxt.png")).getImage());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel content = new JPanel(new GridBagLayout());
                //content.setBackground(Color.GREEN);
                content.setBorder(new EmptyBorder(20, 20, 20, 20));
                frame.setContentPane(content);
                frame.add(loginPanel());
                frame.getRootPane().setDefaultButton(loginButton);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
    }
    
        /** Return the user name entered by the user. */
    String getUsername(){
    return username.getText();
    }
  
    /** Return the password entered by the user. */
    String getPassword(){
    return new String(password.getPassword());
    //return password.getPassword().toString();
    }
    
    
    private JPanel loginPanel()
    {
            loginPanel = new JPanel();
            loginButton = new JButton("Login");
            cancelButton = new JButton("Register");
            usernameLabel = new JLabel("Username: ");
            username = new JTextField(15);
            passwordLabel = new JLabel("Password: ");
            password = new JPasswordField(15);
            loginPanel.setLayout(new GridBagLayout());
            loginPanel.setBorder(new TitledBorder("KXT Login"));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            loginPanel.add(usernameLabel, gbc);
            gbc.gridy++;
            loginPanel.add(passwordLabel, gbc);

            gbc.gridx++;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            loginPanel.add(username, gbc);
            gbc.gridy++;
            loginPanel.add(password, gbc);

            gbc.gridx = 1;
            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            loginPanel.add(loginButton, gbc);
            gbc.gridx++;
            loginPanel.add(cancelButton, gbc);
        return loginPanel;
        
    }
    
    
    public JButton getLoginButton()
    {
        
        return loginButton;
        
    }
    public JButton getCancelButton()
    {
        
        return cancelButton;
        
    }

    public JFrame getFrame() {
        return frame;
    }
    
    
    
  
    
    

}
