<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.Gender"%>
<%@ page import="bean.Prefecture"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員データの新規作成</title>
</head>
<body>
	<h2>社員データを新規作成します</h2>
	<form action="./EmployeeDatabase" method="post" enctype="multipart/form-data">
		<p>社員ID：<input type="text" name="empID" placeholder="1~99999"></p>
		<p>名前：<input type="text" name="empName" placeholder="名字 名前"></p>
		<p>年齢：<input type="text" name="age" placeholder="00"></p>
		<p>性別：
			<input type="radio" name="gender" value="${ Gender.MALE.text }">男性
			<input type="radio" name="gender" value="${ Gender.FEMALE.text }">女性
		</p>
		<p>写真：<input type="file" name="picture" accept="image/png,image/jpeg"></p>
		<p>郵便番号：<input type="text" name="zipcode" placeholder="000-0000"></p>
		<p>都道府県：
			<select name="prefecture">
				<c:forEach var="pref" items="${ Prefecture.values() }">
					<option value="${ pref.fullText }">${ pref.fullText }</option>
				</c:forEach>
			</select>
		</p>
		<p>住所：<input type="text" name="address" placeholder="入力してください"></p>
		<p>所属：
			<select name="deptID">
				<c:forEach var="dept" items="${ deptAllList }">
					<option value="${ dept.deptID }">${ dept.deptName }</option>
				</c:forEach>
			</select>
		</p>
		<p>入社日：<input type="date" name="dateEntering" placeholder="YYYY-MM-DD"></p>
		<p>退社日：<input type="date" name="dateRetired" placeholder="YYYY-MM-DD"></p>
		<input type="hidden" name="action" value="action.EmpAddLogic" />
		<input type="submit" value="設定">
	</form>
	<br>
	<button onclick="history.back()">戻る</button>
</body>
</html>