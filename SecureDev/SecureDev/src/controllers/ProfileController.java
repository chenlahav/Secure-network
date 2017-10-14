package controllers;
import java.io.IOException;
import java.util.ArrayList;
//import java.nio.file.DirectoryNotEmptyException;
//import java.nio.file.Files;
//import java.nio.file.NoSuchFileException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;

import Repository.EventRepository;
import Repository.UserRepository;
import model.Event;
import model.User;
import utils.Xss;
import utils.validator;

public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileController() {
		super();
	}
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		UserRepository ur = new UserRepository();
		User userToEdit = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(userToEdit == null){
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String bdate = request.getParameter("bdate");
		String telephone = request.getParameter("telephone");
		
		if(inputvalidation(firstname,lastname,username,email,bdate,telephone) == false){
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
		
		userToEdit.setFirstName(Xss.cleanString("firstname",firstname));
		userToEdit.setLastName(Xss.cleanString("last name",lastname));
		userToEdit.setUsername(Xss.cleanString("username",username));
		userToEdit.setEmail(Xss.cleanString("email",email));
		userToEdit.setBday(Xss.cleanString("dbate",bdate));
		userToEdit.setTelephone(Xss.cleanString("telephone",telephone));
	  
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
			request.setAttribute("error", "The operation failed");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd = null;

	if(request.getSession().getAttribute("userID") == null){
		request.setAttribute("error", "Not in a session");
		rd = request.getRequestDispatcher("/error.jsp");
		rd.forward(request, response);
		return;
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
	
	public boolean inputvalidation(String firstName, String lastName, String username, String email, String bdate, String telephone){
		
		if(!validator.validateFirstname(firstName)) return false;
		
		if(!validator.validateLastname(lastName)) return false;
		
		if(!validator.validateUsername(username)) return false;
		
		if(!validator.validateEmail(email)) return false;
		
		if(!validator.validateBirthDate(bdate)) return false;
		
		if(!validator.validateTelephone(telephone)) return false;
		
		return true;
	}
}