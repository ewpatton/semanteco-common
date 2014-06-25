package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;

public interface User{
	// user's person URI?
	URI getUri();
	
	// I guess we have to implement a Permission, too, somehow?
	void addPermission(Permission permission, String label);
	void revokePermission(Permission permission);
	Permission getPermission();
	<List>Permission getPermissions();
	
	/* I'm thinking handling all these as strings might be easiest, and
	/  then they can be parsed as necessary on the client side from JSON?
	/  I am open to ideas on this.
	*/
	void setPreference(String key, String value);
	void removePreference(String key);
	String getPreference(String key);
	
}// /User  