<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.CelebrationBean" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>お祝い画面</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="username">
	ユーザー名:<%=bean.getUserName() %>
</div>
	<img src="pictures/<%=bean.getPicturePath(0) %>" title="キャラクター画像" class="image">
	<form method="POST" action="TaskListServlet">
		<h1>お祝い画面</h1>
		<input type="submit" value="一覧表示画面に戻るよ！">

	</form>
</body>
</html>