package model;

import java.sql.Date;
import java.sql.Time;

public class Event {
	private int id;
	private String event_name;
	private Date date;
	private Time time;
	private String description;
	private String location;
	private String creator;
	
	public Event(int id, String event_name, Date date, Time time, String description, String location, String creator) {
		super();
		this.id = id;
		this.event_name = event_name;
		this.date = date;
		this.time = time;
		this.description = description;
		this.location = location;
		this.creator = creator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
