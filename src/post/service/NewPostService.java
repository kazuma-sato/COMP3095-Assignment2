package post.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewPostService {
	
	final String JDBC_Driver = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/comp3095";
	final String DB_USER = "root";
	final String DB_PASS = "admin";
	
	public boolean postTable(
			String username, String bodyText, String entryType, String commentParentPost){
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement prepInsertStatement;
			
				prepInsertStatement = conn.prepareStatement(
						"INSERT INTO `Entry`" +
						"(`username`, `body`, `entrytype`, `parentpost`)" + 
						"VALUES (?,?,?,?)");
			
			prepInsertStatement.setString(1, username);
			prepInsertStatement.setString(2, bodyText);
			prepInsertStatement.setString(3, entryType);
			prepInsertStatement.setInt(4, Integer.parseInt(commentParentPost));
			
			if(prepInsertStatement.executeUpdate() == 0) {
				prepInsertStatement.close();
				conn.close();
				return false;
			}
			
			prepInsertStatement.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public boolean postTable(String username, String bodyText, String entryType){
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement prepInsertStatement;
			
			prepInsertStatement = conn.prepareStatement(
				"INSERT INTO `Entry`" +
				"(`username`, `body`, `entrytype`)" + 
				"VALUES (?,?,?)");
				
			prepInsertStatement.setString(1, username);
			prepInsertStatement.setString(2, bodyText);
			prepInsertStatement.setString(3, entryType);
			
			if(prepInsertStatement.executeUpdate() == 0) {
				prepInsertStatement.close();
				conn.close();
				return false;
			}
			
			prepInsertStatement.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
