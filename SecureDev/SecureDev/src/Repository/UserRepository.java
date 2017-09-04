package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Repository.AbstractRepository;

import model.User;

public class UserRepository extends AbstractRepository{
	
	public UserRepository() {
		super();
		// TODO Auto-generated constructor stub
	}
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
			return "Connection ERROR";
		}
	}
	public User getUser(String id){
		Connection c = this.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblusers WHERE id = "+id+";";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					User userRequested = null;
					userRequested.setId(id);
					userRequested.setUsername(rs.getString("username"));
					userRequested.setFirstName(rs.getString("firstName"));
					userRequested.setLastName(rs.getString("lastName"));
					userRequested.setEmail(rs.getString("email"));
					userRequested.setBday(rs.getString("birthOfDate"));
					userRequested.setGender(rs.getString("gnder"));
					return userRequested;
				  } 
				else
				  {
					return null;
				  }
			   }catch (Exception e){
				   return null;
			   }
			}

		else
		{
			return null;
		}
	}
}
