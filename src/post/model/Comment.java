package post.model;

public class Comment extends Entry {
	private Post parentPost;
	public Post getParentPost(){
		return parentPost;
	}
	public void setParentPost(Post parentPost){
		this.parentPost = parentPost;
	}
}
