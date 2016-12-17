package servlet.welcome;

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

/*
COMP3095 Web Application Development with Java
Assignment 1 - Servlets
Instructor : Sergio Santilli sergio.santilli@georgebrown.ca

by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
Date: Monday, October 10, 2016

Description:
	Login Servlet for an online journal
*/

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	final String secret = "K2vJAdF3hJv4nhD3";
	final String JDBC_Driver = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/comp3095";
	final String DB_USER = "root";
	final String DB_PASS = "admin";
	final String passwordCookieNameHashed = hashWithSecret("password", secret);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		
		if(session == null){
			if(checkCookies(request)){
				startSession(request);
			} else {
				response.getWriter().println("<script>\ndocument.getElementById('errorMessage').innerHTML = 'You must login to access that page!<br>';\n</script>\n");
				request.getRequestDispatcher("LoginServlet").forward(request, response);
				return;
			}
		}
		
		if(session.getAttribute("username") == null){
			request.getRequestDispatcher("LoginServlet");
			return;
		}
		String name = session.getAttribute("firstname") + " " + session.getAttribute("lastname");
		response.getWriter().println("<!DOCTYPE html>\r\n<html>\r\n<!--\r\n\tCOMP3095 Web Application Development with Java\r\n\tAssignment 1 - Servlets\r\n\tInstructor : Sergio Santilli sergio.santilli@georgebrown.ca\r\n\t\r\n\tby Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca\r\n\tDate: Monday, October 10, 2016\r\n\r\n\tDescription:\r\n\t\tWelcome page for an online journal\r\n-->\r\n\r\n<head>\r\n    <meta charset='UTF-8'>\r\n    <title>Welcome " + 
				name + "! | Journal</title>\r\n    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Roboto'>\r\n    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Roboto+Slab'>\r\n    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Source+Code+Pro'>\r\n    <style>\r\n        * {\r\n            margin: 0;\r\n            padding: 0;\r\n        }\r\n        body {\r\n            width: 85%;\r\n            max-width: 96em;\r\n            font-size: 62.5%;\r\n            background-color: #333333;\r\n            margin: 2em auto 2em auto;\r\n        }\r\n        form {\r\n            margin-left: auto;\r\n            margin-right: auto;\r\n        }\r\n        fieldset {\r\n            border-width: 1px;\r\n            border-top-style: solid;\r\n            border-color: white;\r\n            padding: .5em;\r\n        }\r\n        legend,\r\n        h2 {\r\n            text-align: center;\r\n            font-family: 'Roboto Slab', Times, serif;\r\n            font-size: 36px;\r\n            color: white;\r\n        }\r\n        label,\r\n        h3,\r\n        header a {\r\ndisplay: inline;\r\n                    text-align: right;\r\n            font-family: 'Roboto Slab', Times, serif;\r\n            font-size: 24px;\r\n            color: #75a3d1;\r\n        }\r\n        .error {\r\n            font-family: 'Roboto Slab', Times, serif;\r\n            font-size: 24px;\r\n            color: #E05151;\r\n        }\r\n        input,\r\n        select {\r\n            border: solid;\r\n            border-width: 1px;\r\n            border-color: white;\r\n            background-color: #555555;\r\n            padding: .25em;\r\n            margin: .25em;\r\n            font-family: 'Source Code Pro', Courier, monospace;\r\n            font-size: 20px;\r\n            color: #FFD452;\r\n        }\r\n        #phone1,\r\n        #phone2 {\r\n            width: 3em;\r\n        }\r\n        #phone3 {\r\n            width: 4em;\r\n        }\r\n        #phoneformat {\r\n            font-family: 'Source Code Pro', Courier, monospace;\r\n            font-size: 20px;\r\n            color: #FFD452;\r\n        }\r\n        header{\r\n        \ttext-align: right;\r\n        height: 4em;\r\n        \tbackground-color: #555555;\r\n        }\r\n    img {\r\n\t\twidth: 100%\r\n\t\t}\r\n\t</style>\r\n</head>\r\n\r\n<body>\r\n\t<header>\r\n\t\t<h3>Welcome, <span id='name'>" + 
				name + "</span>! </h3> <a href='/assignment2/LoginServlet?logout=true'>Logout</a> \r\n\t</header>\r\n\r\n\t<section>\r\n\t\t<img src='" + request.getContextPath() + "/Images/underconstruction.jpg' alt='Under Construction'>\r\n\t</section>\r\n</body>\r\n\r\n</html>\r\n;");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	private boolean checkCookies(HttpServletRequest request) {

		String username = "";
		String passwordCookieValueHashed = "";
		boolean cookieIsValid = false;
		Cookie[] cookies;

	    cookies = request.getCookies();
	    if(cookies != null){
	    	for(int i = 0; i < cookies.length; i++){
	    		if(cookies[i].getName() == "username"){
	    			username = cookies[i].getValue();
	    		} else if (cookies[i].getName() == passwordCookieNameHashed){
	    			passwordCookieValueHashed = cookies[i].getValue();
	    		}
	    	}
	    	if(username == "" || passwordCookieValueHashed == ""){
	    		return false;
	    	}
	    	try{
		    	Class.forName(JDBC_Driver);
				Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				PreparedStatement prepSelectStatement = conn.prepareStatement(
						"SELECT password " +
						"FROM users " +
						"WHERE username = ?;");
				prepSelectStatement.setString(1, username);
				final ResultSet selectResult  = prepSelectStatement.executeQuery();
				if(selectResult.next()){
					cookieIsValid = (hashWithSecret(selectResult.getString("password"), secret) == passwordCookieValueHashed);
				}
				prepSelectStatement.close();
				selectResult.close();
				conn.close();
			} catch(NullPointerException ex) {
				ex.printStackTrace();
			} catch(SQLException se) {
				se.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
	    }
		return cookieIsValid;
	}
	
	private void startSession(HttpServletRequest request){

		String username = "";
		
		Cookie[] cookies = request.getCookies();
    	for(int i = 0; i < cookies.length; i++){
    		if(cookies[i].getName() == "username"){
    			username = cookies[i].getValue();
    		}
    	}
		
		try {
			Class.forName(JDBC_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		
			PreparedStatement prepSelectStatement = conn.prepareStatement(
					"SELECT * " + 
					"FROM USERS " +
					"WHERE username = ?");
			prepSelectStatement.setString(1, username);
			final ResultSet selectResult  = prepSelectStatement.executeQuery();
			
			if(selectResult.next()){
				int columnCount = selectResult.getMetaData().getColumnCount();
				HttpSession session = request.getSession(true);
				
				String columnName;
				String columnValue;
				
				for(int i = 1; i <= columnCount; i++) {
					columnName = selectResult.getMetaData().getColumnName(i);
					columnValue = selectResult.getString(i);
					session.setAttribute(columnName, new String(columnValue));
				}
			}
		} catch(NullPointerException ex) {
			ex.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	} 
	
    private String hashWithSecret(String value, String secret) {
    	
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            return convertByteArrayToHexString(messageDigest.digest((value + secret).getBytes()));
        } catch (NoSuchAlgorithmException e) {
            log("MessageDigest failed " + e);
            return null;
        }
    }
    
    private static String convertByteArrayToHexString(byte[] arrayBytes) {
    	
        StringBuilder stringBuffer = new StringBuilder();
        for (byte arrayByte : arrayBytes) {
            stringBuffer.append(Integer.toString((arrayByte & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }
}
