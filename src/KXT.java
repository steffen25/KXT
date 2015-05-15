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
		//Test to see if a connection is established
		System.out.println("Connection: "+Database.isConnected());
		
		//Write query the database with the SQL code
		JSONObject result = Database.query("SELECT * from users");
		
		
		//Reads the results from the query
		JSONArray tmp = result.getJSONArray("results");
		
		for(int i = 0; i < tmp.length(); i++){
			JSONObject obj = tmp.getJSONObject(i);
			System.out.println("name:\t" + obj.getString("name"));
			System.out.println("email:\t" + obj.getString("email"));
			System.out.println();
		}
                
                LoginModel model = new LoginModel();
                LoginView view = new LoginView(model);
                LoginController controller = new LoginController(view,model);
                
		
	}
    
}
