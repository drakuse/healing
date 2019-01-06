<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>
	<!-- 로그인 폼 -->
	<div>
		<form name="loginForm" action="memberLogin" method="post"
			id="loginForm">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" id="id"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" id="password"></td>
				</tr>
				<tr>
					<td><a href="javascript:loginForm.submit()">로그인</a></td>
					<td><a href="joinForm">회원가입</a></td>
				</tr>
				<tr>
					<td><input type="button" onclick="login()" value="로그인">
					<td><input type="button" onclick="join()" value="회원가입">
				</tr>
			</table>
		</form>
	</div>
	<script>
		function login() {
			loginForm.submit();
		}
		function join() {
			location.href = "joinForm";
		}
	</script>
</body>
</html>