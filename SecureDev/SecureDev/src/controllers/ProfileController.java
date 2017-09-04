package controllers;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import model.Authenticator;
import model.Creator;
import model.User;
 
import sun.text.normalizer.ICUBinary.Authenticate;

public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void postp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection c = DriverManager.getConnection("jdbc:sqlite:C:/Users/Owner/Documents/GitHub/Secure-network/SecureDev/SecureDev/resource/db.sqlite");
		Statement stmt = c.createStatement();
		String sql="INSERT INTO tblusers (id,username,password,firstName,lastName,email,birthOfDate,gender) VALUES ('"+id+"','"+username+"','"+password+"','"+first_name+"','"+last_name+"','"+email+"','"+bdate+"','"+gender+"');";
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/Profile.jsp");
		String u = getUsername();
		request.setAttribute("user", u);

		rd.forward(request, response);
	}
 
}