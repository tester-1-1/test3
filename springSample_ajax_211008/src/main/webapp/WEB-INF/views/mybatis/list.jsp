<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="edu.study.vo.*" %>
<%
	List<BoardUseMyBatisVO> list = (List<BoardUseMyBatisVO>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		for(BoardUseMyBatisVO vo : list){
	%>
		<%=vo.getSubject() %><br>
	<%
		}
	%>

</body>
</html>