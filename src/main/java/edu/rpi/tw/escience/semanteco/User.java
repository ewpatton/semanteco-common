package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;
import java.util.Map;

public interface User{
	// get and set username
	String getUsername();
	void setUsername(String username);
	
	// get a User's person URI
	URI getUri();
	// set a User's person URI
	void setUri(URI theUri);
	
	// add a Permission
	void addPermission(Permission permission);
	// remove a Permission
	void revokePermission(Permission permission);
	
	// get a Permission given the name (key)
	Permission getPermission(String pName);
	// get all of a User's Permissions
	List<Permission> getPermissions();
	// set all of a User's Permissions (for use when reading a User from file/database)
	void setPermissions(List<Permission> perms);
	
	// returns TRUE if the given Permission is set (even if it is set to NONE)
	// and FALSE if there is no Permission with that name exists
	boolean hasPermission(String pName);
	
	// Set a preference by key, value pair
	void setPreference(String key, String value);
	// remove a preference by key
	void removePreference(String key);
	// get the value of a preference by its key
	Object getPreference(String key);
	// get all preferences
	Map<String,Object> getPreferences();
	// set all preferences (for use when reading a User from file/database)
	void setPreferences(Map<String,Object> prefs);
}// /User  