package controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Repository.CommentsRepository;
import Repository.UserRepository;
import model.Comment;
import model.User;
import utils.Xss;
import utils.validator;

public class CommentController  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		UserRepository ur = new UserRepository();
		User creator = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(creator != null){
			String content = request.getParameter("content");
			if(inputvalidation(content) == false){
				request.setAttribute("error", "Invalid comment");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
				return;
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String strnow = dtf.format(now);
			String date = strnow.substring(0, strnow.indexOf(" "));
			String time = strnow.substring(strnow.indexOf(" ")+1);
			String idS = (String)request.getParameter("postid");
			int postId = Integer.parseInt(idS);
			Comment newComment = new Comment(time, date,Xss.cleanString("content", content) , postId, creator);

			CommentsRepository cr = new CommentsRepository();
			String result = cr.addComment(newComment);
			
			if (result.equals("success")) {
				PostController pc = new PostController();
				pc.doGet(request, response);
			}else{
				request.setAttribute("error", "The operation failed");
				rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		}
		else{
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
		
	}
	
	public boolean inputvalidation(String content){
		
		if(!validator.validateText(content)) return false;

		return true;
	}
}
