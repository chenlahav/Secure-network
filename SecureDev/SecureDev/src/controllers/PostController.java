package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.Authenticator;
import model.Post;
import model.User;
import sun.rmi.server.Dispatcher;
import Repository.PostRepository;
 

public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PostController() {
		super();
	}
	
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Function that handles the POST request that comes from login.jsp page.
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	*/
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PostRepository postrepository = new PostRepository();
		//List<Post> result = postrepository.getAllPosts();
		//Post result = postrepository.getPost(2);
		List<Post> result = postrepository.getAllPosts();
		
		//request.setAttribute("allposts", "hello world");
		RequestDispatcher rd = null;
		//rd = request.getRequestDispatcher("/Forum.jsp");
 		
		if (!result.equals(null)) 
		{
			rd = request.getRequestDispatcher("/Forum.jsp");
			request.setAttribute("allpost", result);
		} 
		else
		{
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PostRepository pr = new PostRepository();
		List<Post> allposts = pr.getAllPosts();
		request.setAttribute("allposts", allposts);
		request.getRequestDispatcher("/Forum.jsp").forward(request, response);
	}
}
