package servlet.register;

/*
COMP3095 Web Application Development with Java
Assignment 1 - Servlets
Instructor : Sergio Santilli sergio.santilli@georgebrown.ca

by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
Date: Monday, October 10, 2016

Description:
	Registration Servlet for an online journal
*/

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.register.FormError;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		RegForm html = new RegForm();
		response.getWriter().println(html.print());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		RegForm html = new RegForm();
		FormError errors = formValidation(request);
		
		if(!errors.isEmpty()) {
			response.getWriter().println(html.print());
			response.getWriter().println(fillFormWithJS(request));
			response.getWriter().println(displayErrorsWithJS(errors));
			return;
		} else {
			final String FIRST_NAME = request.getParameter("firstName").trim();
			final String LAST_NAME = request.getParameter("lastName").trim();
			final String EMAIL = request.getParameter("email").trim().toLowerCase();
			final String PHONE1 = request.getParameter("phone1").trim();
			final String PHONE2 = request.getParameter("phone2").trim();
			final String PHONE3 = request.getParameter("phone3").trim();
			final String PHONE = PHONE1 + PHONE2 + PHONE3;
			final String YEAR = request.getParameter("year").trim();
			final String MAJOR = request.getParameter("major").trim();
			final String USERNAME = request.getParameter("username").trim().toLowerCase();
			final String PASSWORD = request.getParameter("password").trim();
			
			final String JDBC_Driver = "com.mysql.jdbc.Driver";
			final String DB_URL = "jdbc:mysql://localhost:3306/comp3095";
			final String DB_USER = "root";
			final String DB_PASS = "admin";
			
			try {
				Class.forName(JDBC_Driver);
				Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				
				PreparedStatement prepSelectStatement = conn.prepareStatement(
						"SELECT count(*) " + 
						"FROM users " +
						"WHERE username = ?;");
				prepSelectStatement.setString(1, USERNAME);
				final ResultSet selectResult  = prepSelectStatement.executeQuery();
				selectResult.next();
				
				if (selectResult.getInt(1) == 1) {
					errors.setUsername("Username already exists. Please try another one.");
					response.getWriter().println(html.print());
					response.getWriter().println(fillFormWithJS(request));
					response.getWriter().println(displayErrorsWithJS(errors));
					prepSelectStatement.close();
					selectResult.close();
					conn.close();
					return;
				}
				
				PreparedStatement prepInsertStatement = conn.prepareStatement(
						"INSERT INTO `USERS`" +
						"(`firstname`, `lastname`, `email`, `phone`, `year`, `major`, `username`, `password`)" + 
						"VALUES (?,?,?,?,?,?,?,?)");
				prepInsertStatement.setString(1, FIRST_NAME);
				prepInsertStatement.setString(2, LAST_NAME);
				prepInsertStatement.setString(3, EMAIL);
				prepInsertStatement.setString(4, PHONE);
				prepInsertStatement.setString(5, YEAR);
				prepInsertStatement.setString(6, MAJOR);
				prepInsertStatement.setString(7, USERNAME);
				prepInsertStatement.setString(8, PASSWORD);
				
				if(prepInsertStatement.executeUpdate() == 0) {
					errors.setMain("Your registration didn't make it to the database. <br>Please try again.");
					response.getWriter().println(html.print());
					response.getWriter().println(fillFormWithJS(request));
					response.getWriter().println(displayErrorsWithJS(errors));
					prepInsertStatement.close();
					prepSelectStatement.close();
					selectResult.close();
					conn.close();
					return;
				}
				
				prepInsertStatement.close();
				prepSelectStatement.close();
				selectResult.close();
				conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			} 
			response.getWriter().print(html.printSuccess(FIRST_NAME));	
		}
	}
	
	private FormError formValidation(HttpServletRequest request) {
		
		FormError errors = new FormError();
		final String FIRST_NAME = request.getParameter("firstName").trim();
		final String LAST_NAME = request.getParameter("lastName").trim();
		final String EMAIL = request.getParameter("email").trim().toLowerCase();
		final String CONFIRM_EMAIL = request.getParameter("confirmEmail").trim().toLowerCase();
		final String PHONE1 = request.getParameter("phone1").trim();
		final String PHONE2 = request.getParameter("phone2").trim();
		final String PHONE3 = request.getParameter("phone3").trim();
		final String YEAR = request.getParameter("year").trim();
		final String MAJOR = request.getParameter("major").trim();
		final String USERNAME = request.getParameter("username").trim().toLowerCase();
		final String PASSWORD = request.getParameter("password").trim();
		final String CONFIRM_PASSWORD = request.getParameter("confirmPassword").trim();
		
		final String ONLY_LETTERS_PATTERN = "^[\\p{L} .'-]+$";
		final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		
		if(FIRST_NAME.isEmpty()) {
			errors.setFirstName("This field should not be empty. ");
		} else if (!FIRST_NAME.matches(ONLY_LETTERS_PATTERN)) {
			errors.setFirstName("Invalid charators used. ");
		}
		
		if(LAST_NAME.isEmpty()) {
			errors.setLastName("This field should not be empty.");
		} else if (!LAST_NAME.matches(ONLY_LETTERS_PATTERN)){
			errors.setLastName("Invalid charators used. ");
		}
		
		if(EMAIL.isEmpty()) {
			errors.setEmail("This field should not be empty.");
		} else {
			if(!EMAIL.matches(EMAIL_PATTERN)) {
				errors.setEmail("Invalid email address");
			}
			if(CONFIRM_EMAIL.isEmpty()) {
				errors.setEmail("This field should not be empty.");
			} else if(!EMAIL.equals(CONFIRM_EMAIL)) {
				errors.setConfirmEmail("Email does not match Confirm Email.");
			}
		}
		
		if(PHONE1.isEmpty() ||  PHONE2.isEmpty() || PHONE3.isEmpty()) {
			errors.setPhone("All 3 fields must be filled");
		} else {
			if(!tryParseInt(PHONE1) || !tryParseInt(PHONE2) || !tryParseInt(PHONE3)) {
				errors.setPhone("These fields only accept numbers.");
			}
			if(PHONE1.length() != 3 || PHONE2.length() != 3 || PHONE3.length() != 4) {
				errors.setPhone("Not the correct format (XXX)XXX-XXXX.");
			}
		}
		
		errors.setYear(
				YEAR.isEmpty() ? "Please make a selection." : "");
		
		errors.setMajor(
				MAJOR.isEmpty() ? "Please make a selection." : "");
		
		if(USERNAME.isEmpty()) {
			errors.setUsername("This field should not be empty.");
		} else if (!USERNAME.matches(ONLY_LETTERS_PATTERN)) {
			errors.setUsername("Invalid charators used. ");
		}

		if(PASSWORD.isEmpty()) { 
			errors.setPassword("This field connot be blank.");
		} else if(!PASSWORD.equals(CONFIRM_PASSWORD)){
			errors.setConfirmPassword("Password and Confirm Password did not match.");
		}
		return errors;
	}
	
	private boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	private String fillFormWithJS(HttpServletRequest request) {
		
		return "<script>" +
				"document.getElementById('firstname').value = '" + 
					request.getParameter("firstName").trim() + "';\n" +
				"document.getElementById('lastname').value = '" + 
					request.getParameter("lastName").trim() + "';\n" + 
				"document.getElementById('email').value = '" + 
					request.getParameter("email").trim().toLowerCase() + "';\n" + 
				"document.getElementById('confirmEmail').value = '" + 
					request.getParameter("confirmEmail").trim().toLowerCase() + "';\n" + 
				"document.getElementById('phone1').value = '" + 
					request.getParameter("phone1").trim() + "';\n" + 
				"document.getElementById('phone2').value = '" + 
					request.getParameter("phone2").trim() + "';\n" + 
				"document.getElementById('phone3').value = '" + 
					request.getParameter("phone3").trim() + "';\n" +
				"document.getElementById('username').value = '" + 
					request.getParameter("username").trim().toLowerCase() + 
				"';\n</script>";
	}
	private String displayErrorsWithJS(FormError errors) {
		return  "<script>" +
				"document.getElementById('mainError').innerHTML = '" + errors.getMain() + "';\n" +
				"document.getElementById('firstNameError').innerHTML = '" + errors.getFirstName() + "';\n" +
				"document.getElementById('lastNameError').innerHTML = '" + errors.getLastName() + "';\n" +
				"document.getElementById('emailError').innerHTML = '" + errors.getEmail() + "';\n" +
				"document.getElementById('confirmEmailError').innerHTML = '" + errors.getConfirmEmail() + "';\n" +
				"document.getElementById('phoneError').innerHTML = '" + errors.getPhone() + "';\n" +
				"document.getElementById('yearError').innerHTML = '" + errors.getYear() + "';\n" +
				"document.getElementById('majorError').innerHTML = '" + errors.getMajor() + "';\n" +
				"document.getElementById('usernameError').innerHTML = '" + errors.getUsername() + "';\n" +
				"document.getElementById('passwordError').innerHTML = '" + errors.getPassword() + "';\n" +
				"document.getElementById('confirmPasswordError').innerHTML = '" + errors.getConfirmPassword() + "';\n</script>";
	}
}
