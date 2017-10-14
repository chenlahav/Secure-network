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
import model.User;

public class SearchController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public SearchController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		if(request.getSession().getAttribute("userID") == null){
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}

		UserRepository ur = new UserRepository();
		List<User> listOfUsers = ur.getUsersByFirstname(request.getParameter("firstname"));
		request.setAttribute("usersresults", listOfUsers);
		
		rd = request.getRequestDispatcher("/SearchResult.jsp");
		rd.forward(request, response);
		}
	
}