package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Repository.EventRepository;
import Repository.UserRepository;
import model.Event;
import model.Post;
import model.User;
 

public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EventController() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventRepository er = new EventRepository();
		UserRepository ur = new UserRepository();
		
		User creator = ur.getUserById((String)request.getSession().getAttribute("userID"));
		if(creator == null){
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "not in session");
		}
		else{
		String event_name = request.getParameter("event_name");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		Event newEvent = new Event(event_name, date, time, description, location, creator);
		if(inputValidator(newEvent) == false){
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "error while adding post");
			return;
		}
		
		String result = er.addEvent(newEvent);
		
		if(result.equals("success")){
			doGet(request, response);
		}else{
			request.getRequestDispatcher("/error.jsp");
			request.setAttribute("error","error while adding event");
		}
		//rd.forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	

	boolean inputValidator(Event event){
		Pattern p;
		Matcher m;
		boolean b;
		
		//check event name 
		p = Pattern.compile("^[a-zA-Z'!@#$%^&*().\\s]{1,20}$");
		m = p.matcher(event.getEvent_name());
		b = m.matches();
		if(b==false) return false;
		
		//check event date
		p = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
		m = p.matcher(event.getDate());
		b = m.matches();
		if(b==false) return false;
		
		//check event time
		p = Pattern.compile("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");
		m = p.matcher(event.getTime());
		b = m.matches();
		if(b==false) return false;
				
		//check description
		p = Pattern.compile("^[a-zA-Z'!@#$%^&*().\\s]{1,40}$");
		m = p.matcher(event.getDescription());
		b = m.matches();
		if(b==false) return false;
			
		//check location
		p = Pattern.compile("^[a-zA-Z'!@#$%^&*().\\s]{1,20}$");
		m = p.matcher(event.getLocation());
		b = m.matches();
		if(b==false) return false;
				
		return true;
	}	
}
