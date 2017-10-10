package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Repository.EventRepository;
import Repository.UserRepository;
import Repository.PostRepository;
import model.Event;
import model.User;
import model.Post;

public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AdminController() {
		super();
	}	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventRepository er = new EventRepository();
		UserRepository ur = new UserRepository();
		PostRepository pr = new PostRepository();
		
		User creator = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(creator == null){
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "not in session");
		}
	/*	if(creator.IsAdmin() == null){
		request.getRequestDispatcher("/error.jsp");
		request.setAttribute("error", "You access this page");
	}*/
		List<Post> allposts = pr.getAllPosts();
		List<Event> allevents = er.getAllEvents();
		List<User> allusers = ur.getAllUsers();
		request.setAttribute("allevents", allevents);
		request.setAttribute("allposts", allposts);
		request.setAttribute("allusers", allusers);
		request.getRequestDispatcher("/Admin.jsp").forward(request, response);
	}

}
