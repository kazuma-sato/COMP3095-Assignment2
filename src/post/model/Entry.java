package post.model;
/*
 * COMP3095 Project
 * Kazuma Sato 100 948 212
 * Mark Wheeler-Gallant 100 800 311
 * 
 */
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
