<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%
	String date = (String)request.getAttribute("serverTime");
%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<!-- ${serverTime} -> el tag -->
<!--  <P>  The time on the server is ${serverTime}. </P>-->
<P>  The time on the server is <%=date %> </P>
<a href="/Board/list.do">Board/list.do</a>
<a href="/Free/list.do">Free/list.do</a>
<a href="/Member/list.do">Member/list.do</a>
<a href="/Mybatis/board/list.do">Mybatis/board/list.do</a>
<a href="/Sample/list.do">Sample/list.do</a>
<a href="/ajax/list.do">ajax/list.do</a>
</body>
</html>
