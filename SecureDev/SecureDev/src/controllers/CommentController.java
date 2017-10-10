package controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Repository.CommentsRepository;
import Repository.UserRepository;
import model.Comment;
import model.User;

public class CommentController  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserRepository ur = new UserRepository();
		User creator = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(creator != null){
			String content = request.getParameter("content");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String strnow = dtf.format(now);
			String date = strnow.substring(0, strnow.indexOf(" "));
			String time = strnow.substring(strnow.indexOf(" ")+1);
			int postId = Integer.parseInt((String) request.getAttribute("postId"));
			Comment newComment = new Comment(time, date, content, postId, creator);
	 		
			CommentsRepository cr = new CommentsRepository();
			String result = cr.addComment(newComment);
			
			if (result.equals("success")) {
				PostController pc = new PostController();
				pc.doGet(request, response);
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
}
