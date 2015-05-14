

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Login"))
        {
            System.out.println(view.getUsername() + " " +view.getPassword());
            Database.query("INSERT INTO projektstyring_test (username, password, email) VALUES ('"+view.getUsername()+"','"+view.getPassword()+"','email@email.com')");
            //System.out.println(result);
            
        }
        //if(view.getu)
    }
    
    
    

    
    
    
    
}
