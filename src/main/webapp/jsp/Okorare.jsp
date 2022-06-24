<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="bean.OkorareBean" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>怒られ</title>
<link rel="stylesheet" href="../style.css">
</head>
<body>


	<img src="pictures/<%=bean.getPicturePath(0)%>" title="キャラクター画像" class="image"><br>
	<div style="align: center;" class="balloon1-top">
	ぶち殺す
	</div>
	

</body>
</html>
