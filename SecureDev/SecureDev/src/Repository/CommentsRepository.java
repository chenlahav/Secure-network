package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.User;
import model.Comment;
public class CommentsRepository {

	public String addComment(Comment newComment) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "INSERT INTO tblcomments (time, date, content, postid, creatorid) VALUES (?,?,?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, newComment.getTime());
			stmt.setString(2, newComment.getDate());
			stmt.setString(3, newComment.getContent());
			stmt.setInt(4, newComment.getPostId());
			stmt.setString(5, newComment.getCreator().getId());
			stmt.executeUpdate();
			return "success";
			
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
	
	public String DeleteComment(int IDCommentToDelete){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "DELETE FROM tblcomments WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, IDCommentToDelete);
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
	
	public String editComment(Comment comment) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "UPDATE tblcomments SET time=?, date=?, content=?, WHERE id=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, comment.getTime());
			stmt.setString(2, comment.getDate());
			stmt.setString(3, comment.getContent());
			stmt.setInt(4, comment.getId());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
	
	public Comment getComment(int id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblcomments WHERE id = ?;";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserRepository ur = new UserRepository();
				User creator = ur.getUserById(rs.getString("creatorid"));
				Comment commentRequested = new Comment(rs.getString("time"), rs.getString("date"), rs.getString("content"), rs.getInt("postid"), creator);
				commentRequested.setId(id);
				return commentRequested;
			  } 
		}catch (Exception e){
			e.printStackTrace();
			return null;
	   }
		return null;
	}
	
	public List<Comment> getAllComments(){
		try{
			Connection c = Database.getInstance().getConnection();
			Statement stmt = null;
			stmt = c.createStatement();
			String sql="SELECT * FROM tblComments;";
			ResultSet rs = stmt.executeQuery(sql);
			List<Comment> allComments = new ArrayList<>();
			while (rs.next()) {
				UserRepository ur = new UserRepository();
				User creator = ur.getUserById(rs.getString("creatorid"));
				Comment commentRequested = new Comment(rs.getString("time"), rs.getString("date"), rs.getString("content"), rs.getInt("postid"), creator);
				commentRequested.setId(rs.getInt("id"));
				allComments.add(commentRequested);
			  } 
			return allComments;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Comment>();
		   }
	}
	
	public List<Comment> getAllCommentsByUserID(String id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblcomments WHERE creatorid =?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			List<Comment> allComments = new ArrayList<>();
			while (rs.next()) {
				UserRepository ur = new UserRepository();
				User creator = ur.getUserById(rs.getString("creatorid"));
				Comment commentRequested = new Comment(rs.getString("time"), rs.getString("date"), rs.getString("content"), rs.getInt("postid"), creator);
				commentRequested.setId(rs.getInt("id"));
				allComments.add(commentRequested);
			  } 
			return allComments;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Comment>();
		   }
	}
	
	public List<Comment> getAllCommentsByPostID(String id){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "SELECT * FROM tblcomments WHERE postid =?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			List<Comment> allComments = new ArrayList<>();
			while (rs.next()) {
				UserRepository ur = new UserRepository();
				User creator = ur.getUserById(rs.getString("creatorid"));
				Comment commentRequested = new Comment(rs.getString("time"), rs.getString("date"), rs.getString("content"), rs.getInt("postid"), creator);
				commentRequested.setId(rs.getInt("id"));
				allComments.add(commentRequested);
			  } 
			return allComments;
		   }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Comment>();
		   }
	}
	
	public String DeleteAllCommentsByPostID(int postid){
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "DELETE FROM tblcomments WHERE postid=?;";
			stmt = c.prepareStatement(sql);
			stmt.setInt(1, postid);
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			return "SQL ERROR";
		}
	}
}
