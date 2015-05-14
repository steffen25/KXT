import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Created by danniwu on 14/05/15.
 */
public class DanniLoginGUI extends JFrame implements ActionListener {

    private DBConnector conn = DBConnector.getDbCon();

    public JTextField EnterLoginTextField;
    public JTextField EnterPasswordTextField;
    public JLabel usernameLabel;
    public JLabel passwordLabel;
    public String username;
    public String password;
    public JButton Enter;
    public JButton Close;


    public DanniLoginGUI() {

        setBackground(Color.black);
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setLayout(gridBagLayout);
        usernameLabel = new JLabel(username,JLabel.CENTER);
        passwordLabel = new JLabel(password,JLabel.CENTER);
        EnterLoginTextField = new JTextField(15);
        EnterPasswordTextField = new JTextField(15);
        Enter = new JButton("Login");
        Close = new JButton("Luk");
        gridBagConstraints.gridx=3;
        gridBagConstraints.gridy=0;
        gridBagLayout.setConstraints(usernameLabel,gridBagConstraints);
        add(usernameLabel);

        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=0;
        gridBagLayout.setConstraints(EnterLoginTextField,gridBagConstraints);
        add(EnterLoginTextField);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagLayout.setConstraints(passwordLabel,gridBagConstraints);
        add(passwordLabel);

        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=2;
        gridBagLayout.setConstraints(EnterPasswordTextField, gridBagConstraints);
        add(EnterPasswordTextField);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=3;
        gridBagLayout.setConstraints(Enter, gridBagConstraints);
        add(Enter);

        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=3;
        gridBagLayout.setConstraints(Close, gridBagConstraints);
        add(Close);

        Enter.addActionListener(this);
        Close.addActionListener(this);


    }

    public void actionPerformed(ActionEvent button) {

        if (button.getSource()== Close) {
            System.out.println("Lukker ned for systemet....");
            System.exit(0);
        }

        if (button.getSource()== Enter) {
            System.out.println("Du er logget ind i systemet....");
            try {

                String username = EnterLoginTextField.getText().toString();
                String password = EnterPasswordTextField.getText().toString();
                ResultSet result = conn.query("SELECT username FROM user where username='" + username + "' and password='" + password + "'");
                if (result.next()) {
                    JOptionPane.showMessageDialog(this,"Tillykke du har logget ind i systemet.");

                    /*

                    Husk at der skal åbnes et nyt gui window herfra, når man har indtastet de rigtige oplysninger.

                    */
                }
                else {

                    JOptionPane.showMessageDialog(this,"Du har tastet de forkerte... Prøv igen");
                    System.exit(0);
                }

            } catch (Exception e) {

            }
        }

    }

    public static void main(String[] args) {
        DanniLoginGUI loginGUI = new DanniLoginGUI();
        loginGUI.setVisible(true);
    }
}
