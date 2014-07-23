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
	private HashMap<String, String> prefs;
	
	// default constructor
	public JSONUser(){
		this.username = "default";
		this.personURI = null;
		this.permissions = new ArrayList<Permission>();
		this.prefs = new HashMap<String, String>();
	}

	public JSONUser(String myName, URI myUri){
		this.username = myName;
		this.personURI = myUri;
		this.permissions = new ArrayList<Permission>();
		this.prefs = new HashMap<String,String>();
	}
	
	// This will be used for reading Users from JSON.
	public JSONUser(JSONObject jsonUser){
		try{
			this.username = jsonUser.getString("username");
			this.personURI = new URI(jsonUser.getString("uri"));
			this.permissions = (ArrayList<Permission>)(jsonUser.get("permissions"));
			this.prefs = (HashMap<String,String>)(jsonUser.get("prefs"));
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
	
	// returns TRUE if the given Permission is set (even if it is set to NONE)
	// and FALSE if there is no Permission with that name exists
	public boolean hasPermission(String pName){
		ListIterator<Permission> iter = permissions.listIterator();
		Permission temp;
		while(iter.hasNext()){
			temp = iter.next();
			if(temp.getPermissionName().equals(pName))
				return true;
		}
		return false;
	}// /hasPermission
	
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
	
	public List<Permission> getPermissions(){
		return this.permissions;
	}
	public void setPermissions(List<Permission> thePerms){
		this.permissions = (ArrayList)thePerms;
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
	public Map<String,String> getPreferences(){
		return this.prefs;
	}
	public void setPreferences(Map<String,String> thePrefs){
		this.prefs = (HashMap)thePrefs;
	}
	
	/*public String toString(){
		JSONObject theJSON = this.toJSON();
		return theJSON.toString(4);
	}*/
	
}// /JSONUser