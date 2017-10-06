package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Post;

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
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="INSERT INTO tblpost (id,title,content,author,date,time) VALUES ('"+newPost.getId()+"','"+newPost.getTitle()+"','"+newPost.getContent()+"','"+ newPost.getAuthor()+"','"+newPost.getDate()+"','"+newPost.getTime()+"');";
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
	
	public String editPost(Post post) {

		Connection c = AbstractRepository.connectionToDB();
		if (c != null) {
			try {
				Statement stmt = null;
				stmt = c.createStatement();
				String sql = "UPDATE tblpost SET id="+post.getId()+",title="+post.getTitle()+",content="+post.getContent()+",author="+post.getAuthor()+",date"+post.getDate()+",time"+post.getTime()+";";
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
	
	public String deletePost(Post postToDelete)
	{
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql = "DELETE FROM tblpost WHERE id="+postToDelete.getId()+";";
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

	public Post getPost(int id)
	{
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblpost WHERE id="+id+";";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					//Time timeParse = (Time) new SimpleTimeZone("HH:mm:ss").parse(rs.getString("time"));
					Post postRequested = new Post(rs.getInt("id"),rs.getString("title"),rs.getString("content"),rs.getString("authorid"),rs.getDate("date"),rs.getTime("time"));
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
					Post postRequested = new Post(rs.getInt("id"),rs.getString("title"),rs.getString("content"),rs.getString("author"),rs.getDate("date"),rs.getTime("time"));
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
