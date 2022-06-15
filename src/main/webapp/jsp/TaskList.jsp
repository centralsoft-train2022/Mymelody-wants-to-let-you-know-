<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.TaskListBean" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム画面</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	</form>
	
	<h2>タスク一覧</h2>
	<table>
		<tr>
			<th>タスク名</th>
			<th>達成状況</th>
		</tr>
		
		<tr>
			<form  method="POST" action="DetailServlet">
				<td><input type = 'submit' name = 'edit' value = '<%=bean.getTaskName() %>'></td>
				<td><%=bean.getTaskName() %></td>
			</form>
		</tr>
	
	</table>
</body></html>