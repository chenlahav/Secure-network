package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Repository.EventRepository;
import Repository.UserRepository;
import model.Event;
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
}
