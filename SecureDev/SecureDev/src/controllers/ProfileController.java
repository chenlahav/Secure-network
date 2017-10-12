package controllers;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Repository.UserRepository;
import model.User;

public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileController() {
		super();
	}
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		UserRepository ur = new UserRepository();
		User userToEdit = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(userToEdit == null){
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "not in session");
		}
		//String id= userToEdit.getId();
		String first_name = request.getParameter("first name");
		String last_name = request.getParameter("last name");
		//String username = request.getParameter("username");
		String email = request.getParameter("email");
		String bdate = request.getParameter("bdate");
		String gender = request.getParameter("gender");
		if(first_name!=null)
			userToEdit.setFirstName(first_name);
		if(last_name!=null)
			userToEdit.setLastName(last_name);
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
	
	request.getRequestDispatcher("/Profile.jsp").forward(request, response);
}
}
