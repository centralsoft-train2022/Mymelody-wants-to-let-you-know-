<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.DetailBean" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細確認</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	ユーザー名:<%=bean.getUserName() %><br>
	<img src="pictures/<%=bean.getPicturePath(1) %>" title="キャラクター画像"
		class="image">
	<h1>詳細確認</h1>
	データベースからタスク情報をとってきて表示するよ

	<table border="1" width="500" cellspacing="0" cellpadding="5"
		bordercolor="#333333" class="box3">
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
		for(vo.TasksVo tv:bean.getTaskList())
		{
		%>
		<tr>
			<td><%=tv.getTaskname()%></td>
			<td><%=tv.getTaskbody()%></td>
			<td><%=tv.getKigen()%></td>
			<td><%=tv.isCompleted()? web.Comon.TRUE :web.Comon.FALSE %></td>
			<td><%=tv.getMailtime()%></td>
			<td><%=tv.isNeedmail()%></td>
			<td><%=tv.getTaskinterval()%></td>
		</tr>
	</table>
	
	<form method="POST" action="CelebrationServlet">
		<button type='submit' name='id' value="<%=bean.getTaskid() %>">達成</button>
		<br>
	</form>
	<form method="POST" action="DeleteServlet">
		<button type='submit' name='delete' value="<%=bean.getTaskid() %>">削除</button>
		<br>
	</form>
	<h1>設定変更</h1>
	<form method="POST" action="DetailDataServlet">
		<p>
			・タスク名変更<br> <input type="text" name="taskname" value=<%=tv.getTaskname()%>>
		</p>

		<p>
			・タスク内容変更<br> <input type="text" name="taskdetail" value=<%=tv.getTaskbody()%>>
		</p>

		<p>
			・メール送信日時変更<br> <input type="datetime-local" name="maildate" value=<%=tv.getKigen().replace(" ","T")%>>
		</p>
		<p>
			・アラートメール送信<br> <input type="radio" name="date1" value="Yes" checked=<%=tv.isNeedmail()%>>Yes
			<input type="radio" name="date1" value="No">No
		</p>
		<p>
			・繰り返し期間<br> <input type="number" name="month" value="0" min="0"
				max="12">カ月・ <input type="number" name="day" value="0"
				min="0" max="30">日・ <input type="number" name="hour"
				value="0" min="0" max="23">時間・ <input type="number"
				name="minutes" value="0" min="0" max="59">分
		</p>

		<Button type="submit" name="taskid" value=<%=bean.getTaskid() %>>設定を適用</Button>
	</form>

	<% }%>







	<form method="POST" action="TaskListServlet">
		<input type="submit" value="戻る">
	</form>
</body>
</html>