package edu.rpi.tw.escience.semanteco;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONParser;

public class JSONUserStore implements UserStore{
	// Returns TRUE on successful write or FALSE on error
	boolean writeUser(User user){
		String identifier = user.getUri();
		JSONObject theJSON = new JSONObject();
		theJSON.put("username", user.getUsername());
		theJSON.put("uri", identifier);
		theJSON.put("permissions", user.getPermissions());
		theJSON.put("prefs", user.getPreferences());
		String fname = identifier + ".txt";
		try {
			File f = new File(fname);
			f.createNewFile();
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(theJSON.toString());
			output.close();
		} catch ( IOException e ){
			e.printStackTrace();
			return false;
		}
		return true;
	}// /writeUser
	
	// identifier should be some way of uniquely identifying a User
	// Ideally a URI?
	User readUser(String identifier){
		String fname = identifier + ".txt";
		JSONParser parser = new JSONParser();
		User user = new User();
		try{
			JSONObject obj = (JSONObject) parser.parse(newFileReader(identifier));
			user.setName(obj.getString("name"));
			user.setUri((URI)obj.get("uri"));
			user.setPermissions((List<Permissions>)obj.get("permissions"));
			user.setPreferences((HashMap<String,String>)obj.get("preferences"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return user;
	}// /readUser
}