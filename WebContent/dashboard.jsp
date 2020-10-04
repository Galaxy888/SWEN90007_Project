<%@ page import="domain.Subject" 
    import="domain.User"
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
	 response.sendRedirect("./login.jsp");
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
    <div align="center">
     <hr class="rounded">
  <hr class="rounded">
    <form name="addUser" action="addUser" method="post">
         name: <input type = "text" name = "name">
         <br />
         email:<input type = "text" name="email">
         <br />
         password:<input type = "text" name="password">
         <br />
         type:<input type = "text" name="type">
         <br />
         <input type = "submit" value = "Add New User" />
      </form>
     <hr class="rounded">
  <hr class="rounded">
    </div>
      <table  style="width:70%">
            <tr>
                <th>User Id</th>
                <th>User Name</th>
                <th>User Password</th>
                <th>User Email</th>
                <th>User Type</th>
                <th>Operation</th>
            </tr>
            
                <tr>
 			<%
 			     List<User> users = new ArrayList<>(); 
 			     users = (List<User>)request.getAttribute("users");//获取request中名称为student的值
           		 for (User user: users) {
       		 %>
       		        <td><%= user.getId() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %>
                    <td><%= user.getPassword()  %></td>
                    <% if(user.getType()==2){ %>
                    <td> Instructor</td>
                    <% } else {%>
                    <td> Student</td>
                    <%} %>
                    <td>
                    <button type="button" class="sel_btn" data-toggle="modal" data-target="#updateModal" id="btn_update" 
                    onclick="showInfo2('<%= user.getId() %>','<%= user.getName() %>','<%= user.getEmail() %>','<%= user.getPassword() %>','<%= user.getType() %>')">
                    Update</button>
	                 <form class = "sel_btn" name="delete" method="post" action="deleteExam">
	                 <input class = "sel_btn"  name="id" type="hidden" value=<%=user.getId()%>>
	                 <input class = "sel_btn"  type = "submit" value = "Delete" />
	                 </form>

	                 
	                 </td>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
    <%} %>



</body>
</html>