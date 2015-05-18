/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package kxt;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Steffen
 */
public class KXT {
    //TEST34234

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
                LoginModel model = new LoginModel();
                LoginView view = new LoginView(model);
                LoginController controller = new LoginController(view,model);
                
		
	}
    
}
