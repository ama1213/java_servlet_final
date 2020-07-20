<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員データベース検索ページ</title>
</head>
<body>
	<h2>条件を指定して社員情報を検索します。</h2>
	<form action = "./EmployeeDatabase" method = "post">
		<input type = "hidden" name = "action" value = "action.EmpSearchLogic">
		<p>所属部署：
			<select name = "searchDeptID">
				<option value = "0">未指定</option>
				<c:forEach var="dept" items="${ deptAllList }">
					<option value = "${ dept.deptID }">${ dept.deptName }</option>
				</c:forEach>
			</select>
		</p>
		<p>
			社員ID：<input type = "text" name = "searchEmpID">
		</p>
		<p>
			名前に含む文字：<input type = "text" name = "searchEmpName">
		</p>
		<input type = "submit" value = "検索">
	</form>
	<br>
	<button onclick = "history.back()">戻る</button>
</body>
</html>