<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String url = (String)request.getAttribute("url");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=url %>
	<!-- 가상경로가 /Free/modifyOk.do와 일치해야한다 -->
	<form action="/Free/modify.do" method="post">
		제목 : <input type="text" name="t1">
		<br><br>
		내용 : <textarea name="t2"></textarea>
		<br><br>
		testField : <input type="text" name="testField">
		<br><br>
		<!-- button태그에 타입을 안 주면 기본 submit이다. -->
		<button>저장</button>
	</form>
</body>
</html>