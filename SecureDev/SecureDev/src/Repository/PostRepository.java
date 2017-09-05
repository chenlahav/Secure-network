package Repository;

import java.sql.Connection;
import java.sql.Statement;

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
	
	public String getAll()
	{
		Connection c = AbstractRepository.connectionToDB();
		if (c!=null){
			try{
				Statement stmt = null;
				stmt = c.createStatement();
				String sql="SELECT * FROM tblpost;";
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
