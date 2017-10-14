package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Repository.UserRepository;
import model.User;

public class AllUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AllUsersController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		if(request.getSession().getAttribute("userID")==null){
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
		
		UserRepository ur = new UserRepository();
		List<User> allusers = ur.getAllUsers();
		request.setAttribute("allusers", allusers);
		
		request.getRequestDispatcher("/AllUsers.jsp").forward(request, response);
	}
}



