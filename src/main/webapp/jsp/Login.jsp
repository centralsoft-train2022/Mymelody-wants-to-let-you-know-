<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.LoginBean" scope="request"/>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>マイメロディは知らせたい</title>
<link rel="stylesheet" href="style.css">

</head>

<body>
<br>
	<p>
		<img src="pictures/kaguya.png" title="タイトル" class="topimage">
	</p>
	
	<div class="box1">
		<form action="LoginServlet" method="post">

			ユーザー名<input name="ID" type="text"><br>
			パスワード<input name="PW" type ="text"><br>
			 <input type="submit" value="ログイン">
			 <input type = "hidden" name = "fromjsp" value = "loginjsp">
		</form>
	</div>
	
	<img src="pictures/<%=bean.getPicturePath(0) %>" title="キャラクター画像" class="image">

</body>
</html>