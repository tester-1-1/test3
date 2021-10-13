<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- <button>태그로 데이터들을 다른곳으로 이동시키기 위해서 <form>태그로 감싸줘야한다. -->
	<form action="/Board/modify.do" method="post">
		제목 : <input type="text" name="title" value="<%=vo.getTitle() %>">
		<br><br>
		작성자 : <input type="text" name="writer" value="<%=vo.getWriter() %>">
		<br><br>
		<!-- 내용 : <textarea name="content" value="<%=vo.getContent()%>"></textarea> 
			<textarea> 태그는 <input> 태그처럼 value 속성으로 값을 지정하지 않는다.
		-->
		내용 : <textarea name="content"><%=vo.getContent()%></textarea>
		<br><br>
		<input type="hidden" name="bidx" value="<%=vo.getBidx()%>">
		<br><br>
		<!-- button태그에 타입을 안 주면 기본 submit이다. -->
		<button>저장</button>
	</form>
</body>
</html>