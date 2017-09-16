package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		
		Connection c = null;
		Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			
			//~~~~~~~~~~~~~~~Change The path of the SQLite database.~~~~~~~~~~~~~~~~~~~~~~
			c = DriverManager.getConnection("jdbc:sqlite:C:/Users/snirkol/Documents/GitHub rep/Secure-network/SecureDev/SecureDev/resource/db.sqlite");
			stmt = c.createStatement();
			String sql = "select * from tblusers where username='" + username + "' and password='" + password + "';";
			
			ResultSet rs = stmt.executeQuery(sql);
			
		  if (rs.next()) {
			return "success";
		  } 
		  else
		  {
			return "failure";
		  }
	   }
		catch(Exception e)
		{
			e.printStackTrace();
			return "SQL ERROR";
			
		}
	}
}