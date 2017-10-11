package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Repository.UserRepository;
import database.Database;
import model.Authenticator;
import model.User;

public class SearchController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public SearchController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Authenticator authenticator = new Authenticator();
		try {
			Database.getInstance().init();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		UserRepository ur = new UserRepository();
		List<User> listOfUsers = ur.getUsersByFirstname(request.getParameter("firstname"));
		request.setAttribute("usersresults", listOfUsers);
		
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/SearchResult.jsp");
		rd.forward(request, response);
		}
	
}