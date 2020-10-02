<%@ page import="domain.Subject" 
    import="datasource.DBConnection"
    import="java.sql.*"
    import="java.util.*"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% //In case, if Instructor session is not set, redirect to Login page
if((request.getSession(false).getAttribute("userName")== null) )
{
	 response.sendRedirect("/login.jsp");
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
<title>DashBoard</title>
</head>
<body>
<div>Name: <%= request.getSession(false).getAttribute("userName")%></div>
<div>Type: <%= request.getSession(false).getAttribute("userType")%></div>
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
 			     String Type = (String)request.getAttribute("user_type");
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
	                 <%-- <a href="courses/<%=subject.getSubjectCode()%>/exams/<%= (String)request.getAttribute("user_type")%>">Check</a> --%>
	                 <%if (Type.equals("Admin")){ %>
	                  <a href="courses/<%=subject.getSubjectCode()%>/instructor">add Instructor</a>
	                  <a href="courses/<%=subject.getSubjectCode()%>/student">add Student</a>
	                 <%} else { %>
	                 <a href="courses/<%=subject.getSubjectCode()%>/exams">Check</a>
	                 <% } %>
	                 </td>
                </tr>
                
            <%
          		  } // for loop
        	%>
        </table>
    </div>
    
    <div style="text-align: mid">
<a href="<%=request.getContextPath()%>/logout">Logout</a></div>

     <hr class="rounded">
  <hr class="rounded">
   <%if (Type.equals("Admin")){ %>
    <div align="center">
    
    <form name="addSubject" action="addSubject" method="post">
         code : <input type = "text" name = "code">
         <br />
         name: <input type = "text" name = "name">
         <br />
         Instructor id:<input type = "text" name="id">
         <br />
         <input type = "submit" value = "Add New Subject" />
      </form>
    
    </div>
    <%} %>



</body>
</html>