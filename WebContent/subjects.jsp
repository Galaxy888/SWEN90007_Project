<%@ page import="domain.Subject" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
<title>LMS System</title>
</head>
<body>
    <div align="center">
        <table  style="width:70%">
            <tr>
                <th>Subject Code</th>
                <th>Subject Name</th>
                <th>Coordinator</th>
            </tr>
            
                <tr>
 			<%
           		 for (Subject subject : Subject.getAllSubjects()) {
       		 %>
       		        <td><%= subject.getSubjectCode() %></td>
                    <td><%= subject.getName() %></td>
                    <td><%= subject.getCoordinator() %></td>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
    </div>
    
  <hr class="rounded">
  <hr class="rounded">
    
    <div align="center">
    
    <form name="AddSubjectForm" action="/" method="post">
         Subject Code: <input type = "text" name = "code">
         <br />
         Subject Name: <input type = "text" name = "name">
         <br />
         Coordinator: <input type = "text" name = "corrdinator_name" />
         <br />
         <input type = "submit" value = "Add New Subject" />
      </form>
    
    </div>
</body>
</html>