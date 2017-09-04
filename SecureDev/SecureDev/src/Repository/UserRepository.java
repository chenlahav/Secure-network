package Repository;

import java.sql.Connection;
import java.sql.Statement;
import Repository.AbstractRepository;

import model.User;

public class UserRepository extends AbstractRepository{
	
	public String addUser(User newUser){
		
		Connection c = this.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="INSERT INTO tblusers (id,username,password,firstName,lastName,email,birthOfDate,gender) VALUES ('"+newUser.getId()+","+newUser.getUsername()+","+newUser.getPassword()+","+ newUser.getFirstName()+","+newUser.getLastName()+","+newUser.getEmail()+","+newUser.getBday()+","+newUser.getGender()+"');";
				int rs = stmt.executeUpdate(sql);
				if (rs!=0) {
					return "succses";
				  } 
				else
				  {
					return "failed";
				  }
			   }catch (Exception e){
				   return "SQL ERROR";
			   }
			}

		else
		{
			return "SQL ERROR";
		}
		}
	}
