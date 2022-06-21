<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.PopTestBean" scope="request" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>テスト１</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<img src="pictures/シナモロール1.png" title="シナモロール" class="image">

	<h1>ポップアップ表示前画面</h1>
	<script>
	<%
	if (bean.getAchievementFlag()) {
			%>
			
			let option = "width=300,height=300,left=200,top=200"
			let newwin = open("https://www.google.co.jp/","mywindow", option);
			
			
			<%
			}
			%>
			</script>


</body>
</html>