

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

public class LoginUI extends JFrame{
    private JButton loginButton;
        
    private JLabel usernameLabel,passwordLabel;
    
    private JTextField username;
    
    private JPasswordField password;
    
    private LoginController loginController;
    
    
      /**  Constructor.  */
    LoginUI(LoginController controller)
    {
        loginController = controller;
    }{
          
    EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("KXT Login");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel content = new JPanel(new GridBagLayout());
                //content.setBackground(Color.GREEN);
                content.setBorder(new EmptyBorder(20, 20, 20, 20));
                frame.setContentPane(content);
                frame.add(new LoginPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        
    }
    
    
    
    
    /** Return the user name entered by the user. */
    String getUserName(){
    return username.getText();
    }
  
    /** Return the password entered by the user. */
    String getPassword(){
    return password.getPassword().toString();
    }
    
    
    
    public class LoginPane extends JPanel {

        public LoginPane() {
            
            loginButton = new JButton("Login");
            usernameLabel = new JLabel("Username:");
            username = new JTextField(15);
            passwordLabel = new JLabel("Password");
            password = new JPasswordField(15);
            loginButton.setActionCommand(LoginController.LOGIN);
            loginButton.addActionListener(loginController);
            setLayout(new GridBagLayout());
            setBorder(new TitledBorder("KXT Login"));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(usernameLabel, gbc);
            gbc.gridy++;
            add(passwordLabel, gbc);

            gbc.gridx++;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;
            add(username, gbc);
            gbc.gridy++;
            add(password, gbc);

            gbc.gridx = 1;
            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            add(loginButton, gbc);
            gbc.gridx++;
            add(new JButton("Cancel"), gbc);
        }

    }

    
    
    
    

}
