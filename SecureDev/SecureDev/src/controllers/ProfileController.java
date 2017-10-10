package controllers;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Repository.EventRepository;
import Repository.PostRepository;
import Repository.UserRepository;
import database.Database;
import model.Authenticator;
import model.Event;
import model.Post;
import model.User;

public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileController() {
		super();
	}
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		Authenticator authenticator = new Authenticator();
		UserRepository ur = new UserRepository();
		User userToEdit = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(userToEdit == null){
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "not in session");
		}
		String id= userToEdit.getId();
		String first_name = request.getParameter("first name");
		String last_name = request.getParameter("last name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPassword = authenticator.toSha256(password);
		String email = request.getParameter("email");
		String bdate = request.getParameter("bdate");
		String gender = request.getParameter("gender");
		if(first_name!=null)
			userToEdit.setFirstName(first_name);
		if(last_name!=null)
			userToEdit.setLastName(last_name);
		if(password!=null)
			userToEdit.setPassword(hashedPassword);
		if(email!=null)
			userToEdit.setEmail(email);
		if(bdate!=null)
			userToEdit.setBday(bdate);
		if(gender!=null)
			userToEdit.setGender(gender);
		String result=ur.editUser(userToEdit);
		if(result.equals(null))
			result="error";		
		if(result.equals("success")){
			doGet(request, response);
		}else{
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error","error while adding event");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UserRepository ur = new UserRepository();
	User creator = ur.getUserById((String)request.getSession().getAttribute("userID"));
	if(creator == null){
		request.getRequestDispatcher("/error.jsp");
		request.setAttribute("error", "not in session");
	}
/*	if(creator.IsAdmin() == null){
	request.getRequestDispatcher("/error.jsp");
	request.setAttribute("error", "You access this page");
}*/
	request.getRequestDispatcher("/Profile.jsp").forward(request, response);
}
}
