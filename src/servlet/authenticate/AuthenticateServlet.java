package servlet.authenticate;

/*
COMP3095 Web Application Development with Java
Assignment 1 - Servlets
Instructor : Sergio Santilli sergio.santilli@georgebrown.ca

by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
Date: Monday, October 10, 2016

Description:
	Authentication Servlet for Login to an online journal
*/

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AuthenticateServlet")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		final String JDBC_Driver = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/comp3095";
		final String DB_USER = "root";
		final String DB_PASS = "admin";
		
		
		boolean authenticationSuccess = false;
		try {
			final String username = request.getParameter("username").trim().toLowerCase();
			final String password = request.getParameter("password").trim();
			boolean remember = false;
			if(request.getParameter("remember") == null){
				remember = false;
			} else {
				remember = request.getParameter("remember").equals(new String("true"));
			}
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			
			if(username != "" && password != ""){
				PreparedStatement prepSelectStatement = conn.prepareStatement(
						"SELECT count(*) " + 
						"FROM USERS WHERE username = ?;");
				prepSelectStatement.setString(1, username);
				final ResultSet selectResult  = prepSelectStatement.executeQuery();
				selectResult.next();
				if (selectResult.getInt(1) != 1) {
					prepSelectStatement.close();
					selectResult.close();
					conn.close();
				} else {
					PreparedStatement prepLoginStatement = conn.prepareStatement(
							"SELECT * " + 
							"FROM USERS " +
							"WHERE username = ? AND password = ?;");
					prepLoginStatement.setString(1, username);
					prepLoginStatement.setString(2, password);
					final ResultSet loginResult  = prepLoginStatement.executeQuery();
					
					authenticationSuccess = loginResult.next();
					if(authenticationSuccess) {
						int columnCount = loginResult.getMetaData().getColumnCount();
						HttpSession session = request.getSession(true);
						
						String columnName;
						String columnValue;
						
						for(int i = 1; i <= columnCount; i++) {
							columnName = loginResult.getMetaData().getColumnName(i);
							columnValue = loginResult.getString(i);
							session.setAttribute(columnName, new String(columnValue));
						}
						if(remember) {
							Cookie userCookie = new Cookie("username", username);
							Cookie passCookie = new Cookie("password", password);
							userCookie.setMaxAge(604800);
							passCookie.setMaxAge(604800);
							userCookie.setPath("/");
							passCookie.setPath("/");
							response.addCookie(userCookie);
							response.addCookie(passCookie);
						}
					}
					prepSelectStatement.close();
					prepLoginStatement.close();
					selectResult.close();
					loginResult.close();
					conn.close();
				}
			} 
		} catch(NullPointerException ex) {
			ex.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		String destination = (authenticationSuccess) ? "WelcomeServlet" : "LoginServlet";
		request.getRequestDispatcher(destination).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		doGet(request, response);
	}
	

}
