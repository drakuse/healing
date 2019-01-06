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
			<td>삭제</td>
		</tr>
		<!-- items = 반복하고자 하는 원본 데이터 -->
		<!-- var = 반복용변수 -->
		<c:forEach var="member" items="${memberList}">
			<c:if test="${member.id ne 'admin' }">
				<tr>
					<td><a href="memberView?id=${member.id}">${member.id }</a></td>
					<td><a href="memberDelete?id=${member.id}">삭제</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</body>
</html>