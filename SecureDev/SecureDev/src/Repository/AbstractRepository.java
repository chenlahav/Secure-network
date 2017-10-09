package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.Database;


public abstract class AbstractRepository extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Connection connectionToDB(){
		try {
			Database.getInstance().init();
			Connection c = Database.getInstance().getConnection();
			return c;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConnection(){
		Database.getInstance().closeConnection();
	}

}
