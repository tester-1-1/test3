<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="edu.study.vo.*" %>
<%
	SampleVO vo = (SampleVO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>springtest 테이블 데이터 수정 화면</h2>
	<form action="modify.do" method="post">
		제목 : <input type="text" name="stitle" value="<%=vo.getStitle()%>"><br>
		작성자 : <input type="text" name="swriter" value="<%=vo.getSwriter()%>"><br>
		내용 : <textarea name="scontents"><%=vo.getScontents()%>"</textarea><br>
		<input type="hidden" name="sidx" value="<%=vo.getSidx() %>" >
		<input type="submit" value="수정">
	</form>
</body>
</html>