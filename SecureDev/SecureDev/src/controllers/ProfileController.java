package controllers;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import model.Authenticator;
import model.User;
import Repository.UserRepository;
 
import sun.text.normalizer.ICUBinary.Authenticate;

public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void postp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/Profile.jsp");
		String u = "Hilla";
		request.setAttribute("user", u);

		rd.forward(request, response);
	}
 
}