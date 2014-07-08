package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;

public class AtomicPermission implements Permission{
	public enum Level{ 
		NONE, READ_ONLY, WRITE_ONLY, READ_WRITE, ADMIN;
	}

	private String pName;
	private Level pLevel;
	private URI pURI;
	
	public AtomicPermission(){
		this.pName = "null";
		this.pLevel = NONE;
		this.pURI = null;
	}
	// assuming the URI is of the format
	// http://<authority>/<Level>
	public AtomicPermission(URI theURI){
		this.pName = theURI.getAuthority();
		this.pLevel = (theURI.getPath()).toUpperCase();
		this.pURI = theURI;
	}
	
	String getPermissionName(){
		return this.pName;
	}
	
	Level getPermissionLevel(){
		return this.pLevel;
	}
	
	void setPermissionLevel(Level newLevel){
		this.pLevel = newLevel;
	}
	
	/* 
	* AtomicPermission class in semanteco-core that takes a URI as a parameter and 
	* for its hasPermission(User) method, check that the user contains the permission object.
	*/
	hasPermission(User user){
		List<AtomicPermission> perms = user.getPermissions();
		//private ListIterator<AtomicPermission> iter = perms.listIterator();
		int index = perms.indexOf(this);
		if( index < 0 ) return false;
		else return true;
	}// /hasPermission

	/**
	 * Allows permissions to implement rules as a function of users so that
	 * explicit &lt;user, permission&gt; pairs do not need to be set by an
	 * administrator.
	 * @param user A User object to evaluate for permission
	 * @return true if the user passes the test this permission requires,
	 * otherwise false.
	 */
	boolean evaluate(User user){
		if(this.hasPermission(user)) return true;
		else return false;
	}// /evaluate
}// /AtomicPermission
