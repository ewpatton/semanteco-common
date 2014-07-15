package edu.rpi.tw.escience.semanteco;

public interface UserStore{
	// should return TRUE on successful write, or FALSE if there is some error
	boolean writeUser(User user);
	
	// identifier should be some way of uniquely identifying a User
	// ** this may not be a username, as two separate authorities may each have a user with the same name
	User readUser(String identifier);
}