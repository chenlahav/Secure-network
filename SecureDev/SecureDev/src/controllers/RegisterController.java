package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.Creator;
import model.User;
 
import sun.text.normalizer.ICUBinary.Authenticate;

public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		String id= request.getParameter("id");
		String first_name = request.getParameter("first name");
		String last_name = request.getParameter("last name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String bdate = request.getParameter("bdate");
		String gender = request.getParameter("gender");
		
		RequestDispatcher rd = null;
 
		Creator creator=new Creator();
		String result = creator.createUser(id, first_name, last_name, username, password, email, gender, bdate);
		
		if (result.equals("success")) 
		{
			rd = request.getRequestDispatcher("/success.jsp");
			User user = new User(username, password);
			request.setAttribute("user", user);
		} 
		else
		{
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}
	
}
