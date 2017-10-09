package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Post;
import model.User;

public class PostRepository extends AbstractRepository
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String addPost(Post newPost)
	{
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				
				PreparedStatement stmt ;
				String sql= "INSERT INTO tblpost (title,content,authorid,date,time) VALUES (?,?,?,?,?);";
				stmt = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, newPost.getTitle());
				stmt.setString(2, newPost.getContent());
				stmt.setString(3, newPost.getAuthor().getId());
				stmt.setString(4, newPost.getDate());
				stmt.setString(5, newPost.getTime());
				stmt.setString(6, newPost.getTime());
				
				int rs = stmt.executeUpdate();
				
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
	
	public String editPost(Post post) {

		Connection c = AbstractRepository.connectionToDB();
		if (c != null) {
			try {
				PreparedStatement stmt ;
				String sql= "UPDATE tblpost SET title=?,content=?,date=?,time=?;";
				stmt = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, post.getTitle());
				stmt.setString(2, post.getContent());
				stmt.setString(3, post.getDate());
				stmt.setString(4, post.getTime());
				
				int rs = stmt.executeUpdate();
				
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
	
	public String deletePost(Post postToDelete)
	{
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				
				PreparedStatement stmt ;
				String sql= "UPDATE tblpost SET title=?,content=?,date=?,time=?;";
				stmt = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, postToDelete.getTitle());
				stmt.setString(2, postToDelete.getContent());
				stmt.setString(3, postToDelete.getDate());
				stmt.setString(4, postToDelete.getTime());
				
				int rs = stmt.executeUpdate();
				
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

	public Post getPost(int id)
	{
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
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
		}
		return null;

	}
	
	public List<Post> getAllPosts()
	{
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblpost;";
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
		return new ArrayList<Post>();

	}
}
