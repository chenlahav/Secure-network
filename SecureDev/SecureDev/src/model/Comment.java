package model;

public class Comment {
	private int id;
	private String time;
	private String date;
	private String content;
	private int postId;
	private User creator;
	
	public Comment(String time, String date, String content, int postId,User creator) {
		super();
		this.time = time;
		this.date = date;
		this.content = content;
		this.postId = postId;
		this.creator = creator;
	}
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}

	

}
