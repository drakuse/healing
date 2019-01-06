<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<script>
	function modify() {
		var password = memberModify.password.value;
		var passwordDB = 1234;
		if (password == passwordDB) {
			memberModify.submit();
		} else {
			alert('비밀번호가 틀립니다.');
		}
	}
</script> 
</head>
<body>
	<h1>memberInfo.jsp 파일입니다.</h1>
	<form name="memberModify" action="memberModify" method="post">
		<table border="1">
			<tr>
				<td colspan="2">회원목록</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name=id value=${memberView.id } readonly></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name=password></td>
			</tr>
			<tr>
				<td>새 비밀번호</td>
				<td><input type="password" name=newPassword></td>
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
				<td><input type="email" name="email" value=${memberView.email }></td>
			</tr>
			<tr>
			<td><input type="button" onclick="modify()" value="수정" /></td>
			</tr>
		</table>
	</form>

</body>
</html>