package controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comment;
import model.Post;
import model.User;
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
	 		if(inputValidator(newPost) == false) return;
	 		
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
		CommentsRepository cr = new CommentsRepository();
		List<Comment> allComments = cr.getAllComments();
		request.setAttribute("allcomments",allComments);
		
		request.getRequestDispatcher("/Forum.jsp").forward(request, response);
	}
	
	boolean inputValidator(Post post){
		Pattern p;
		Matcher m;
		boolean b;
		
		p = Pattern.compile("^[a-zA-Z'!@#$%^&*().\\s]{1,40}$");
		m = p.matcher(post.getTitle());
		b = m.matches();
		
		
		m = p.matcher(post.getContent());
		b = m.matches();
		if(b==false) return false;
		return true;
	}
}
