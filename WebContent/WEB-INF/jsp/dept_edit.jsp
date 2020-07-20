<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部署データ修正</title>
</head>
<body>
	<h2>部署データを修正します</h2>
	<form action = "./EmployeeDatabase" method = "post">
		<input type = "hidden" name = "deptID" placeholder = "1~99" value = "${ deptEdited.deptID }">
		<p>部署名：<input type = "text" name = "deptName" placeholder = "入力してください" value = "${ deptEdited.deptName }"></p>
		<input type = "hidden" name = "action" value = "action.DeptUpdateLogic" />
		<input type = "submit" value = "設定">
	</form>
	<br>
	<button onclick = "history.back()">戻る</button>
</body>
</html>