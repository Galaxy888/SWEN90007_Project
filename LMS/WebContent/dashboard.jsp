<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard Page</title>
<% //In case, if User session is not set, redirect to Login page
if((request.getSession(false).getAttribute("userName")== null) )
{
	 response.sendRedirect("/LMS/login.jsp");
}
%>
</head>
<body>
<!-- <h3>Hi Admin, Logging in was successful.</h3>  -->
<%-- 
The time is now <%= new java.util.Date()%>
<a href="logInForm.html">Login Page</a> --%>
<div>Welcome: <%=request.getAttribute("userName") %></div>
<div>User Type: <%=request.getAttribute("userType") %></div>
<div>Seesion username: <%=request.getSession(false).getAttribute("userName") %></div>
<div>Seesion user type: <%=request.getSession(false).getAttribute("userType") %></div>
<div style="text-align: mid">
<a href="<%=request.getContextPath()%>/logout">Logout</a></div>
</body>
</html>
