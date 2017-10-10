package model;

import Repository.UserRepository;

public class Authenticator {
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Authentication function is responsible to perform the connection to the database.
	 * In addition, the function is responsible for the authentication process.
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */  
	
	public String authenticate(String username, String password) 
	{
		//String hashedPassword = toSha256(password);
		User user_to_authenticate = new User(username, /*hashedPassword*/password);
		UserRepository userrep = new UserRepository();
		String result = userrep.userAuthenticator(user_to_authenticate);
		return result;
	}
	
	public String toSha256(String password) {
		String hashed = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);   
		return hashed;
	}
}