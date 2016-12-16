package servlet.login;

/*
COMP3095 Web Application Development with Java
Assignment 1 - Servlets
Instructor : Sergio Santilli sergio.santilli@georgebrown.ca

by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
Date: Monday, October 10, 2016

Description:
	Login Servlet for an online journal
*/

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		LoginForm html = new LoginForm();
		response.getWriter().println(html.print());
		if(request.getParameter("username") != null){
			response.getWriter().println("<script>\ndocument.getElementById('username').value = '" + 
					request.getParameter("username") + "';\n" +
					"document.getElementById('errorMessage').innerHTML = 'The username and password is invalid<br>';\n</script>");
		}
		try{
			if(request.getParameter("logout") == null) {
				
			} else if(request.getParameter("logout").equals(new String("true"))){
				final String secret = "K2vJAdF3hJv4nhD3";
				HttpSession session = request.getSession();
				session.invalidate();
				response.getWriter().println("<script>\ndocument.getElementById('errorMessage').innerHTML = 'You have been successfully logged out!<br>';\n</script>\n");
				
				Cookie usernameCookie = new Cookie("username", "");
				Cookie passwordCookie = new Cookie(hashWithSecret("password", secret), "");
				usernameCookie.setMaxAge(0);
				passwordCookie.setMaxAge(0);
				usernameCookie.setPath("/");
				passwordCookie.setPath("/");
				response.addCookie(usernameCookie);
				response.addCookie(passwordCookie);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
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
