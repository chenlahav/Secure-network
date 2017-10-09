package model;

public class Event {
	private int id;
	private String event_name;
	private String date;
	private String time;
	private String description;
	private String location;
	private User creator;
	
	public Event(int id, String event_name, String date, String time, String description, String location, User creator) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
