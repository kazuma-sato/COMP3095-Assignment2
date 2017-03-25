package post.model;
/*
 * COMP3095 Project
 * Kazuma Sato 100 948 212
 * Mark Wheeler-Gallant 100 800 311
 * 
 */
public class Comment extends Entry {
	
	private int parentPostID;
	
	public int getParentPostID(){
		return parentPostID;
	}
	public void setParentPostID(int parentPostID){
		this.parentPostID = parentPostID;
	}
}
