<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Comment | Blog</title>
</head>
<body>
	<article>
		<section id="post">
			<% //generate post info here %>
		</section>
		<section id="comments">

		</section>
	</article>
	
	<form method="POST" action="/CreateEntryServlet">
		
		<input type="text" name="bodyText" />
		<input type="submit" />
		<input type="hidden" name="entrytype" value="comment">
		<input type="hidden" name="username" value="<% session.getAttribute("username"); %>" />
		<input type="hidden" name="parentpost" value="<% //Post ID goes here %>">
	</form>
</body>
</html>