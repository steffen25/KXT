

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
    
  /** Action command string. */
  static final String LOGIN = "Login";
  
  /** Action command string. */
  static final String CANCEL = "Cancel";
  
  
  
  //  PRIVATE 
  private LoginUI loginView = new LoginUI(this);
  private int loginNumAttempts = 0;
  private static final int MAX_NUM_ATTEMPTS = 3;
  

    @Override
    public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    if ( LOGIN.equals(command) ) {
        System.out.println("klikket på login");
        System.out.println(loginView.getUserName());
        System.out.println("test" +loginView.getUserName());
        
      validateUserCredentials();
    }
    else if( CANCEL.equals(command) ){
      System.exit(0);
    }
    else {
      throw new AssertionError("Unexpected command: " + command);
    }
  }
    
    
    
    
    
    void validateUserCredentials(){
    loginNumAttempts++;
    String userName = loginView.getUserName();
    String password = loginView.getPassword();
    boolean connected = Database.isConnected();
    String encryptedPassword = null;
    if (connected)
    {
        JSONObject result = Database.query("SELECT FROM users WHERE = '"+ userName +"'");
        JSONArray tmp = result.getJSONArray("results");
        JSONObject obj = tmp.getJSONObject(0);
        encryptedPassword = obj.getString("pwd");
    }
    if (passwordEncryptor.checkPassword(password, encryptedPassword)){
      loginView.dispose();
      System.out.println("Correct");
    }
    else {
      if(loginNumAttempts < MAX_NUM_ATTEMPTS) {
        //fView.tryAgain();
      }
      else {
        //fLogger.config("Shutting down. User credentials not valid for more than the max number of tries.");
        //shutDownApplication();
      }
    }
  }
    
    
    
    
    
}
