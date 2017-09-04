package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;

public abstract class AbstractRepository extends Exception{
	public static Connection connectionToDB(){
		Connection c = null;
		try{
			c = DriverManager.getConnection("jdbc:sqlite:C:/Users/Owner/Documents/GitHub/Secure-network/SecureDev/SecureDev/resource/db.sqlite");
			return c;
		}catch (Exception e){
			//TODO
			return null;
		}		
	}

}
