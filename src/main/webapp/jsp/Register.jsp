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

	<form method="POST" action="RegisteredServlet" class="box1">
	<dl>
		<dt>タスク名</dt>
		<dd><input name="name" type="text" ></dd>
		<dt>タスク詳細</dt>
		<dd><input name="detail" type="text" ></dd>
		<dt>タスク期限</dt>
		<dd><input name="datekigen" type="date">
			<input name="timekigen" type="time"></dd>
		<dt>アラートメール送信</dt>
		<dd><input name="mailbutton" type="radio" value="true">はい 
			<input name="mailbutton" type="radio" value="false">いいえ</dd>
		<dt>メールの送信日時</dt>
		<dd><input name="datemailtime" type="date" >
			<input name="timemailtime" type="time" ></dd>
		<dt>繰り返し設定</dt>
		<dd><input name="regular" type="radio" value="true">はい
		 	<input name="regular" type="radio" value="false">いいえ</dd>
		<dt>繰り返し間隔</dt>
		<dd><input name="month" type="number" value="0" min="0" max="12">か月
			<input name="day" type="number" value="0" min="0" max="30">日
			<input name="hour" type="number" value="0" min="0" max="23">時
			<input name="min" type="number" value="0" min="0" max="59">分</dd>
		</dl>
		<input type="submit" value="登録！">
	</form>
	
	
</body>
</html>