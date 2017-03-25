<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, post.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="post.model.User" scope="request" />
<jsp:useBean id="posts" class="java.util.List<post.model.Post>" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome <jsp:getProperty property="username" name="user" /> !</title>
</head>
<body>
	<header>
		<h1>Welcome <jsp:getProperty property="firstName" name="user" /> </h1>
	</header>
	<nav>
		<a href="/newPost">New Post</a>
	</nav>
		<c:forEach items="${posts}" var="${post}">
			<article>
				<h2><c:out value="${post.title}"></c:out></h2>
				<detail>
					<c:out value="${post.author.username}"></c:out>
					<br>
					<c:out value="${post.date}"></c:out>
				</detail>
				<main>
					<c:out value="${post.bodyText}"></c:out>
				</main>
				<a href="/newComment">Post Comment</a>
				<c:forEach items="#{post.comments}" var="#{comment}">
					<main>
						<c:out value="${comment.bodyText}"></c:out>
					</main>
					<detail>
						<c:out value="${comment.author.username}"></c:out>
						<br>
						<c:out value="${comment.date}"></c:out>
					</detail>
				</c:forEach>
			</article>
		</c:forEach>
</body>
</html>