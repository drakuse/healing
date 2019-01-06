<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록 페이지</title>
</head>
<body>
	<h1>memberList.jsp 파일입니다.</h1>
	<table border="1">
		<tr>
			<td colspan="2">회원목록</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${memberView.id }</td>		
		</tr>
		<tr>
			<td>이름</td>
			<td>${memberView.name }</td>		
		</tr>
		<tr>
			<td>나이</td>
			<td>${memberView.age }</td>		
		</tr>
		<tr>
			<td>성별</td>
			<td>${memberView.gender }</td>		
		</tr>
		<tr>
			<td>이메일</td>
			<td>${memberView.email }</td>		
		</tr>
	</table>
</body>
</html>