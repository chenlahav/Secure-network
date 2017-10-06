package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Repository.UserRepository;

import java.sql.*;


public class Authenticator {
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Authentication function is responsible to perform the connection to the database.
	 * In addition, the function is responsible for the authentication process.
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */  
	
	public String authenticate(String username, String password) 
	{
		User user_to_authenticate = new User(username, password);
		UserRepository userrep = new UserRepository();
		String result = userrep.userAuthenticator(user_to_authenticate);
		return result;
	}
}