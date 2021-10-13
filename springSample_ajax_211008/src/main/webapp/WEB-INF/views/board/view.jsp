<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="edu.study.vo.*" %>
<%
	BoardVO vo = (BoardVO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	게시판 상세보기 화면입니다.
	<br>
	<a href="modify.do?bidx=<%=vo.getBidx() %>">/Board/modify.do로 이동합니다.</a>
	
	<form action="/Board/view.do" method="post">
		<table border=1 style="width:400px">
			<tr>
				<td width="100px">제목</td>
				<td><%=vo.getTitle() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><%=vo.getContent()%></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><%=vo.getWriter()%></td>
			</tr>
		</table>
		<!--	밑과 동일하게 작동한다
		<input type="button" name="bidx" value="삭제" onClick="location.href='delete.do?bidx=<%=vo.getBidx()%>'">
		-->
		<button type="button" onclick="location.href='delete.do?bidx=<%=vo.getBidx() %>'">삭제</button>
	</form>
</body>
</html>