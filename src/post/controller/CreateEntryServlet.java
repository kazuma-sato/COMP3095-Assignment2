package post.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateEntryServlet")
public class CreateEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateEntryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		final String JDBC_Driver = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/comp3095";
		final String DB_USER = "root";
		final String DB_PASS = "admin";
		
		String username;
		String bodyText;
		String entryType;
		String commentParentPost;
		
		username = request.getParameter("username");
		bodyText = request.getParameter("bodyText");
		entryType = request.getParameter("entryType");
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			PreparedStatement prepInsertStatement;
			
			if(entryType.matches("comment")) {
				commentParentPost = request.getParameter("parentPost");
				prepInsertStatement = conn.prepareStatement(
						"INSERT INTO `Entry`" +
						"(`username`, `body`, `entrytype`, `parentpost`)" + 
						"VALUES (?,?,?,?)");
				prepInsertStatement.setInt(4, Integer.parseInt(commentParentPost));
			} else {
				prepInsertStatement = conn.prepareStatement(
					"INSERT INTO `Entry`" +
					"(`username`, `body`, `entrytype`)" + 
					"VALUES (?,?,?)");
			}
			prepInsertStatement.setString(1, username);
			prepInsertStatement.setString(2, bodyText);
			prepInsertStatement.setString(3, entryType);
			
			if(prepInsertStatement.executeUpdate() == 0) {
				prepInsertStatement.close();
				conn.close();
				return;
			}
			
			prepInsertStatement.close();
			conn.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
