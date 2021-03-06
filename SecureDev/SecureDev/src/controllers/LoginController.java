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
import utils.Xss;
  
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
		String result = authenticator.authenticate(Xss.cleanString("username", username), password);
		
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
			session.setAttribute("telephone", user.getTelephone());
			session.setAttribute("isAdmin",user.isAdmin());
			session.setAttribute("ProfilePicture",user.getPathProfilePic());
			
			//same origin policy
			response.setHeader("Set-Cookie", "JSESSIONID=" + session.getId() + "; HttpOnly; SameSite=strict");
			
			rd = request.getRequestDispatcher("/Home.jsp");
		} 
		else
		{
			request.setAttribute("error", "wrong username or password");
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}
 
}