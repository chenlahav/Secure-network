package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		RequestDispatcher rd = null;
		if(inputvalidation(id, first_name, last_name, username, password, email, bdate, gender, telephone) == false){
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
			}
		
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
	
	
	public boolean inputvalidation(String id ,String firstName,String lastName, String userName,String password,String email,String bday, String gender, String telephone){
		Pattern p;
		Matcher m;
		boolean b;
		
		//check Id field
		p = Pattern.compile("^[0-9]{9}$");
		m = p.matcher(id);
		b = m.matches();
		if(b==false) return false;
		
		//check first name field
		p = Pattern.compile("^[a-zA-Z]{2,20}$");
		m = p.matcher(firstName);
		b = m.matches();
		if(b==false) return false;
		
		//check last name field
		p = Pattern.compile("^[a-zA-Z]{2,20}$");
		m = p.matcher(lastName);
		b = m.matches();
		if(b==false) return false;
		
		//check last user name field
		p = Pattern.compile("^[a-zA-Z0-9]{2,12}$");
		m = p.matcher(userName);
		b = m.matches();
		if(b==false) return false;
		
		//check password field	
		p = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,16}");
		m = p.matcher(password);
		b = m.matches();
		if(b==false) return false;
		
		//check email field	
		p = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
		m = p.matcher(email);
		b = m.matches();
		if(b==false) return false;
		
		//check birth date field	
		p = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
		m = p.matcher(bday);
		b = m.matches();
		
		//check gender field	
		//
		if(!Objects.equals(gender, "Male")&&(!Objects.equals(gender, "Female"))) return false;
		
		//check telephone field	
		p = Pattern.compile("\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})");
		m = p.matcher(telephone);
		b = m.matches();
		if(b==false) return false;
		
		return true;
	}
}
