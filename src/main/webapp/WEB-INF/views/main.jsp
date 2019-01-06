<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<h1>main.jsp</h1>

	<form name="logoutForm" action="memberLogout" method="get">
		<h3>세션 아이디 확인 : ${sessionScope.loginId } 님이 로그인 하셨습니다.</h3>
		<br>
		<h3>mav.addObject 확인 : ${loginMember.id }</h3>
		<!-- if문의 역할 test 부분은 조건문에 해당 -->
		<c:choose>
			<c:when test="${sessionScope.loginId eq 'admin' }">
				<a href="memberList">관리자 모드 접속(회원목록보기)</a>

			</c:when>
			<c:otherwise>
				<a href="memberInfo">회원정보수정</a>
			</c:otherwise>
		</c:choose>
		<a href="memberLogout">로그아웃</a> <input type="button"
			onclick="logout()" value="로그아웃">
	</form>
	<script>
		function logout() {
			logoutForm.submit();
		}
	</script>
</body>
</html>