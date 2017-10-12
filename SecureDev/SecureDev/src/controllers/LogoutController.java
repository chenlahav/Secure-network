package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;

public class LogoutController {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		Database.getInstance().closeConnection();
		response.sendRedirect("/login.jsp");
	}

}
