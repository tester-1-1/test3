<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String testData = (String)request.getAttribute("test1");
%>
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
	가상경로 Free/list.do 로 보여지는 화면입니다!!
	||
	<%=testData %>
	<br>
	<%=url %>
	<br>
	<a href="view.do">view.do로 이동</a>
</body>
</html>