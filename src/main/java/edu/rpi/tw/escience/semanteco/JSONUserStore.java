package edu.rpi.tw.escience.semanteco;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUserStore implements UserStore{
	// Takes a User and parses it into a JSONObject, then records
	//    that JSON object for storage
	// Returns TRUE on successful write or FALSE on error
	@Override
	public boolean writeUser(User user){
		URI identifier = user.getUri();
		JSONObject theJSON = new JSONObject();
		try{
			theJSON.put("username", user.getUsername());
			theJSON.put("uri", identifier);
			theJSON.put("permissions", user.getPermissions());
			theJSON.put("prefs", user.getPreferences());
		} catch (JSONException e ){
			e.printStackTrace();
			return false;
		}
		String fname = identifier.getAuthority() + ".json";
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
	
	// This method takes a recorded user stored in a .json file and 
	//    parses it into a JSONUser object
	// identifier should be some way of uniquely identifying a User;
	// Ideally a URI, or a compression thereof that can be used as a file name
	@Override
	public JSONUser readUser(String identifier){
		String fname = identifier + ".json";
		JSONParser parser = new JSONParser();
		JSONUser user = new JSONUser();
		try{
			JSONObject obj = (JSONObject) parser.parse(new FileReader(identifier));
			user.setUsername(obj.getString("name"));
			user.setUri((URI)obj.get("uri"));
			user.setPermissions((List<Permission>)obj.get("permissions"));
			user.setPreferences((HashMap<String,Object>)obj.get("preferences"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}// /readUser
}