package post.model;
/*
 * COMP3095 Project
 * Kazuma Sato 100 948 212
 * Mark Wheeler-Gallant 100 800 311
 * 
 */
import java.util.List;

public class Post extends Entry{

	private List<Comment> comments;
	String title;
	
	public List<Comment> getComments(){
		return comments;
	}
	public void setComments(List<Comment> comments){
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
