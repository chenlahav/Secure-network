	package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import Repository.AbstractRepository;

import model.User;

public class UserRepository extends AbstractRepository{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2789556140998785078L;
	
	public UserRepository() {
		super();
	}
	
	public String addUser(User newUser){
		
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="INSERT INTO tblusers (id,username,password,firstName,lastName,email,birthOfDate,gender) VALUES ('"+newUser.getId()+"','"+newUser.getUsername()+"','"+newUser.getPassword()+"','"+ newUser.getFirstName()+"','"+newUser.getLastName()+"','"+newUser.getEmail()+"','"+newUser.getBday()+"','"+newUser.getGender()+"');";
				int rs = stmt.executeUpdate(sql);
				if (rs!=0) {
					return "success";
				  } 
				else
				  {
					return "failed";
				  }
			   }catch (Exception e){
					e.printStackTrace();
					return "SQL ERROR";
			   }
			}

		else
		{
			return "Connection ERROR";
		}
	}
	
	public User getUser(String id){
		Connection c = AbstractRepository.connectionToDB();
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
					userRequested.setGender(rs.getString("gender"));
					return userRequested;
				  } 
				else
				  {
					return null;
				  }
			   }catch (Exception e){
					e.printStackTrace();
					return null;
			   }
			}

		else
		{
			return null;
		}
	}
	
	public String userAuthenticator(User user){
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="select * from tblusers where username='" + user.getUsername() + "' and password='" + user.getPassword() + "';";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					return "success";
				  } 
				else
				  {
					return "failure";
				  }
			   }catch (Exception e){
					e.printStackTrace();
					return "SQL ERROR";
			   }
			}

		else
		{
			return null;
		}
	}
	
	
}
