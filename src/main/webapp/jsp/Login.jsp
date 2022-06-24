<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.LoginBean" scope="request" />

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

			<%
			if (!bean.isWrongUserName() || !bean.isWrongPassword()) {
			%>
			<div style="color: red;" class="alart">
			メールアドレスまたはパスワードが違います
			</div>
			<%
			}
			%>
			
	<div class="box1">
		<form action="LoginServlet" method="post">

			ユーザー名<input name="Mailaddress" type="text"><br> 
			パスワード<input type="password" name="Password" size="25"><br> 
			<input type="submit" value="ログイン">
		</form>
	</div>

	<img src="pictures/<%=bean.getPicturePath(0)%>" title="キャラクター画像"
		class="image">

</body>
</html>