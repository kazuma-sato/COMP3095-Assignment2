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

@WebServlet("/NewPost")
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewPostServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username;
		String bodyText;
		String entryType;
		String commentParentPost;
		
		username = request.getParameter("username");
		bodyText = request.getParameter("bodyText");
		entryType = request.getParameter("entryType");
		
		if(entryType.matches("comment")) {
			commentParentPost = request.getParameter("parentPost");
		}
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
