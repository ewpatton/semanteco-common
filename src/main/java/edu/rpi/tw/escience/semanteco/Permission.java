package edu.rpi.tw.escience.semanteco;

import java.net.URI;
import java.util.List;

public interface Permission{
	// How do these sound?
	enum Level { NONE, VIEW, WRITE, ADMIN }

	String getPermissionName();
	Level getPermissionLevel();
	void setPermissionLevel(Level pName);

	/**
	 * Allows permissions to implement rules as a function of users so that
	 * explicit &lt;user, permission&gt; pairs do not need to be set by an
	 * administrator.
	 * @param user A User object to evaluate for permission
	 * @return true if the user passes the test this permission requires,
	 * otherwise false.
	 */
	boolean evaluate(User user);
}// Permission
