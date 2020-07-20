<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員データベース管理</title>
</head>
<body>
	<h2>社員一覧：</h2>
	<c:choose>
		<c:when test = "${ fn:length(searchedEmpList) == 0 }">
			<p>登録されている社員がいません。</p>
		</c:when>
		<c:when test = "${ fn:length(searchedEmpList) != 0 }">
			<table border = "1">
				<tr>
					<th>ID</th>
					<th>名前</th>
				</tr>
				<c:forEach var = "employee" items = "${ searchedEmpList }">
					<tr>
						<td><c:out value = "${ employee.empID }" /></td>
						<td><c:out value = "${ employee.empName }" /></td>
						<td>
							<form action = "./EmployeeDatabase" method = "post">
								<input type = "hidden" name = "action" value = "action.EmpEditLogic" />
								<input type = "hidden" name = "empID" value = "${ employee.empID }">
								<input type = "submit" value = "削除">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<form action = "#" method = "post">
				<input type = "hidden" name = "action" value = "search" />
			</form>
		</c:when>
	</c:choose>
	<br>
	<button onclick = "history.back()">戻る</button>
</body>
</html>