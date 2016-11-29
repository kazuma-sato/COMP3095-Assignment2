package post.model;

public class Post extends Entry{

	private Comment[] comments;
	
	public Comment[] getComments(){
		return comments;
	}
	public void setComments(Comment[] comments){
		this.comments = comments;
	}
}
