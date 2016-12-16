package post.model;

public class Comment extends Entry {
	
	private int parentPostID;
	
	public int getParentPostID(){
		return parentPostID;
	}
	public void setParentPostID(int parentPostID){
		this.parentPostID = parentPostID;
	}
}
