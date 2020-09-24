<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title> Login Page</title>
</head>
<% //In case, if User session is not set, redirect to Login page
if((request.getSession(false).getAttribute("userName")!= null) )
{
	 response.sendRedirect("/LMS/dashboard");
}
%>
<body>
<span style="color:red"><%=(request.getSession(false).getAttribute("errMessage") == null) ? "" : request.getSession(false).getAttribute("errMessage")%></span></td>
<form action="<%=request.getContextPath()%>/login" method="post">
User name: <input type="text" name="userName"><br>
Password: <input type="password" name="passWord"><br>
<input type="submit" value="Login">
</form>
</body>
</html>
