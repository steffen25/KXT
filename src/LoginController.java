

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.json.JSONArray;
import org.json.JSONObject;

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
        if(e.getActionCommand().equals("Login"))
        {
            String username = view.getUsername();
            //Database.query("INSERT INTO projektstyring_test (username, password, email) VALUES ('"+view.getUsername()+"','"+view.getPassword()+"','email@email.com')");
            //System.out.println(result);
            JSONObject password = Database.query("SELECT * FROM projektstyring_test WHERE username = '"+view.getUsername()+"'");
            if(password.getJSONArray("results").length() == 0)
            {
                System.out.println("Invalid username or password");
            }
            else {
                
            
            
            //System.out.println(password.getJSONArray("results").length());
            
            
            
            
            String encryptedPassword = password.getJSONArray("results").getJSONObject(0).getString("password");
            
            if (passwordEncryptor.checkPassword(view.getPassword(), encryptedPassword)) {
            System.out.println("Correct password");
            model.setUsername(username);
            JFrame newFrame = new JFrame();
            newFrame.setTitle("KXT - " +model.getUsername());
            
            newFrame.setVisible(true);
            } else {
            System.out.println("Invalid username or password");
            }
            
            }    
            
        }
        else if (e.getActionCommand().equals("Register"))
        {
            java.util.Date today = new java.util.Date();
            java.sql.Date sqlToday = new java.sql.Date(today.getTime());
            
            String encryptedPassword = passwordEncryptor.encryptPassword(view.getPassword());
            String username = view.getUsername();
            //System.out.println("Date:" +sqlToday);
            JSONObject duplicate = Database.query("SELECT * FROM projektstyring_test WHERE username = '"+view.getUsername()+"'");

            if(duplicate.getJSONArray("results").length() >= 1)
            {
                System.out.println("User already exist");
            }
            else if(!username.isEmpty() && !view.getPassword().isEmpty()) {
                JSONObject sql = Database.query("INSERT into projektstyring_test (username,password,email,reg_date) VALUES('"+username+"','"+encryptedPassword+"','email@email.com','"+sqlToday+"')");
                //System.out.println(sql);
                System.out.println("User: "+username+" created successfully");
            }
            else {
                System.out.println("Username or password is empty");
            }
            //System.out.println("Check"+duplicate+"size: "+duplicate.getJSONArray("results").length());

            //JSONObject sql = Database.query("INSERT into projektstyring_test (username,password,email) VALUES('"+username+"','"+encryptedPassword+"','email@email.com')");
            //System.out.println(sql);
        }
        //if(view.getu)
    }
    
    
    

    
    
    
    
}
