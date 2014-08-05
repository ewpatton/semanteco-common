package edu.rpi.tw.escience.semanteco;

public interface UserStore{
	// Writes a new User to the store (eg, JSON file, database, etc)
	// Returns TRUE on successful write, or FALSE if there is some error
	boolean writeUser(User user);
	
	// Creates a User object from a stored User uniquely identified by the identifier
	// * Identifier may not necessarily be a username,
	//   as two separate authorities may each have a user with the same name
	User readUser(String identifier);
}