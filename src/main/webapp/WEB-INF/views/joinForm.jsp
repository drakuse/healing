<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	/* 자바 스크립트 합수 선언 */
	function passConfirm() {
		/* 비밀번호, 비밀번호 확인 입력창에 입력된 값을 비교해서 같다면 비밀번호 일치,
		다르면 비밀번호 불일치라는 텍스트를 출력함.*/
		var password = document.getElementById('password');
		var passwordConfirm = document.getElementById('passwordConfirm');
		var confirmMsg = document.getElementById('confirmMsg');
		var correctColor = "#00ff00";
		var wrongㄴColor = "#ff0000";

		if (password.value == passwordConfirm.value) {
			confirmMsg.style.color = correctColor;
			confirmMsg.innerHTML = "비밀번호 일치";
		} else {
			confirmMsg.style.color = wrongColor;
			confirmMsg.innerHTML = "비밀번호 불일치";
		}
	}

	/* ajax(Asynchronus javascript and XML) */
	function idOverlap() {
		console.log("idOverlap 함수 호출");
		$.ajax({
			type : "post",
			url : "idOverlap",
			data : {
				"id" : joinForm.id.value
			/* "파라미터 이름" : 파라미터 값 */
			},
			/* 내가 전송할 타입 */
			dataType : "text",

			/* success = ajax 성공여부 판단.
				성공이 되면 if문 실행이되고,
				실패면 error 부분이 실행됨.
			 */
			success : function(data) {
				if (data == "1") {
					alert("이 아이디는 사용 가능합니다.");
				} else {
					alert("이 아이디는 사용할 수 없습니다.");
				}
			},
			error : function() {
				alert("아이디 중복확인 ajax 실패");
			}
		});
	}
</script>
<title>회원 가입 폼</title>
</head>
<body>
	<!-- 회원가입 폼 -->
	<div>
		<form name="joinForm" action="memberJoin" method="post" id="joinForm">
			<table border="1">
				<tbody>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id" id="id" /><input
							type="button" onclick="idOverlap()" value="중복확인" /></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="password" id="password"></td>
					</tr>
					<tr>
						<td>비밀번호확인</td>
						<td><input type="password" name="passwordConfirm"
							id="passwordConfirm" onkeyup="passConfirm()" /><span
							id="confirmMsg"></span></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="name" id="name"></td>
					</tr>
					<tr>
						<td>나이</td>
						<td><input type="text" name="age" id="age"></td>
					</tr>
					<tr>
						<td>성별</td>
						<td><input type="radio" name="gender" id="gender" value="남"
							checked /> 남자 <input type="radio" name="gender" id="gender"
							value="여" />여자</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="email" name="email" id="email" size="20"></td>
					</tr>
					<tr>
						<td><input type="submit" value="회원가입" /></td>
						<td><input type="reset" value="다시작성" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>