package post.model;

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
