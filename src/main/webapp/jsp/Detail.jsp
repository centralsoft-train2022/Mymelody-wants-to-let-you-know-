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
	<div class="username">
		ユーザー名:<%=bean.getUserName()%>
	</div>
	<img src="pictures/<%=bean.getPicturePath(0)%>" title="キャラクター画像"
		class="image">
	<h1>詳細確認</h1>

	<table border="1" width="500" cellspacing="0" cellpadding="5"
		bordercolor="#333333" class="box3">
		<tr>
			<th>タスク名</th>
			<th>タスク内容</th>
			<th>期限</th>
			<th>達成状況</th>

			<%
			if (bean.getTask().isNeedmail()) {
			%>
			<th>メール送信日時</th>
			<%
			}
			%>

			<%
			if (bean.getTask().isRegular()) {
			%>
			<th>繰り返し期間</th>
			<%
			}
			%>

		</tr>

		<tr>
			<%String isRegular = String.valueOf(bean.getTask().isRegular()); %>
			<%String Mailtime = String.valueOf(bean.getTask().getMailtime());%>
			
			<%String Month =  bean.getTask().getTaskinterval().split("[- :]")[1]; %>
			<%String Day =  bean.getTask().getTaskinterval().split("[- :]")[2]; %>
			<%String Hour =  bean.getTask().getTaskinterval().split("[- :]")[3]; %>
			<%String Minutes =  bean.getTask().getTaskinterval().split("[- :]")[4]; %>
			<td><%=bean.getTask().getTaskname()%></td>
			<td><%=bean.getTask().getTaskbody()%></td>
			<td><%=bean.getTask().getKigen()%></td>
			<td><%=bean.getTask().isCompleted() ? web.Comon.TRUE : web.Comon.FALSE%></td>
			<%
			if (bean.getTask().isNeedmail()) {
			%>
			<td><%=bean.getTask().getMailtime()%></td>
			<%
			}
			%>

			<%
			if (bean.getTask().isRegular()) {
			%>
			<td><%=bean.getTask().getTaskinterval()%></td>
			<%
			}
			%>
		</tr>
	</table>

	<form method="POST" action="CelebrationServlet">
		<button type='submit' name='id'
			value="<%=bean.getTask().getTaskid()%>">達成</button>
		<br>
	</form>
	<form method="POST" action="DeleteServlet">
		<button type='submit' name='delete'
			value="<%=bean.getTask().getTaskid()%>">削除</button>
		<br>
	</form>
	<h1>設定変更</h1>
	<form method="POST" action="DetailDataServlet">

		<p>
			・タスク名変更<br> <input type="text" name="taskname"
				value=<%=bean.getTask().getTaskname()%>>
		</p>

		<p>
			・タスク内容変更<br> <input type="text" name="taskdetail"
				value=<%=bean.getTask().getTaskbody()%>>
		</p>

		<p>
			・タスク期限<br> <input type="datetime-local" name="tasktime"
				value=<%=bean.getTask().getKigen().replace(" ", "T")%>>
		</p>

		<p>
			・アラートメール送信の有無<br> 
			<input type="radio" name="needmail"value="true" <%=bean.getTask().isNeedmail() ? "checked" : ""%>>Yes
			<input type="radio" name="needmail" value="false"<%=!bean.getTask().isNeedmail() ? "checked" : ""%>>No
		</p>

		<p>
			・メール送信日時変更<br> 
			<input type="datetime-local" name="maildate"value=<%=Mailtime != null? Mailtime.replace(" ", "T"):""%>>
		</p>

		<p>
			・繰り返し設定の有無<br> 
			<input type="radio" name="regular" value="true"<%= isRegular.equals("true")? "checked" : ""%>>Yes 
			<input type="radio" name="regular" value="false" <%= isRegular.equals("false") ? "checked" : ""%>>No
		</p>

		<p>
			・繰り返し期間<br> 
			<input type="number" name="month" value="<%=Month%>" min="0"max="12">カ月・ 
			<input type="number" name="day" value="<%=Day%>"min="0" max="30">日・ 
			<input type="number" name="hour"value="<%=Hour%>" min="0" max="23">時間・ 
			<input type="number"name="minutes" value="<%=Minutes%>" min="0" max="59">分
		</p>

		<Button type="submit" name="taskid"
			value=<%=bean.getTask().getTaskid()%>>設定を適用</Button>
	</form>







	<form method="POST" action="TaskListServlet">
		<input type="submit" value="戻る">
	</form>
</body>
</html>