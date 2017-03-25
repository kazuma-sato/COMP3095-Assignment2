package post.service;
/*
 * COMP3095 Project
 * Kazuma Sato 100 948 212
 * Mark Wheeler-Gallant 100 800 311
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import post.model.Post;
import post.model.User;

public class UserService {
	
	final String JDBC_Driver = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/comp3095";
	final String DB_USER = "root";
	final String DB_PASS = "admin";
	
	public User getUserDetails(int userID){
		
		ResultSet resultSet;
		User user = new User();
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement prepStatement;
			prepStatement = conn.prepareStatement(
					"SELECT * FROM `users` WHERE `id`=?;");
			prepStatement.setInt(1, userID);
			resultSet = prepStatement.executeQuery();
			
			while(resultSet.next()){
				user.setId(userID);
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setYear(resultSet.getString("year"));
				user.setMajor(resultSet.getString("major"));
				user.setUsername(resultSet.getString("username"));
			}
			resultSet.close();
			prepStatement.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	public List<Post> getAllPosts(int userID){
		
		List<Post> posts = new ArrayList<Post>();
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement prepStatement;
			ResultSet resultSet;
			
			prepStatement = conn.prepareStatement(
					"SELECT * FROM posts" +
				    "WHERE author_id=?;");
			
			prepStatement.setInt(1, userID);
			
			resultSet = prepStatement.executeQuery();
			
			while(resultSet.next()){
				
				Post post = new Post();
				post.setAuthor(userID);
				post.setBodyText(resultSet.getString("content"));
				post.setDate(resultSet.getDate("date_created"));
				post.setTitle("title");
				posts.add(post);
			}
			resultSet.close();
			
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
