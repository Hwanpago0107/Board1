<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<!-- user패키지의 UserDAO 클래스 임포트 -->
<%@ page import="java.io.PrintWriter"%>
<!-- 자바패키지 printwriter 임포트 -->
<%
	request.setCharacterEncoding("UTF-8");
%>

<!-- 한명의 회원정보를 담는 User클래스를 Beans로 사용 , scope: 현재의 페이지에서만 사용하도록 범위 설정-->
<jsp:useBean id="user" class="user.User" scope="page" />
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />
<jsp:setProperty name="user" property="userName" />
<jsp:setProperty name="user" property="userGender" />
<jsp:setProperty name="user" property="userEmail" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="text/html; charset=UTF-8">
<title>게시판 웹사이트</title>
</head>
<body>
	<%
		session.invalidate();
	%>
	<script>
	location.href = 'main.jsp'
	</script>
</body>
</html>