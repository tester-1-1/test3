<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="modify.do" method="post">
		이름 : <input type="text" name="name">
		<br><br>
		나이 : <input type="number" name="age">
		<br><br>
		주소 : <textarea name="addr"></textarea>
		<br><br>
		<!-- button태그에 타입을 안 주면 기본 submit이다. -->
		<button>저장</button>
	</form>
</body>
</html>