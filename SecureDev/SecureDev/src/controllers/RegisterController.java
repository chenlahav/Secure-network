package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

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
		String first_name = request.getParameter("first name");
		String last_name = request.getParameter("last name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hashedPassword = authenticator.toSha256(password);
		String email = request.getParameter("email");
		String bdate = request.getParameter("bdate");
		String gender = request.getParameter("gender");
		String telephone = request.getParameter("telephone");
		Part file = request.getPart("img");
		String contentType = file.getContentType();
		boolean isProfileImage;
		if(contentType.equals("image/jpeg")){
			isProfileImage = storeProfileImage(file,id);;
		}else{
			isProfileImage = false;
		}
		User newUser= new User(username,id,email,first_name,last_name,bdate,gender,telephone,isProfileImage);
		UserRepository rep = new UserRepository();
		String result = rep.addUser(newUser,hashedPassword);
	
		RequestDispatcher rd = null;
 		
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
			session.setAttribute("isAdmin",user.isAdmin());
			session.setAttribute("ProfilePicture",user.getPathProfilePic());
			rd = request.getRequestDispatcher("/Home.jsp");
		} 
		else
		{
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}
	private boolean storeProfileImage(Part filePart, String id){
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
}
