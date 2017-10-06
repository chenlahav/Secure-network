package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public String editUser(User user) {

		Connection c = AbstractRepository.connectionToDB();
		if (c != null) {
			try {
				Statement stmt = null;
				stmt = c.createStatement();
				String sql = "UPDATE tblevents SET id="+user.getId()+",username="+user.getUsername()+",firstName="+user.getFirstName()+",lastName="+user.getLastName()+",email="+user.getEmail()+",birthOfDate="+user.getBday()+",gender="+user.getGender()+";";
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
	
	public String deleteUser(User user) {

		Connection c = AbstractRepository.connectionToDB();
		if (c != null) {
			try {
				Statement stmt = null;
				stmt = c.createStatement();
				String sql = "DELETE FROM tblevents WHERE id="+user.getId()+";";
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
	
	public User getUserById(String id){
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblusers WHERE id = "+id+";";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					User userRequested= new User(rs.getString("username"), rs.getString("password"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getDate("birthOfDate"), rs.getString("gender"));
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
		return null;
	}
	
	public User getUserByUsername(String username){
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblusers WHERE username = "+username+";";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					User userRequested= new User(rs.getString("username"), rs.getString("password"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getDate("birthOfDate"), rs.getString("gender"));
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
		return null;
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
		return null;
	}
	
	public List<User> getAllUsers(String id){
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblusers;";
				ResultSet rs = stmt.executeQuery(sql);
				List<User> allUsers = new ArrayList<>();
				while (rs.next()) {
					User userRequested= new User(rs.getString("username"), rs.getString("password"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getDate("birthOfDate"), rs.getString("gender"));
					allUsers.add(userRequested);
				}
				return allUsers;
			 }catch (Exception e){
					e.printStackTrace();
					return new ArrayList<User>();
			   }
			}
		return new ArrayList<User>();	
	}
}
