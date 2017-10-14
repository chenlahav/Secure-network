package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Repository.EventRepository;
import Repository.UserRepository;
import model.Event;
import model.User;
import utils.Xss;
import utils.validator;
 

public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EventController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		EventRepository er = new EventRepository();
		UserRepository ur = new UserRepository();
		
		User creator = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(creator == null){
			request.setAttribute("error", "Not in a session");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
		else{
		String event_name = request.getParameter("event_name");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		
		if(inputvalidation(event_name, date, time, description, location) == false){
			request.setAttribute("error", "Invalid event");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			return;
		}
		
		Event newEvent = new Event(Xss.cleanString("event_name", event_name), Xss.cleanString("date", date), Xss.cleanString("time", time), Xss.cleanString("description", description), Xss.cleanString("location", location), creator);

		String result = er.addEvent(newEvent);
		
		if(result.equals("success")){
			doGet(request, response);
		}else{
			request.setAttribute("error", "The operation failed");
			rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
			}
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
		
		EventRepository pr = new EventRepository();
		List<Event> allevents = pr.getAllEvents();
		request.setAttribute("allevents", allevents);
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
		request.getRequestDispatcher("/Events.jsp").forward(request, response);
	}
	
	public boolean inputvalidation(String eventname, String date, String time, String description, String location){
		
		if(!validator.validateText(eventname)) return false;
		if(!validator.validateBirthDate(date)) return false;
		if(!validator.validateTime(time)) return false;
		if(!validator.validateText(description)) return false;
		if(!validator.validateText(location)) return false;
		return true;
	}
}