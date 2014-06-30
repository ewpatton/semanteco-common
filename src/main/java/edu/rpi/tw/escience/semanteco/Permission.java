package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;

public interface Permission{
	// How do these sound?
	private enum Level{ NONE, VIEW, WRITE, ADMIN }

	String getPermissionName();
	enum getPermissionLevel();
	void setPermissionLevel(String pName);
}// Permission