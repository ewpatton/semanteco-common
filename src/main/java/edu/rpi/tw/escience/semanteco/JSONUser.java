package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONException;

public class JSONUser implements User{
	private String username;
	private URI personURI;
	private ArrayList<Permission> permissions;
	private HashMap<String, Object> prefs;
	
	// default constructor
	public JSONUser(){
		this.username = "default";
		this.personURI = null;
		this.permissions = new ArrayList<Permission>();
		this.prefs = new HashMap<String,Object>();
	}

	// Creates a JSONUser with the specified name and URI,
	//  and blank sets of Permissions and preferences
	public JSONUser(String myName, URI myUri){
		this.username = myName;
		this.personURI = myUri;
		this.permissions = new ArrayList<Permission>();
		this.prefs = new HashMap<String,Object>();
	}
	
	// This will be used for reading stored JSONUsers from JSON
	public JSONUser(JSONObject jsonUser){
		try{
			this.username = jsonUser.getString("username");
			this.personURI = new URI(jsonUser.getString("uri"));
			this.permissions = (ArrayList<Permission>)(jsonUser.get("permissions"));
			this.prefs = (HashMap<String,Object>)(jsonUser.get("prefs"));
		} catch (JSONException e ){
			e.printStackTrace();
		} catch (URISyntaxException e){
			e.printStackTrace();
		}
	}
	
	/*public JSONObject toJSON(){
		JSONObject theJSON = new JSONObject();
		theJSON.put("username", this.username);
		theJSON.put("uri", this.personURI);
		theJSON.put("permissions", this.permissions);
		theJSON.put("prefs", this.prefs);
		return theJSON;
	}*/
	
	// Getters and Setters for JSONUser fields
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String theName){
		this.username = theName;
	}
	public URI getUri(){
		return this.personURI;
	}
	public void setUri(URI theUri){
		this.personURI = theUri;
	}
	public void setUri(String theUri){
		try{
			this.personURI = new URI(theUri);
		} catch (URISyntaxException e){
			e.printStackTrace();
		}
	}
	
	public void addPermission(Permission newPermission){
		permissions.add(newPermission);
	}
	
	public void revokePermission(Permission toRevoke){
		int index = permissions.indexOf(toRevoke);
		if( index != -1 ) {
			permissions.remove(index);
		}
	}// /revokePermission
	// Returns false if no Permission with name pName exists 
	// OR if the Permission exists but is set to NONE
	// Otherwise, returns true
	public boolean hasPermission(String pName){
		ListIterator<Permission> iter = permissions.listIterator();
		Permission temp;
		while(iter.hasNext()){
			temp = iter.next();
			if(temp.getPermissionName().equals(pName)){
				if(temp.getPermissionLevel() == Permission.Level.NONE)
					return false;
				else
					return true;
			}
		}
		return false;
	}// /hasPermission
	// Returns the Permission with the name provided as an argument
	// If no such Permission exists, returns null
	public Permission getPermission(String pName){
		ListIterator<Permission> iter = permissions.listIterator();
		Permission temp;
		while(iter.hasNext()){
			temp = iter.next();
			if(temp.getPermissionName().equals(pName))
				return temp;
		}
		return null;
	}
	// Returns a List of all Permissions
	public List<Permission> getPermissions(){
		return this.permissions;
	}
	// Sets ALL Permissions at once
	// (eg, when a JSONUser is loaded from storage)
	public void setPermissions(List<Permission> thePerms){
		this.permissions = (ArrayList)thePerms;
	}
	
	
	
	// User Preference Methods:
	// eg, for storing a single-value global preference
	public void setPreference(String key, String value){
		this.prefs.put(key, value);
	}
	// eg, for storing lists of loaded ontologies
	public void setPreference(String key, List<String> valueList){
		this.prefs.put(key, valueList);
	}
	// eg, for storing frequency-of-use stats for ontologies
	public void setPreference(String key, Map<String,Object> valueMap){
		this.prefs.put(key, valueMap);
	}
	// If key had an associated value, remove() returns the value,
	// 	 else returns NULL
	public void removePreference(String key){
		this.prefs.remove(key);
	}
	// will return NULL if there is no value associated with the key
	// This will either be a String, a List<String>, or a Map<String,Object>
	public JSONObject getPreference(String key){
		return new JSONObject(this.prefs.get(key));
	}
	// Returns ALL preferences
	public Map<String,Object> getPreferences(){
		return this.prefs;
	}
	public JSONObject getJSONPrefs(){
		return new JSONObject(this.prefs);
	}
	// Sets ALL preferences at once
	// (eg, when loading a JSONUser that has been stored)
	public void setPreferences(Map<String,Object> thePrefs){
		this.prefs = (HashMap)thePrefs;
	}
	
	/*public String toString(){
		JSONObject theJSON = this.toJSON();
		return theJSON.toString(4);
	}*/
	
}// /JSONUser