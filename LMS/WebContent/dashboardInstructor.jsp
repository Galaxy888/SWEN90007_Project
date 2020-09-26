<%@ page import="domain.Subject" 
    import="datasource.DBConnection"
    import="java.sql.*"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% //In case, if Instructor session is not set, redirect to Login page
if((request.getSession(false).getAttribute("userName")== null) )
{
	 response.sendRedirect("/LMS/login.jsp");
}
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
<title>Dashboard Instructor</title>
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
           		 for (Subject subject : Subject.getAllSubjects()) {
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
	                 <a href="courses/<%=subject.getSubjectCode()%>/exams">Check</a>
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