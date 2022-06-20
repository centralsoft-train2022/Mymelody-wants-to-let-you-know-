<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.TaskListBean" scope="request"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
	<meta charset="UTF-8">
	<title>ホーム画面</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<img src="pictures/<%=bean.getPicturePath(0) %>" title="キャラクター画像" class="image">
	<form  method="POST" action="RegisterServlet">
	    <input type = 'submit' name = 'add' value = '新しいタスクを追加'>
	</form>
	
	
	<h2>タスク一覧</h2>
	<form  method="POST" action="DetailServlet" >
		<table  border="1" width="500" cellspacing="0" cellpadding="5" bordercolor="#333333" class="box1">
			<tr>
				<th>№</th>
				<th>タスク名</th>
				<th>達成状況</th>
			</tr>
		
			<tr>

				<%int count = 0;
				for(vo.TasksVo tv:bean.getTaskList())
				{
					count++;
				%>
				<tr>
					<td>
						<%=count %><br>
					</td>
					<td>
						<button type = 'submit' name = 'edit' value =<%=tv.getTaskid() %>><%=tv.getTaskname() %></button><br>
					</td>
					<td>
						<%=tv.isCompleted()? web.Comon.TRUE :web.Comon.FALSE %> <br>
					</td>
				
				<% }%>
			</tr>
				
		</table>
	</form>
</body>
</html>