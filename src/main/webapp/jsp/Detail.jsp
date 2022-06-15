<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細確認</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>詳細確認</h1>
	データベースからタスク情報をとってきて表示するよ
	
	<form  method="POST" action="html/Celebration.html">
		<input type = "submit" value="達成">
	</form>
	<h1>設定変更</h1>
	<p>・タスク名変更<br>
		<input type="text" name="name">
	</p>
	<p>・メール送信日時変更<br>
		<input type="datetime-local" name="date">
	</p>
	<p>・アラートメール送信<br>
		<input type="radio" name="date1" value="Yes">Yes
		<input type="radio" name="date1" value="No">No
	</p>
	<p>・アラートの繰り返し<br>
		<input type="radio" name="date2" value="Yes">Yes
		<input type="radio" name="date2" value="No">No
	</p>
	
	
	
	<form  method="POST" action="TaskList">
		<input type = "submit" value="設定を適用">
	</form>
	<form  method="POST" action="TaskList">
		<input type = "submit" value="戻る">
	</form>
</body>
</html>