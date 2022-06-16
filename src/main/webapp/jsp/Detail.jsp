<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="bean" class="bean.DetailBean" scope="request"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細確認</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<img src="pictures/<%=bean.getPicturePath(1) %>" title="キャラクター画像" class="image">
	<h1>詳細確認</h1>
	データベースからタスク情報をとってきて表示するよ
	
	<table  border="1" width="500" cellspacing="0" cellpadding="5" bordercolor="#333333" class="box1">
		<tr>
			<th>タスク名</th>
			<th>タスク内容</th>
			<th>期限</th>
			<th>達成状況</th>
			<th>メール送信日時</th>
			<th>アラートメール送信</th>
			<th>繰り返し期間</th>
		</tr>
		
		<%
		for(Vo.TasksVo tv:bean.getTaskList())
		{
		%>
		<tr>
			<td><%=tv.getTaskname()%></td>
			<td><%=tv.getTaskbody()%></td>
			<td><%=tv.getKigen()%></td>
			<td><%=tv.isCompleted()%></td>
			<td><%=tv.getKigen()%></td>
			<td><%=tv.isNeedmail()%></td>
			<td><%=tv.getTaskinterval()%></td>
		</tr>
		<% }%>
		
	
	</table>
	
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
	<p>・繰り返し期間<br>
		<input type="number" name="month" value="0" min="0">カ月・
		<input type="number" name="day" value="0" min="0">日・
		<input type="number" name="hour" value="0" min="0">時間・
		<input type="number" name="minutes" value="0" min="0">分
	</p>
	
	
	
	<form  method="POST" action="TaskListServlet">
		<input type = "submit" value="設定を適用">
	</form>
	<form  method="POST" action="TaskListServlet">
		<input type = "submit" value="戻る">
	</form>
</body>
</html>