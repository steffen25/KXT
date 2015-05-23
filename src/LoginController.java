import org.jasypt.util.password.BasicPasswordEncryptor;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by danniwu on 11/05/15.
 */
public class LoginController implements ActionListener {

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

    private LoginView view;

    private LoginModel model;

    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
        System.out.println("Database connection: " + Database.isConnected());
        view.getLoginButton().addActionListener(this);
        view.getCancelButton().addActionListener(this);
        //System.out.println(model.getUsername());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            validateUserCredentials();
        } else if (e.getActionCommand().equals("Register")) {
            java.util.Date today = new java.util.Date();
            java.sql.Date sqlToday = new java.sql.Date(today.getTime());

            String encryptedPassword = passwordEncryptor.encryptPassword(view.getPassword());
            String username = view.getUsername();
            //System.out.println("Date:" +sqlToday);
            JSONObject duplicate = Database.query("SELECT * FROM projektstyring_user1 WHERE username = '" + view.getUsername() + "'");

            if (duplicate.getJSONArray("results").length() >= 1) {

                System.out.println("User already exist");
            } else if (!username.isEmpty() && !view.getPassword().isEmpty()) {
                JSONObject sql = Database.query("INSERT into projektstyring_user1 (username,password,email,reg_date) VALUES('" + username + "','" + encryptedPassword + "','email@email.com','" + sqlToday + "')");
                //System.out.println(sql);
                System.out.println("User: " + username + " created successfully");
            } else {
                System.out.println("Username or password is empty");
            }
            //System.out.println("Check"+duplicate+"size: "+duplicate.getJSONArray("results").length());

            //JSONObject sql = Database.query("INSERT into projektstyring_test (username,password,email) VALUES('"+username+"','"+encryptedPassword+"','email@email.com')");
            //System.out.println(sql);
        }
        //if(view.getu)
    }

    private void validateUserCredentials() {


        String username = view.getUsername();

        //check if user exist in out system, if so check if our stored password hash matches the inputted one.
        JSONObject userPassword = Database.query("SELECT * FROM projektstyring_user1 WHERE username = '" + view.getUsername() + "'");
        if (userPassword.getJSONArray("results").length() == 0) {
            JOptionPane.showMessageDialog(null, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // here we check if the password matches
        else {

            String encryptedPassword = userPassword.getJSONArray("results").getJSONObject(0).getString("password");

            if (passwordEncryptor.checkPassword(view.getPassword(), encryptedPassword)) {
                //System.out.println("Correct password");
                model.setUsername(username);

                view.getFrame().dispose();

                Dashboard dashboard = new Dashboard(username);
                
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }


    }


}
