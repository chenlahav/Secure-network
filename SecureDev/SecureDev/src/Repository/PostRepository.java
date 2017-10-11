package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Post;
import model.User;

public class PostRepository {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public String addPost(Post newPost)
	{
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "INSERT INTO tblpost (title,content,authorid,date,time) VALUES (?,?,?,?,?);";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, newPost.getTitle());
			stmt.setString(2, newPost.getContent());
			stmt.setString(3, newPost.getAuthor().getId());
			stmt.setString(4, newPost.getDate());
			stmt.setString(5, newPost.getTime());
			stmt.executeUpdate();
			return "success";
		}catch(Exception e){
			return "SQL ERROR";
		}
	}
	
	public String editPost(Post post) {

		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql= "UPDATE tblpost SET title=?,content=?,date=?,time=?;";
			stmt = c.prepareStatement(sql);
			stmt.setString(1, post.getTitle());
			stmt.setString(2, post.getContent());
			stmt.setString(3, post.getDate());
			stmt.setString(4, post.getTime());
			stmt.executeUpdate();
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "SQL ERROR";
		}
	}
	
	public String deletePost(Post postToDelete)
	{
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			CommentsRepository cr = new CommentsRepository();
			String result = cr.DeleteAllCommentsByPostID(postToDelete.getId());
			if(result=="success"){
				String sql= "DELETE FROM tblpost WHERE id=?;";
				stmt = c.prepareStatement(sql);
				stmt.setInt(1, postToDelete.getId());
				stmt.executeUpdate();
				return "success";
			}
			else{
				return "SQL ERROR";
			}

			
		}catch (Exception e){
			   return "SQL ERROR";
		}
	}

	public Post getPost(int id) {
		try{
			Connection c = Database.getInstance().getConnection();
			PreparedStatement stmt ;
			String sql="SELECT * FROM tblpost WHERE id=?;";
			stmt = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				UserRepository ur = new UserRepository();
				User author = ur.getUserById(rs.getString("authorid"));
				Post postRequested = new Post(rs.getString("title"),rs.getString("content"),author,rs.getString("date"),rs.getString("time"));
				postRequested.setId(rs.getInt("id"));
				return postRequested;
			}
		 }catch (Exception e){
				e.printStackTrace();
				return null;
		 }
		return null;
	}
		
	public List<Post> getAllPosts() {
		try{
			Connection c = Database.getInstance().getConnection();
			Statement stmt = null;
			stmt = c.createStatement();
			String sql="SELECT * FROM tblpost ORDER BY id desc;";
			ResultSet rs = stmt.executeQuery(sql);
			List<Post> allPosts = new ArrayList<>();
			while (rs.next()) {
				UserRepository ur = new UserRepository();
				User author = ur.getUserById(rs.getString("authorid"));
				Post postRequested = new Post(rs.getString("title"),rs.getString("content"),author,rs.getString("date"),rs.getString("time"));
				postRequested.setId(rs.getInt("id"));
				allPosts.add(postRequested);
			}
			return allPosts;
		 }catch (Exception e){
				e.printStackTrace();
				return new ArrayList<Post>();
		 }
	}
}