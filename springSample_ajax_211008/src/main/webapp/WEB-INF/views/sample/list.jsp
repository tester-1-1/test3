<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="edu.study.vo.*" %>
<%
	List<SampleVO> list = (List<SampleVO>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>springtest 테이블 목록</h2>
	<table border="1">
		<tr>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	<%
		if(list.size()>0){
			for(SampleVO vo : list){
	%>
		<tr>
			<td><a href="view.do?sidx=<%=vo.getSidx()%>"><%=vo.getStitle() %></a></td>
			<td><%=vo.getSwriter()%></td>
			<td><%=vo.getSdate()%></td>
		</tr>
	<%
			}
		}else{
	%>
		<tr>
			<th colspan="3">데이터가 존재하지 않습니다.</th>
		</tr>
	<%
		}
	%>
	</table>
	<!-- / 안 붙이면 경로의 뒤에 이어서 붙는다. -->
	<button onclick="location.href='save.do'">등록</button>
</body>
</html>