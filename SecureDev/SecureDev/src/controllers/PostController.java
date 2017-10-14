package controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comment;
import model.Post;
import model.User;
import utils.Xss;
import utils.validator;
import Repository.CommentsRepository;
import Repository.PostRepository;
import Repository.UserRepository;
 

public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PostController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		UserRepository ur = new UserRepository();
		User author = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(author != null){
			String title= request.getParameter("title");
			String content = request.getParameter("content");
			
	 		if(inputvalidation(title, content) == false){
				request.setAttribute("error", "Invalid post");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
				return;
	 		}
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String strnow = dtf.format(now);
			String date = strnow.substring(0, strnow.indexOf(" "));
			String time = strnow.substring(strnow.indexOf(" ")+1);
			
			Post newPost = new Post(Xss.cleanString("title", title), Xss.cleanString("content", content), author, date, time);
	 		
			PostRepository pr = new PostRepository();
			String result = pr.addPost(newPost);
			
			if (result.equals("success")) {
				doGet(request, response);
			}else{
				request.setAttribute("error", "The operation failed");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
				return;
			}
		}
		else{
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		

		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;

		if(request.getSession().getAttribute("userID") == null){
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
		
		PostRepository pr = new PostRepository();
		List<Post> allposts = pr.getAllPosts();
		request.setAttribute("allposts", allposts);
		CommentsRepository cr = new CommentsRepository();
		List<Comment> allComments = cr.getAllComments();
		request.setAttribute("allcomments",allComments);
		
		request.getRequestDispatcher("/Forum.jsp").forward(request, response);
	}
	
	public boolean inputvalidation(String title, String content){
			
			if(!validator.validateText(title)) return false;
			if(!validator.validateText(content)) return false;
			return true;
	}
}
