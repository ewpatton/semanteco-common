package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;
import java.util.HashMap;
import org.json.JSONObject;

public class TestUser implements User{
	private String username;
	private String personURI;
	private List<Permission> permissions;
	private HashMap<String, String> prefs;
	
	// default constructor
	public TestUser(){
		this.username = "default";
		this.personURI = "";
		this.permissions = new List<Permission>();
		this.prefs = new HashMap<String, String>();
	}

	public TestUser(String myName, String myUri){
		this.username = myName;
		this.personURI = myUri;
		this.permissions = new List<Permission>();
		this.prefs = new HashMap<String,String>();
	}
	
	// This will be used for reading Users from JSON.
	public TestUser(JSONObject jsonUser){
		this.username = jsonUser.getString("username");
		this.personURI = (URI)jsonUser.get("uri");
		this.permissions = (List<Permission>)(jsonUser.get("permissions"));
		this.prefs = (HashMap<String,String>)(jsonUser.get("prefs"));
	}
	
	public JSONObject toJSON(){
		JSONObject theJSON = new JSONObject();
		theJSON.put("username", this.username);
		theJSON.put("uri", this.personURI);
		theJSON.put("permissions", this.permissions);
		theJSON.put("prefs", this.prefs);
		return theJSON;
	}
	
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String theName){
		this.username = theName;
	}
	
	public String getUri(){
		return this.personURI;
	}
	public void setUri(URI theUri){
		this.personURI = theUri;
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
	
	// returns TRUE if the given Permission is set (even if it is set to NONE)
	// and FALSE if there is no Permission with that name exists
	public boolean hasPermission(String pName){
		ListIterator<Permission> iter = new ListIterator();
		Permission temp;
		while(iter.hasNext()){
			temp = iter.next();
			if(temp.getPermissionName().equals(pName))
				return true;
		}
		return false;
	}// /hasPermission
	
	public TestPermission getPermission(String pName){
		ListIterator<Permission> iter = new ListIterator();
		Permission temp;
		while(iter.hasNext()){
			temp = iter.next();
			if(temp.getPermissionName().equals(pName))
				return temp;
		}
		return null;
	}
	
	public List<Permission> getPermissions(){
		return this.permissions;
	}
	public void setPermissions(List<Permission> thePerms){
		this.permissions = thePerms;
	}
	
	// User Preference Methods:
	public void setPreference(String key, String value){
		this.prefs.put(key, value);
	}
	// If key had an associated value, remove() returns the value,
	// 	 else returns NULL
	public void removePreference(String key){
		this.prefs.remove(key);
	}
	// will return NULL if there is no value associated with the key
	public String getPreference(String key){
		return this.prefs.get(key);
	}
	public HashMap<String,String> getPreferences(){
		return this.prefs;
	}
	public void setPreferences(HashMap<String,String> thePrefs){
		this.prefs = thePrefs;
	}
	
	public String toString(){
		JSONObject theJSON = this.toJSON;
		return theJSON.toString(4);
	}
	
}// /TestUser