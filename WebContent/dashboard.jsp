<<<<<<< HEAD
<%@ page import="domain.Subject" 
    import="datasource.DBConnection"
    import="java.sql.*"
    import="java.util.*"
%>
=======
>>>>>>> master
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<<<<<<< HEAD
<% //In case, if Instructor session is not set, redirect to Login page
=======
<meta charset="UTF-8">
<title>Dashboard Page</title>
<% //In case, if User session is not set, redirect to Login page
>>>>>>> master
if((request.getSession(false).getAttribute("userName")== null) )
{
	 response.sendRedirect("/LMS/login.jsp");
}
<<<<<<< HEAD

%>
</head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
  
<meta charset="UTF-8">
<title>DashBoard</title>
</head>
<body>
    <div align="center">
        <table  style="width:70%">
            <tr>
                <th>Subject Code</th>
                <th>Subject Name</th>
                <th>Coordinator</th>
                <th>Exam List</th>
            </tr>
            
                <tr>
 			<%
 		         List<Subject> subjects = new ArrayList<>(); 
		         subjects = (List<Subject>)request.getAttribute("subjects");//获取request中名称为student的值
           		 for (Subject subject : subjects) {
       		 %>
       		        <td><%= subject.getSubjectCode() %></td>
                    <td><%= subject.getName() %></td>
                    <%  
                    int user_id =subject.getCoordinator();
                    String stm = "select * from users where id='"+user_id+"' limit 1"; 
                    PreparedStatement stmt = DBConnection.prepare(stm);
                	ResultSet rs = stmt.executeQuery();
                	if(rs.next()){
                		out.print("<td>"+ rs.getString(2)+"</td>");
                		}
                     %>
	                 <td>
	                 	<%-- <a href="./exams?subject_code=<%=subject.getSubjectCode()%>">Check</a> --%>
	                 <%-- <a href="<%=request.getContextPath()%>/exams">Check</a> --%>
	                 <a href="courses/<%=subject.getSubjectCode()%>/exams/<%= (String)request.getAttribute("user_type")%>">Check</a>
	                 </td>
                </tr>
                
            <%
          		  } // for loop
        	%>
        </table>
    </div>
    
    <div style="text-align: mid">
<a href="<%=request.getContextPath()%>/logout">Logout</a></div>
</body>
</html>
=======
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
>>>>>>> master
