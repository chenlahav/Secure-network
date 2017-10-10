package controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import model.Post;
import model.User;
import Repository.PostRepository;
import Repository.UserRepository;
 

public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PostController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserRepository ur = new UserRepository();
		User author = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(author != null){
			String title= request.getParameter("title");
			String content = request.getParameter("content");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String strnow = dtf.format(now);
			String date = strnow.substring(0, strnow.indexOf(" "));
			String time = strnow.substring(strnow.indexOf(" ")+1);
			
			Post newPost = new Post(title, content, author, date, time);
	 		
			PostRepository pr = new PostRepository();
			String result = pr.addPost(newPost);
			
			if (result.equals("success")) {
				doGet(request, response);
			}else{
				request.getRequestDispatcher("/error.jsp");
				request.setAttribute("error", "error while adding post");
			}
		}
		else{
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "not in session");	
		}
		

		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PostRepository pr = new PostRepository();
		List<Post> allposts = pr.getAllPosts();
		request.setAttribute("allposts", allposts);
		request.getRequestDispatcher("/Forum.jsp").forward(request, response);
	}
}
