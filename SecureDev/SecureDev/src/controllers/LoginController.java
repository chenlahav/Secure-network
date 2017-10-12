package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Repository.UserRepository;
import database.Database;
import model.Authenticator;
import model.User;
  
public class LoginController extends HttpServlet {
 
	private static final long serialVersionUID = 407056058428113610L;

	public LoginController() {
		super();
	}
	
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Function that handles the POST request that comes from login.jsp page.
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	*/
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		RequestDispatcher rd = null;
		try {
			Database.getInstance().init();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		Authenticator authenticator = new Authenticator();
		String result = authenticator.authenticate(username, password);
		
		if (result.equals("success")) 
		{
			UserRepository ur = new UserRepository();
			User user = ur.getUserByUsername(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userID", user.getId());
			session.setAttribute("firstname", user.getFirstName());
			session.setAttribute("lastname", user.getLastName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("bdate", user.getBday());
			session.setAttribute("gender", user.getGender());
			session.setAttribute("isAdmin",user.isAdmin());
			session.setAttribute("ProfilePicture",user.getPathProfilePic());
			rd = request.getRequestDispatcher("/Home.jsp");
		} 
		else
		{
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}
 
}