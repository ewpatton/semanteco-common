package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;

public class TestPermission implements Permission{
	public enum Level{ 
		NONE, READ_ONLY, WRITE_ONLY, READ_WRITE, ADMIN;
	}
	private String pName;
	private Level pLevel;
	private URI pURI;

	public TestPermission(){
		this.pName = "null";
		this.pLevel = NONE;
		this.pURI = null;
	}
	public TestPermission(String theName){
		this.pName = theName;
		this.pLevel = NONE;
		String tempURI = "http://" + theName + "/NONE";
		this.pURI = new URI(tempURI);
	}
	public TestPermission(String theName, Level theLevel){
		this.pName = theName;
		this.pLevel = theLevel;
		String tempURI = "http://" + theName + "/" + theLevel;
		this.pURI = new URI(tempURI);
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

	public boolean hasPermission(TestUser user){
		List<TestPermission> perms = user.getPermissions();
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
	boolean evaluate(TestUser user){
		if(this.hasPermission(user)) return true;
		else return false;
	}
}// Permission
