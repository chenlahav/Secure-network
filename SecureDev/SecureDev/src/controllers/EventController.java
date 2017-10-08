package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.server.Dispatcher;
import Repository.EventRepository;
import model.Event;
 

public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EventController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventRepository er = new EventRepository();
		List<Event> allevents = er.getAllEvents();
		RequestDispatcher rd = null;
 		
		if (!allevents.equals(null)) 
		{
			rd = request.getRequestDispatcher("/Events.jsp");
			request.setAttribute("allevents", allevents);
		} 
		else
		{
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventRepository pr = new EventRepository();
		List<Event> allevents = pr.getAllEvents();
		request.setAttribute("allevents", allevents);
		request.getRequestDispatcher("/Events.jsp").forward(request, response);
	}
}
