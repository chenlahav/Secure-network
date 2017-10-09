package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Database instance = null;
	private Connection c = null;
	
	private Database() {
	}
	
	public static Database getInstance() {
		if (instance == null) {
			synchronized (Database.class) {
				if (instance == null)
					instance = new Database();
			}
		}
		return instance;
	}
	
	public void init() throws ClassNotFoundException, SQLException {
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:resource/db.sqlite");
		}catch (Exception e){}
	}

	public Connection getConnection() {
		return this.c;
	}

	public void closeConnection() {
		try {
			this.c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.c = null;
		}
	}

}
