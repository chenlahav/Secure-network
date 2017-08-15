package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class Creator {

		public String createUser(String id, String first_name, String last_name, String username, String password, String email, String gender, String bdate){
			
			Connection c = null;
			Statement stmt = null;
			try{
				Class.forName("org.sqlite.JDBC");
				
				//~~~~~~~~~~~~~~~Change The path of the SQLite database.~~~~~~~~~~~~~~~~~~~~~~
				
				c = DriverManager.getConnection("jdbc:sqlite:C:/Users/CHEN/Documents/GitHub/Secure-network/SecureDev/SecureDev/resource/db.sqlite");
				stmt = c.createStatement();
				String sql="INSERT INTO tblusers (id,username,password,firstName,lastName,email,birthOfDate,gender) VALUES ('"+id+"','"+username+"','"+password+"','"+first_name+"','"+last_name+"','"+email+"','"+bdate+"','"+gender+"');";

				//String sql = "select * from tblusers where username='" + username + "' and password='" + password + "';";
				
				int rs = stmt.executeUpdate(sql);
					
			  if (rs!=0) {
				return "success";
			  } 
			  else
			  {
				return "failure";
			  }
		   }
			catch(Exception e)
			{
				return "SQL ERROR";
			}
			
		}

}
