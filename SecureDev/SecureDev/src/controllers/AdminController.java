package controllers;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EventRepository er = new EventRepository();
		UserRepository ur = new UserRepository();
		PostRepository pr = new PostRepository();

		User creator = ur.getUserById((String) request.getSession().getAttribute("userID"));
		if (creator.isAdmin() == false) {
			request.getRequestDispatcher("/error.jsp");
			return;
		}

		if (creator == null) {
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "not in session");
		} else {
			String uIdToDelete = request.getParameter("hiddenu");
			String pIdToDelete = request.getParameter("hiddenp");
			String eIdToDelete = request.getParameter("hiddene");
			String uToAdmin = request.getParameter("hiddenAdmin");
			String result = null;
			if (uToAdmin != null) {
				User userToAdmin = ur.getUserById(uToAdmin);
				if(userToAdmin.isAdmin()){
					userToAdmin.setAdmin(false);
					result = ur.editUser(userToAdmin);
				}else{
					userToAdmin.setAdmin(true);
					result = ur.editUser(userToAdmin);
				}
				
			}
			if (uIdToDelete != null) {
				User UserToDelete = ur.getUserById(uIdToDelete);
				result = ur.deleteUser(UserToDelete);
				if(UserToDelete.getisProfileImage()){		//remove the current image
				try {
					Path pathfile = Paths.get("WebContent/Images/ProfileImage/"+UserToDelete.getId()+".jpg");
				    Files.delete(pathfile);
				} catch (NoSuchFileException x) {
				    System.err.format("%s: no such" + " file or directory%n", "WebContent/Images/ProfileImage/"+UserToDelete.getId()+".jpg" );
				} catch (DirectoryNotEmptyException x) {
				    System.err.format("%s not empty%n", "WebContent/Images/ProfileImage/"+UserToDelete.getId()+".jpg");
				} catch (IOException x) {
				    // File permission problems are caught here.
				    System.err.println(x);
				}
			}
			}
			if (eIdToDelete != null) {
				int id = Integer.parseInt(eIdToDelete);
				Event EventToDelete = er.getEvent(id);
				result = er.deleteEvent(EventToDelete);
			}
			if (pIdToDelete != null) {
				int id = Integer.parseInt(pIdToDelete);
				Post PostToDelete = pr.getPost(id);
				result = pr.deletePost(PostToDelete);
			}
			if (result.equals(null))
				result = "error";
			if (result.equals("success")) {
				doGet(request, response);
			} else {
				request.getRequestDispatcher("/error.jsp");
				request.setAttribute("error", "error while adding event");
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EventRepository er = new EventRepository();
		UserRepository ur = new UserRepository();
		PostRepository pr = new PostRepository();

		User creator = ur.getUserById((String) request.getSession().getAttribute("userID"));
		if (creator == null) {
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "not in session");

		}
		if (creator.isAdmin() == false) {
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "You access this page");
			return;
		}

		List<Post> allposts = pr.getAllPosts();
		List<Event> allevents = er.getAllEvents();
		List<User> allusers = ur.getAllUsers();
		request.setAttribute("allevents", allevents);
		request.setAttribute("allposts", allposts);
		request.setAttribute("allusers", allusers);
		request.getRequestDispatcher("/Admin.jsp").forward(request, response);
	}

}
