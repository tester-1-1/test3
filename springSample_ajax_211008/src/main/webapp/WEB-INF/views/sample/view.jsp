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
	<h2>springtest 테이블 데이터 상세조회 화면</h2>
	<table border="1">
		<tr>
			<th>제목</th>
			<td colspan="3"><%=vo.getStitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=vo.getSwriter() %></td>
			<th>등록일</th>
			<td><%=vo.getSdate() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3"><%=vo.getScontents() %></td>
		</tr>
	</table>
	<input type="button" value="수정" onclick="location.href='modify.do?sidx=<%=vo.getSidx()%>'">
	<input type="button" value="삭제" onclick="location.href='delete.do?sidx=<%=vo.getSidx()%>'">
</body>
</html>