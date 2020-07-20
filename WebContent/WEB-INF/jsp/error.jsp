<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラーページ</title>
</head>
<body>
	<h2>エラーメッセージ</h2>
	<c:if test = "${ empty errorMessage }">
		<p>メッセージなし</p>
	</c:if>
	<p>${ errorMessage }</p>
	<br>
	<button onclick = "history.back()">戻る</button>
</body>
</html>