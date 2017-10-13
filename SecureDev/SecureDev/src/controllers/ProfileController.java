package controllers;
import java.io.IOException;
import java.util.ArrayList;
//import java.nio.file.DirectoryNotEmptyException;
//import java.nio.file.Files;
//import java.nio.file.NoSuchFileException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;

import Repository.EventRepository;
import Repository.UserRepository;
import model.Event;
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
		userToEdit.setFirstName(request.getParameter("first name"));
		userToEdit.setLastName(request.getParameter("last name"));
		userToEdit.setUsername(request.getParameter("username"));
		userToEdit.setEmail(request.getParameter("email"));
		userToEdit.setBday(request.getParameter("bdate"));
		userToEdit.setTelephone(request.getParameter("telephone"));
		
//		Part file = request.getPart("img");
//		String contentType = file.getContentType();
//		RegisterController rc = new RegisterController();
//		if(contentType.equals("image/jpeg")){
//			if(userToEdit.getisProfileImage()){		//remove the current image
//				try {
//					Path pathfile = Paths.get("WebContent/Images/ProfileImage/"+userToEdit.getId()+".jpg");
//				    Files.delete(pathfile);
//				} catch (NoSuchFileException x) {
//				    System.err.format("%s: no such" + " file or directory%n", "WebContent/Images/ProfileImage/"+userToEdit.getId()+".jpg" );
//				} catch (DirectoryNotEmptyException x) {
//				    System.err.format("%s not empty%n", "WebContent/Images/ProfileImage/"+userToEdit.getId()+".jpg");
//				} catch (IOException x) {
//				    // File permission problems are caught here.
//				    System.err.println(x);
//				}
//			}
//			userToEdit.setProfileImage(rc.storeProfileImage(file, userToEdit.getId()));
//		}
		
		String result = ur.editUser(userToEdit);		
		if(result.equals("success")){
			request.getSession().setAttribute("username", userToEdit.getUsername());
			request.getSession().setAttribute("firstname", userToEdit.getFirstName());
			request.getSession().setAttribute("lastname", userToEdit.getLastName());
			request.getSession().setAttribute("email", userToEdit.getEmail());
			request.getSession().setAttribute("bdate", userToEdit.getBday());
			request.getSession().setAttribute("telephone", userToEdit.getTelephone());
			
			doGet(request, response);
		}else{
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error","error while edit user");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UserRepository ur = new UserRepository();
	User creator = ur.getUserById((String)request.getSession().getAttribute("userID"));
	if(creator == null){
		request.getRequestDispatcher("/error.jsp");
		request.setAttribute("error", "not in session");
	}
	
	EventRepository er = new EventRepository();
	List<Event> events = er.getLatestEvents();
	if(events.size()!=0){
		if(events.size()<=3){
			request.setAttribute("latestEvents", events);
		}else{
			List<Event> treeEvents = new ArrayList<>();
			for(int i=0; i<3;i++){
				treeEvents.add(events.get(i));
			}
			request.setAttribute("latestEvents", treeEvents);
		}
	}
	request.getRequestDispatcher("/Profile.jsp").forward(request, response);
}
}
