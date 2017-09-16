package Repository;

import java.sql.Connection;
import java.sql.DriverManager;


public abstract class AbstractRepository extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Connection connectionToDB(){
		
		Connection c = null;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:C:/Users/snirkol/Documents/GitHub rep/Secure-network/SecureDev/SecureDev/resource/db.sqlite");
			return c;
		}catch (Exception e){
			//TODO
			return null;
		}		
	}

}
