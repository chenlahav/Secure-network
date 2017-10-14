package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Repository.UserRepository;
import database.Database;
import model.Authenticator;
import model.User;
import utils.Xss;
import utils.validator;


@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Authenticator authenticator = new Authenticator();
		try {
			Database.getInstance().init();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String id= request.getParameter("id");
		String first_name = request.getParameter("firstname");
		String last_name = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPassword = authenticator.toSha256(password);
		String email = request.getParameter("email");
		String bdate = request.getParameter("bdate");
		String gender = request.getParameter("gender");
		String telephone = request.getParameter("telephone");
		
		RequestDispatcher rd = null;
		if(inputvalidation(id, first_name, last_name, username, email, bdate, gender, telephone) == false){
			request.setAttribute("error", "Invalid input");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
			}
		
		Part file = request.getPart("img");
		String contentType = file.getContentType();
		boolean isProfileImage;
		if(contentType.equals("image/jpeg")){
			isProfileImage = storeProfileImage(file,id);
		}else{
			isProfileImage = false;
		}
		
		username = Xss.cleanString("username", username);
		id = Xss.cleanString("userId", id);
		email = Xss.cleanString("email", email);
		first_name = Xss.cleanString("firstName", first_name);
		last_name = Xss.cleanString("lastName", last_name);
		bdate = Xss.cleanString("bdate", bdate);
		telephone = Xss.cleanString("telephone", telephone);
		
		User newUser= new User(username,id,email,first_name,last_name,bdate,gender,telephone,isProfileImage);
		UserRepository rep = new UserRepository();
		String result = rep.addUser(newUser,hashedPassword);
	
 		
		if (result.equals("success")) 
		{
			UserRepository ur = new UserRepository();
			User user = ur.getUserByUsername(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userID", user.getId());
			session.setAttribute("firstname", user.getFirstName());
			session.setAttribute("lastname", user.getLastName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("bdate", user.getBday());
			session.setAttribute("gender", user.getGender());
			session.setAttribute("telephone", user.getTelephone());
			session.setAttribute("isAdmin",user.isAdmin());
			session.setAttribute("ProfilePicture",user.getPathProfilePic());
			rd = request.getRequestDispatcher("/Home.jsp");
			rd.forward(request, response);
		} 
		else
		{
			request.setAttribute("error", "The operation failed");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
	}
	
	public boolean storeProfileImage(Part filePart, String id){
		try{
		 InputStream  inputStream = filePart.getInputStream();
         byte[] buffer = new byte[inputStream.available()];
         inputStream.read(buffer);
         String path = "WebContent/Images/ProfileImage/"+id+".jpg";
         File targetFile = new File(path);
         OutputStream outStream = new FileOutputStream(targetFile);
         outStream.write(buffer);
         outStream.close();
         return true;
		}catch(Exception e){
		return false;
		}
	}
	
	public boolean inputvalidation(String id ,String firstName,String lastName, String userName,String email,String bday, String gender, String telephone){
		
		if(!validator.validateUserId(id)) return false;
		
		if(!validator.validateFirstname(firstName)) return false;
		
		if(!validator.validateLastname(lastName)) return false;
		
		if(!validator.validateUsername(userName)) return false;
		
		if(!validator.validateEmail(email)) return false;
		
		if(!validator.validateBirthDate(bday)) return false;
		
		if(!validator.validateTelephone(telephone)) return false;
		
		//check gender field	
		if(!Objects.equals(gender, "Male")&&(!Objects.equals(gender, "Female"))) return false;
		
		return true;
	}
}
