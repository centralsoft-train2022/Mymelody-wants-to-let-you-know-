<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:useBean id="bean" class="bean.TaskListBean" scope="request" />

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

	<div class="username">
		ユーザー名:<%=bean.getUserName()%>
	</div>


	<%
	if (bean.getAchievementFlag()) {
			%>
			<script>
			let option = 'width=300,height=300,left=200,top=200'
			let newwin = window.open("jsp/PopTest2.jsp","popUp", option);
			
			</script>
			
			<%
			}
			%>
			


	<table>
		<tr style="height: 10px;">
			<%
			for (int i = 0; i < bean.getTaskList().size(); i++) {
			%>
			<th><%=bean.getTaskList().get(i).getTaskname()%></th>
			<%
			}
			%>
		</tr>
			<%
			for (int i = 0; i < bean.getPicturePaths().size(); i++) {
			%>
		
		<td><img src="pictures/<%=bean.getPicturePaths().get(i)%>"
			title="キャラクター画像" style="width: 80%; margin: 0 auto;"></td>
		<%
		}
		%>

	</table>

	<form method="POST" action="RegisterServlet">
		<input type='submit' name='add' value='新しいタスクを追加'>
	</form>


	<h2>タスク一覧</h2>
	<form method="POST" action="DetailServlet">
		<table border="1" width="500" cellspacing="0" cellpadding="5"
			bordercolor="#333333" class="box1">
			<tr>
				<th>№</th>
				<th>タスク名</th>
				<th>達成状況</th>
				<%
				int count = 0;
				for (vo.TasksVo tv : bean.getTaskList()) {
					count++;
				%>
			
			<tr>
				<td><%=count%><br></td>
				<td>
					<button type='submit' name='edit' value=<%=tv.getTaskid()%>><%=tv.getTaskname()%></button>
					<br>
				</td>
				<td><%=tv.isCompleted() ? web.Comon.TRUE : web.Comon.FALSE%> <br>
				</td>

				<%
				}
				%>
			</tr>

		</table>
	</form>
</body>
</html>