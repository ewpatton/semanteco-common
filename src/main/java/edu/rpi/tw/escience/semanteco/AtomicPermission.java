package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;

public class AtomicPermission implements Permission{

	private String pName;
	private Level pLevel;
	private URI pURI;
	
	// null constructor
	public AtomicPermission(){
		this.pName = "null";
		this.pLevel = Level.valueOf("NONE");
		this.pURI = null;
	}
	// assuming the URI is of the format
	// http://<authority>/<Level>
	public AtomicPermission(URI theURI){
		this.pName = theURI.getAuthority();
		this.pLevel = Level.valueOf((theURI.getPath()).toUpperCase());
		this.pURI = theURI;
	}
	
	public String getPermissionName(){
		return this.pName;
	}
	
	public Level getPermissionLevel(){
		return this.pLevel;
	}
	
	@Override
	public void setPermissionLevel(Level newLevel){
		this.pLevel = newLevel;
	}
	
	/* 
	* AtomicPermission class in semanteco-core that takes a URI as a parameter and 
	* for its hasPermission(User) method, check that the user contains the permission object.
	*/
	public boolean hasPermission(User user){
		List<Permission> perms = user.getPermissions();
		int index = perms.indexOf((Permission)(this));
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
	public boolean evaluate(User user){
		if(this.hasPermission(user)) return true;
		else return false;
	}// /evaluate
}// /AtomicPermission
