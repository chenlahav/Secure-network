package Repository;

import java.sql.Connection;
import java.sql.Statement;

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
				String sql = "INSERT INTO tblusers (eventname, location, date, time, description, creator) VALUES ('"
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

}
