package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Event;
import model.User;

public class EventRepository {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = -4712130067123623444L;

	public EventRepository() {
		super();
	}

	public String addEvent(Event newEvent) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "INSERT INTO tblevents (eventname, location, date, time, description, creator) VALUES (?,?,?,?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, newEvent.getEvent_name());
			stmt.setString(2, newEvent.getLocation());
			stmt.setString(3, newEvent.getDate());
			stmt.setString(4, newEvent.getTime());
			stmt.setString(5, newEvent.getDescription());
			stmt.setString(6, newEvent.getCreator().getId());
			stmt.executeUpdate();
			return "success";
			
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}

	public String editEvent(Event event) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "UPDATE tblevents SET eventname=?, location=?, date=?, time=?, description=? WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, event.getEvent_name());
			stmt.setString(2, event.getLocation());
			stmt.setString(3, event.getDate());
			stmt.setString(4, event.getTime());
			stmt.setString(5, event.getDescription());
			stmt.setInt(6, event.getId());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
		
	public String deleteEvent(Event event) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "DELETE FROM tblevents WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, event.getId());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
	
	public Event getEvent(int id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblevents WHERE id = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserRepository ur = new UserRepository();
				User creator = ur.getUserById(rs.getString("creatorid"));
				Event eventRequested = new Event(rs.getString("eventname"), rs.getString("date"), rs.getString("time"), rs.getString("description"), rs.getString("location"), creator);
				eventRequested.setId(id);
				return eventRequested;
			  } 
		}catch (Exception e){
			e.printStackTrace();
			return null;
	   }
		return null;
	}
	
	public List<Event> getAllEvents(){
		try{
			Connection c = Database.getInstance().getConnection();
			Statement stmt = null;
			stmt = c.createStatement();
			String sql="SELECT * FROM tblevents;";
			ResultSet rs = stmt.executeQuery(sql);
			List<Event> allEvents = new ArrayList<>();
			while (rs.next()) {
				UserRepository ur = new UserRepository();
				User creator = ur.getUserById(rs.getString("creatorid"));
				Event eventRequested = new Event(rs.getString("eventname"), rs.getString("date"), rs.getString("time"), rs.getString("description"), rs.getString("location"), creator);
				eventRequested.setId(rs.getInt("id"));
				allEvents.add(eventRequested);
			  } 
			return allEvents;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Event>();
		   }
	}
	
	public List<Event> getAllEventsByUserID(String id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblevents WHERE creatorid =?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			List<Event> allEvents = new ArrayList<>();
			while (rs.next()) {
				UserRepository ur = new UserRepository();
				User creator = ur.getUserById(rs.getString("creatorid"));
				Event eventRequested = new Event(rs.getString("eventname"), rs.getString("date"), rs.getString("time"), rs.getString("description"), rs.getString("location"), creator);
				eventRequested.setId(rs.getInt("id"));
				allEvents.add(eventRequested);
			  } 
			return allEvents;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Event>();
		   }
	}
}
				
