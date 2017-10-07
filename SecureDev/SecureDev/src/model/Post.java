package model;

import java.sql.*;


public class Post{
	private int id;
	private String title;
	private String content;
	private String author;
	private String date;
	private String time;
	
	
	public Post(int id, String title, String content, String author, String date, String time) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.date = date;
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
}
