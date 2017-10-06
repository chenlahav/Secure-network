package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Event;

public class EventRepository extends AbstractRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4712130067123623444L;

	public EventRepository() {
		super();
	}

	public String addEvent(Event newEvent) {

		Connection c = AbstractRepository.connectionToDB();
		if (c != null) {
			try {
				Statement stmt = null;
				stmt = c.createStatement();
				String sql = "INSERT INTO tblevents (eventname, location, date, time, description, creator) VALUES ('"
						+ newEvent.getEvent_name() + "','" + newEvent.getLocation() + "','" + newEvent.getDate() + "','"
						+ newEvent.getTime() + "','" + newEvent.getDescription() + "','" + newEvent.getCreator()
						+ "');";
				int rs = stmt.executeUpdate(sql);
				if (rs != 0) {
					return "success";
				} else {
					return "failed";
				}
			} catch (Exception e) {
				return "SQL ERROR";
			}
		}

		else {
			return "Connection ERROR";
		}
	}

	public String editEvent(Event event) {

		Connection c = AbstractRepository.connectionToDB();
		if (c != null) {
			try {
				Statement stmt = null;
				stmt = c.createStatement();
				String sql = "UPDATE tblevents SET eventname="+event.getEvent_name()+", location="+event.getLocation()+", date="+event.getDate()+", time="+event.getTime()+", description="+event.getDescription()+"WHERE id="+event.getId()+";";
				int rs = stmt.executeUpdate(sql);
				if (rs != 0) {
					return "success";
				} else {
					return "failed";
				}
			} catch (Exception e) {
				return "SQL ERROR";
			}
		}

		else {
			return "Connection ERROR";
		}
	}
	
	public String deleteEvent(Event event) {

		Connection c = AbstractRepository.connectionToDB();
		if (c != null) {
			try {
				Statement stmt = null;
				stmt = c.createStatement();
				String sql = "DELETE FROM tblevents WHERE id="+event.getId()+";";
				int rs = stmt.executeUpdate(sql);
				if (rs != 0) {
					return "success";
				} else {
					return "failed";
				}
			} catch (Exception e) {
				return "SQL ERROR";
			}
		}

		else {
			return "Connection ERROR";
		}
	}
	
	public Event getEvent(int id){
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblevents WHERE id = "+id+";";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					Event eventRequested = new Event(id, rs.getString("event_name"), rs.getDate("date"), rs.getTime("time"), rs.getString("description"), rs.getString("location"), rs.getString("creator"));
					return eventRequested;
				  } 
			   }catch (Exception e){
					e.printStackTrace();
					return null;
			   }
		}
		return null;
	}
	
	public List<Event> getAllEvents(){
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblevents;";
				ResultSet rs = stmt.executeQuery(sql);
				List<Event> allEvents = new ArrayList<>();
				while (rs.next()) {
					Event eventRequested = new Event(rs.getInt("id"), rs.getString("event_name"), rs.getDate("date"), rs.getTime("time"), rs.getString("description"), rs.getString("location"), rs.getString("creator"));
					allEvents.add(eventRequested);
				  } 
				return allEvents;
			   }catch (Exception e){
					e.printStackTrace();
					return new ArrayList<Event>();
			   }
		}
		return new ArrayList<Event>();
	}
	
	public List<Event> getAllEventsByUserID(String id){
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblevents WHERE creatorid = "+id+";";
				ResultSet rs = stmt.executeQuery(sql);
				List<Event> allEvents = new ArrayList<>();
				while (rs.next()) {
					Event eventRequested = new Event(rs.getInt("id"), rs.getString("event_name"), rs.getDate("date"), rs.getTime("time"), rs.getString("description"), rs.getString("location"), rs.getString("creator"));
					allEvents.add(eventRequested);
				  } 
				return allEvents;
			   }catch (Exception e){
					e.printStackTrace();
					return new ArrayList<Event>();
			   }
		}
		return new ArrayList<Event>();
	}
}
