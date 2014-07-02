package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;

public interface Permission{
	// How do these sound?
	public enum Level{ 
		NONE, READ_ONLY, WRITE_ONLY, READ_WRITE, ADMIN;
	}

	String getPermissionName();
	Level getPermissionLevel(String pName);
	void setPermissionLevel(String pName);
}// Permission