<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>部署データベース管理</title>
</head>
<body>
	<h2>部署一覧：</h2>
	<c:if test="${ !empty message }">
		<p>${ message }</p>
	</c:if>
	<table border = "1">
		<tr>
			<th>ID</th>
			<th>部署名</th>
		</tr>
		<c:forEach var = "dept" items = "${ deptAllList }">
			<tr>
				<td><c:out value="${ dept.deptID }" /></td>
				<td><c:out value="${ dept.deptName }" /></td>
				<td>
					<form action = "./EmployeeDatabase" method = "post">
						<input type = "hidden" name = "action" value = "action.DeptEditLogic" />
						<input type = "hidden" name = "deptID" value = "${ dept.deptID }">
						<input type = "submit" value = "編集">
					</form>
				</td>
				<td>
					<form action = "./EmployeeDatabase" method = "post">
						<input type = "hidden" name = "action" value = "action.DeptDeleteLogic" />
						<input type = "hidden" name = "deptID" value = "${ dept.deptID }">
						<input type = "submit" value = "削除">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<form action = "./EmployeeDatabase" method = "post">
		<input type = "hidden" name = "action" value = "action.DeptNewLogic" />
		<input type = "submit" value = "新規追加">
	</form>
	<br>
	<button onclick = "history.back()">戻る</button>

</body>
</html>