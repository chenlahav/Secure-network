package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Repository.EventRepository;
import Repository.PostRepository;
import Repository.UserRepository;
import model.Post;
import model.User;
import model.Event;

public class InformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InformationController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserRepository ur = new UserRepository();
		User nowConnected = ur.getUserById((String)request.getSession().getAttribute("userID"));
		PostRepository pr = new PostRepository();
		List<Post> allposts = pr.getAllPosts();
		request.setAttribute("allposts", allposts);
		EventRepository cr = new EventRepository();
		List<Event> allEvents = cr.getAllEvents();
		request.setAttribute("allevents",allEvents);
		request.setAttribute("userConnected", nowConnected);
		
		request.getRequestDispatcher("/Information.jsp").forward(request, response);
	}
}
