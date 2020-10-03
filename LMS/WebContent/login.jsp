<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Login Page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
</head>
<%
	//In case, if User session is not set, redirect to Login page
if ((request.getSession(false).getAttribute("userName") != null)) {
	response.sendRedirect("./dashboard");
}
%>
<body>
	<form class="border border-light p-5 col-md-4 offset-md-4"
		action="<%=request.getContextPath()%>/login" method="post">

		<div class="text-center">
			<p class="text-center h1 mb-4">Log in</p>
			<span class="center-block" style="color: red"><%=(request.getSession(false).getAttribute("errMessage") == null) ? ""
		: request.getSession(false).getAttribute("errMessage")%></span>
			<%
session.removeAttribute("errMessage");

%>
		</div>

		<div class="form-group">
			<label>User name:</label> <input type="text" name="userName"
				placeholder="User name" class="form-control">
		</div>

		<div class="form-group ">
			<label>Password: </label> <input type="password" name="passWord"
				placeholder="Password" class="form-control">

		</div>

		<input type="submit" value="Login"
			class="btn btn-primary col-md-4 offset-md-4">
	</form>

</body>
</html>
