package model;

import java.sql.*;


public class Post{
	private int id;
	private String title;
	private String content;
	private String author;
	private Date date;
	private Time time;
	
	
	public Post(int id, String title, String content, String author, Date date, Time time) {
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
}
