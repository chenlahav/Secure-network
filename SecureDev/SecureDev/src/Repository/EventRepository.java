package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
				
				PreparedStatement stmt ;
				String sql= "INSERT INTO tblevents (eventname, location, date, time, description, creator) VALUES (?,?,?,?,?,?);";
				stmt = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, newEvent.getEvent_name());
				stmt.setString(2, newEvent.getLocation());
				stmt.setString(3, newEvent.getDate());
				stmt.setString(4, newEvent.getTime());
				stmt.setString(5, newEvent.getDescription());
				stmt.setString(6, newEvent.getCreator());
				
				int rs = stmt.executeUpdate();
				
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
				
				
				PreparedStatement stmt ;
				String sql= "UPDATE tblevents SET eventname=?, location=?, date=?, time=?, description=? WHERE id=?;";
				stmt = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, event.getEvent_name());
				stmt.setString(2, event.getLocation());
				stmt.setString(3, event.getDate());
				stmt.setString(4, event.getTime());
				stmt.setString(5, event.getDescription());
				stmt.setInt(6, event.getId());
				
				int rs = stmt.executeUpdate();
				
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
				
				PreparedStatement stmt ;
				String sql= "DELETE FROM tblevents WHERE id=?;";
				stmt = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, event.getId());
				
				int rs = stmt.executeUpdate();
				
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
				
				PreparedStatement stmt ;
				String sql= "SELECT * FROM tblevents WHERE id = ?;";
				stmt = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, id);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					Event eventRequested = new Event(id, rs.getString("event_name"), rs.getString("date"), rs.getString("time"), rs.getString("description"), rs.getString("location"), rs.getString("creator"));
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
					Event eventRequested = new Event(rs.getInt("id"), rs.getString("event_name"), rs.getString("date"), rs.getString("time"), rs.getString("description"), rs.getString("location"), rs.getString("creator"));
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
					Event eventRequested = new Event(rs.getInt("id"), rs.getString("event_name"), rs.getString("date"), rs.getString("time"), rs.getString("description"), rs.getString("location"), rs.getString("creator"));
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
