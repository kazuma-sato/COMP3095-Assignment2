<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Post | Blog</title>
</head>
<body>
	<form method="POST" action="NewPostServlet">
		<input type="text" name="title">
		<input type="text" name="bodyText" />
		<input type="submit" />
		<input type="hidden" name="entrytype" value="post">
		<input type="hidden" name="username" value="<% session.getAttribute("username"); %>" />
	</form>
</body>
</html>