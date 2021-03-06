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
	
	public String addUser(User newUser,String hashedPassword){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql="INSERT INTO tblusers (id,username,password,firstName,lastName,email,birthOfDate,gender,telephoneNumber,isprofileimage) VALUES (?,?,?,?,?,?,?,?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, newUser.getId());
			stmt.setString(2, newUser.getUsername());
			stmt.setString(3, hashedPassword);
			stmt.setString(4, newUser.getFirstName());
			stmt.setString(5, newUser.getLastName());
			stmt.setString(6,newUser.getEmail());
			stmt.setString(7, newUser.getBday());
			stmt.setString(8,newUser.getGender());
			stmt.setString(9, newUser.getTelephone());
			stmt.setBoolean(10, newUser.getisProfileImage());
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
			String sql= "UPDATE tblusers SET username=?, firstName=?, lastName=?, email=?, birthOfDate=?, telephoneNumber=?, isAdmin=? WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getFirstName());
			stmt.setString(3, user.getLastName());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getBday());
			stmt.setString(6, user.getTelephone());
			stmt.setBoolean(7, user.isAdmin());
			stmt.setString(8, user.getId());
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
			PostRepository pr = new PostRepository();
			if(pr.deletePostsByUserID(user.getId())!= "success"){
				return "SQL ERROR";
			}
			EventRepository er = new EventRepository();
			if(er.deleteEventsByUserId(user.getId())!="success"){
				return "SQL ERROR";
			}
			String sql= "DELETE FROM tblusers WHERE id=?;";
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
				User userRequested= new User(rs.getString("username"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthOfDate"), rs.getString("gender"),rs.getString("telephoneNumber"),rs.getBoolean("isProfileImage"));
				userRequested.setAdmin(rs.getBoolean("isAdmin"));
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
				User userRequested= new User(rs.getString("username"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthOfDate"), rs.getString("gender"),rs.getString("telephoneNumber"),rs.getBoolean("isProfileImage"));
				userRequested.setAdmin(rs.getBoolean("isAdmin"));
				return userRequested;
			} else {
				return null;
			  }
		   }catch (Exception e){
				e.printStackTrace();
				return null;
		 }
	}
	
	public List<User> getUsersByFirstname(String firstname){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblusers WHERE firstName = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, firstname);	
			ResultSet rs = stmt.executeQuery();
			List<User> listOfUsers =  new ArrayList<>();
			while (rs.next()) {
				User userRequested= new User(rs.getString("username"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthOfDate"), rs.getString("gender"),rs.getString("telephoneNumber"),rs.getBoolean("isProfileImage"));
				userRequested.setAdmin(rs.getBoolean("isAdmin"));
				listOfUsers.add(userRequested);
			  }
			return listOfUsers;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<User>();
		 }
	}
	
	public String userAuthenticator(User user,String password){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "select * from tblusers where username=? and password=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return "success";
			}else{
				return "SQL ERROR";
			}
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
				User userRequested= new User(rs.getString("username"), rs.getString("id"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("birthOfDate"), rs.getString("gender"),rs.getString("telephoneNumber"),rs.getBoolean("isProfileImage"));
				userRequested.setAdmin(rs.getBoolean("isAdmin"));
				allUsers.add(userRequested);
			}
			return allUsers;
		 }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<User>();
		 }
	}
}
