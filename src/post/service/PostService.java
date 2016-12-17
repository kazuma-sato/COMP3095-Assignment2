package post.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import post.model.*;

public class PostService {
	
	final String JDBC_Driver = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/comp3095";
	final String DB_USER = "root";
	final String DB_PASS = "admin";
	
	public boolean insertPost(
			String username, String bodyText, String entryType, String commentParentPost){
		
		boolean isAComment = entryType.equalsIgnoreCase("comment"); 
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement prepStatement;
				
				prepStatement = conn.prepareStatement(
						"INSERT INTO" +
						((isAComment) ? "`comment`" : "`post`") +
						"(`username`, `body`, `entrytype`, `" +
						((isAComment) ? "post_id" : "title")
						+ "`) VALUES (?,?,?,?)");
			
			prepStatement.setString(1, username);
			prepStatement.setString(2, bodyText);
			prepStatement.setString(3, entryType);
			
			if(isAComment) {
				prepStatement.setInt(4, Integer.parseInt(commentParentPost));
			} else {
				prepStatement.setString(4, commentParentPost);
			}
			
			if(prepStatement.executeUpdate() == 0) {
				prepStatement.close();
				conn.close();
				return false;
			}
			
			prepStatement.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Post getPostDetails(String username, String title){
		
		int postAuthorID = 0;
		Post post = new Post();
		List<Comment> comments = new ArrayList<Comment>();
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement prepPostStatement;
			PreparedStatement prepCommentStatement;
			ResultSet postResultSet;
			ResultSet commentResultSet;
			
			prepPostStatement = conn.prepareStatement(
					"SELECT * FROM posts" +
				    "WHERE username=?" + 
					"AND title=?;");
			prepCommentStatement = conn.prepareStatement(
					"SELECT * FROM comments" +
					"WHERE post_id=?;"
					);
			
			prepPostStatement.setString(1, username);
			prepPostStatement.setString(2, title);
			
			postResultSet = prepPostStatement.executeQuery();
			
			while(postResultSet.next()){
				
				post = new Post();
				postAuthorID = postResultSet.getInt("author_id");
				post.setAuthor(postAuthorID);
				post.setBodyText(postResultSet.getString("content"));
				post.setDate(postResultSet.getDate("date_created"));
				post.setTitle("title");
			}
			postResultSet.close();
			prepCommentStatement.setInt(1, postAuthorID);
			commentResultSet = prepCommentStatement.executeQuery();
			
			while(commentResultSet.next()){
				Comment comment = new Comment();
				comment.setAuthor(commentResultSet.getInt("author_id"));
				comment.setBodyText(commentResultSet.getString("content"));
				comment.setDate(commentResultSet.getDate("date_created"));
				comment.setParentPostID(postAuthorID);
				comments.add(comment);
			}
			post.setComments(comments);
			prepPostStatement.close();
			prepCommentStatement.close();
			commentResultSet.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return post;
	}
	
	public List<Integer> getAllPostIDs(){
		List<Integer> posts = new ArrayList<Integer>();
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement prepStatement;
			ResultSet resultSet;

			prepStatement = conn.prepareStatement("SELECT `id` FROM `posts`");
			resultSet = prepStatement.executeQuery();
			
			while(resultSet.next()){
				posts.add(resultSet.getInt("id"));
			}
			prepStatement.close();
			resultSet.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return posts;
	}
}
