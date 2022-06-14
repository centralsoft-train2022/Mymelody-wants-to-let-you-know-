<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
	<meta charset="UTF-8">
	<title>ホーム画面</title>
</head>
<body>
	<form  method="POST" action="RegisterServlet">
	    <input type = 'submit' name = 'add' value = '追加'>
	</form>
	
	
	<h2>タスク一覧</h2>
	<table>
		<tr>
			<th>タスク名</th>
			<th>達成状況</th>
		</tr>
		
		<tr>
			<form  method="POST" action="DetailServlet">
				<td><input type = 'submit' name = 'edit' value = 'タスク名'></td>
				<td></td>
			</form>
		</tr>
	
	
	
	
	
	</table>
</body>
</html>