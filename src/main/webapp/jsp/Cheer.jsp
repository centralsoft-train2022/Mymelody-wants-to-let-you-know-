<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.CheerBean" scope="request"/>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>がんばれ！！</title>
<link rel="stylesheet" href="../style.css">
</head>


<body>
	<img src="../pictures/シナモロール1.png" title="シナモロール" class="image">
	<form method="POST" action="TaskListServlet">
		がんばれ！！<input type="submit" value="一覧表示画面に戻るよ！">
	</form>
</body>
</html>