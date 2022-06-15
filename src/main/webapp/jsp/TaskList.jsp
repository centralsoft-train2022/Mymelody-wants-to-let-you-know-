<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.TaskListBean" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	</form>
	
	<h2>タスク一覧</h2>

	<form method="POST" action="DetailServlet">
		タスク名<input type='submit' name='edit' value='タスク名'>
		<%=bean.getTaskName()%>
	</form></body>
</html>