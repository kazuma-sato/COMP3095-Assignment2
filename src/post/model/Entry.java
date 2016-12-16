package post.model;

import java.util.Date;

public class Entry {
	
	private String bodyText;
	private int authorID;
	private Date date;
	
	public String getBodyText(){
		return bodyText;
	}
	public void setBodyText(String bodyText){
		this.bodyText = bodyText;
	}
	public int getAuthorID(){
		return authorID;
	}
	public void setAuthor(int authorID){
		this.authorID = authorID;
	}
	public Date getDate(){
		return date;
	}
	public void setDate(Date date){
		this.date = date;
	}
}
