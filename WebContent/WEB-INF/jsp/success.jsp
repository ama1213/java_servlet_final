<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成功ページ</title>
</head>
<body>
	<c:if test = "${ not empty message }">
		<h2>${ message }</h2>
	</c:if>
	<form action = "./EmployeeDatabase" method = "post">
		<input type = "hidden" name = "action" value = "top" />
		<input type = "submit" value = "トップページへ">
	</form>
</body>
</html>