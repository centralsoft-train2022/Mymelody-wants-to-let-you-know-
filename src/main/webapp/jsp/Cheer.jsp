<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.CheerBean" scope="request" />

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>がんばれ！！</title>
<link rel="stylesheet" href="style.css">
</head>

<body>
	<div class="username">
		ユーザー名:<%=bean.getUserName()%>
	</div>
	<img src="pictures/<%=bean.getPicturePath(0)%>" title="キャラクター画像"
		class="image">
		<br>
	<div style="align: center;" class="balloon1-top">
		<p><%=bean.getMessage()%></p>
	</div>

	<form method="POST" action="TaskListServlet">
		<input type="submit" value="一覧表示画面" >
	</form>
</body>
</html>