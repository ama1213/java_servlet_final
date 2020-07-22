<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員データベース管理ページ</title>
</head>
<body>
	<h3>社員一覧：</h3>
	<p>${ message }</p>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>名前</th>
		</tr>
		<c:forEach var="emp" items="${ empAllList }">
			<tr>
				<td><c:out value="${ emp.empID }" /></td>
				<td><c:out value="${ emp.empName }" /></td>
				<td>
					<form action="./EmployeeDatabase" method="post">
						<input type="hidden" name="action" value="action.EmpEditLogic">
						<input type="hidden" name="empID" value="${ emp.empID }">
						<input type="submit" value="編集">
					</form>
				</td>
				<td>
					<form action="./EmployeeDatabase" method="post">
						<input type="hidden" name="action" value="action.EmpDeleteLogic">
						<input type="hidden" name="empID" value="${ emp.empID }">
						<input type="submit" value="削除">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<form action="./EmployeeDatabase" method="post">
		<input type="hidden" name="action" value="action.EmpNewLogic" />
		<input type="submit" value="新規追加">
	</form>
	<br>
	<form action="./EmployeeDatabase" method="post">
		<input type="hidden" name="action" value="search" />
		<input type="submit" value="検索...">
	</form>
	<br>
	<form action="./EmployeeDatabase" method="post">
		<input type="hidden" name="action" value="action.CreateEmpsCSVLogic" />
		<input type="submit" value="CSVファイルに出力">
	</form>
	<br>
	<button onclick="history.back()">戻る</button>
</body>
</html>