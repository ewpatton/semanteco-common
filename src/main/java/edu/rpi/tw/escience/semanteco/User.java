package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;

public interface User{
	String getUserName();
	
	// user's person URI?
	URI getUri();
	
	void addPermission(Permission permission);
	void revokePermission(Permission permission);
	
	// returns TRUE if the given Permission is set (even if it is set to NONE)
	// and FALSE if there is no Permission with that name exists
	boolean hasPermission(String pName);
	
	Permission getPermission(String pName);
	List<Permission> getPermissions();
	
	/* I'm thinking handling all these as strings might be easiest, and
	/  then they can be parsed as necessary on the client side from JSON?
	/  I am open to ideas on this.
	*/
	void setPreference(String key, String value);
	void removePreference(String key);
	String getPreference(String key);
	
}// /User  