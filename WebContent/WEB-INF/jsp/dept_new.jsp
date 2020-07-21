<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部署データの新規作成</title>
</head>
<body>
	<h2>部署データを新規作成します</h2>
	<form action = "./EmployeeDatabase" method = "post">
		<p>部署ID：<input type = "text" name = "deptID" placeholder = "1~99">
		<p>部署名：<input type = "text" name ="deptName" placeholder = "入力してください。"></p>
		<input type = "hidden" name = "action" value = "action.DeptAddLogic" />
		<input type = "submit" value = "設定">
	</form>
	<button onclick = "history.back()">戻る</button>
</body>
</html>