<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:useBean id="bean" class="bean.RegisterBean" scope="request"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<img src="pictures/<%=bean.getPicturePath(0) %>" title="キャラクター画像" class="image">
<h1>登録画面</h1>
	<form method="POST" action="html/Cheer.html">
		<input type="submit" value="登録！">
	</form>
</body>
</html>