package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.User;

public class UserRepository {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -2789556140998785078L;
	
	public UserRepository() {
		super();
	}
	
	public String addUser(User newUser){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql="INSERT INTO tblusers (id,username,password,firstName,lastName,email,birthOfDate,gender) VALUES (?,?,?,?,?,?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, newUser.getId());
			stmt.setString(2, newUser.getUsername());
			stmt.setString(3, newUser.getPassword());
			stmt.setString(4, newUser.getFirstName());
			stmt.setString(5, newUser.getLastName());
			stmt.setString(6,newUser.getEmail());
			stmt.setString(7, newUser.getBday());
			stmt.setString(8,newUser.getGender());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e){
			e.printStackTrace();
			return "SQL ERROR";
	   }
	}
		
	public String editUser(User user) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "UPDATE tblevents SET id=? ,username=?,firstName=?,lastName=?,email=?,birthOfDate=?,gender=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getFirstName());
			stmt.setString(5, user.getLastName());
			stmt.setString(6, user.getEmail());
			stmt.setString(7, user.getBday());
			stmt.setString(8, user.getGender());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}

	
	public String deleteUser(User user) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "DELETE FROM tblevents WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, user.getId());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
	
	public User getUserById(String id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblusers WHERE id =?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, id);	
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User userRequested= new User(rs.getString("username"), rs.getString("password"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthOfDate"), rs.getString("gender"));
				return userRequested;
			} else {
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
	   }
	}
	
	public User getUserByUsername(String username){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblusers WHERE username = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, username);	
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				User userRequested= new User(rs.getString("username"), rs.getString("password"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthOfDate"), rs.getString("gender"));
				return userRequested;
			} else {
				return null;
			  }
		   }catch (Exception e){
				e.printStackTrace();
				return null;
		 }
	}
		
	
	public String userAuthenticator(User user){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "select * from tblusers where username=? and password=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.executeQuery();
			return "success";
		   }catch (Exception e){
				e.printStackTrace();
				return "SQL ERROR";
		   }
	}
	
	public List<User> getAllUsers(){
		try{
			Connection c = Database.getInstance().getConnection();
			Statement stmt = null;
			stmt = c.createStatement();
			String sql="SELECT * FROM tblusers;";
			ResultSet rs = stmt.executeQuery(sql);
			List<User> allUsers = new ArrayList<>();
			while (rs.next()) {
				User userRequested= new User(rs.getString("username"), rs.getString("password"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthOfDate"), rs.getString("gender"));
				allUsers.add(userRequested);
			}
			return allUsers;
		 }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<User>();
		 }
	}
}
