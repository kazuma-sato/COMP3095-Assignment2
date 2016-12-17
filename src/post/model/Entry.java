package post.model;

import java.util.Date;

public class Entry {
	
	private String bodyText;
	private User author;
	private Date date;
	
	public String getBodyText(){
		return bodyText;
	}
	public void setBodyText(String bodyText){
		this.bodyText = bodyText;
	}
	public User getAuthor(){
		return author;
	}
	public void setAuthor(User authorID){
		this.author = authorID;
	}
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date = date;
	}
}
