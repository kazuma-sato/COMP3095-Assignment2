package post.controller;
/*
 * COMP3095 Project
 * Kazuma Sato 100 948 212
 * Mark Wheeler-Gallant 100 800 311
 * 
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import post.service.PostService;;

@WebServlet("/NewPost")
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewPostServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username;
		String title;
		String bodyText;
		String entryType;
		String commentParentPost;
		
		PostService newPostService = new PostService();
		
		username = request.getParameter("username");
		bodyText = request.getParameter("bodyText");
		entryType = request.getParameter("entryType");
		
		
		if(entryType.equalsIgnoreCase("comment")) {
			commentParentPost = request.getParameter("parentPost");
			if(!newPostService.insertPost(username, bodyText, entryType, commentParentPost)){
				response.sendRedirect("NewPost.jsp");
				return;
			}
		} else { 
			title = request.getParameter("title");
			if(!newPostService.insertPost(username, bodyText, entryType, title)){
				response.sendRedirect("NewComment.jsp");
				return;
			}
		}
		response.sendRedirect("PostSuccess.jsp");
	}
		

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String type;
		
		type = request.getParameter("type");
		if(type.isEmpty()){
			response.sendRedirect("/login");
		} else {
			response.sendRedirect("/login?type="+type);
		}
	}
}
