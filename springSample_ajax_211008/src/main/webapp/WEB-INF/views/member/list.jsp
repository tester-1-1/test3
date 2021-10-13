<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!-- 클래스 타입 import하기 -> page import="패키지이름.클래스이름" -->
<%@ page import="edu.study.vo.MemberVO" %>
<%
	ArrayList<MemberVO> alist = (ArrayList<MemberVO>)request.getAttribute("alist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		for(MemberVO mv : alist){
			
	%>
		-------------------<br>
		이름 : <%=mv.getName() %> <br>
		나이 : <%=mv.getAge() %> <br>
		주소 : <%=mv.getAddr() %> <br>
		-------------------<br>
	<%
		}
	%>
	<br>
	<a href="view.do">view.do로 이동</a>
</body>
</html>