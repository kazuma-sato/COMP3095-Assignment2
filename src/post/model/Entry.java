package post.model;

import java.util.Date;

public class Entry {
	
	private String bodyText;
	private String author;
	private Date date;
	
	public String getBodyText(){
		return bodyText;
	}
	public void setBodyText(String bodyText){
		this.bodyText = bodyText;
	}
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author){
		this.author = author;
	}
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date = date;
	}
}
