<%@ page import="domain.Exam" 
    import="datasource.DBConnection"
    import="java.sql.*"
    import="java.util.*"
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a class="sel_btn" href="/LMS/login.jsp">DashBoard</a> 
 <div align="center">
        <table  style="width:70%">
            <tr>
                <th>exam Id</th>
                <th>exam Title</th>
                <th>exam Status</th>
                <th>Subject</th>
                <th>Operation</th>
            </tr>
            
                <tr>
 			<%
 			     List<Exam> exams = new ArrayList<>(); 
 			     exams = (List<Exam>)request.getAttribute("exams");//获取request中名称为student的值
           		 for (Exam exam : exams) {
       		 %>
       		        <td><%= exam.getId() %></td>
                    <td><%= exam.getTitle() %></td>
                    <td><%= exam.getStatus() %>
                    <td><%= exam.getSubject()  %></td>
                    <td>
	                 <%-- <a class="sel_btn" href="./updateExam?id=<%=exam.getId()%>&title=<%=exam.getTitle()%>&status=<%=exam.getStatus()%>&subject_code=<%=exam.getSubject()%>">Edit</a> --%>
	                 <%-- <a class="sel_btn" href="./questions?exam_id=<%=exam.getId()%>">Edit Questions</a> --%>
	               <a class="sel_btn" href="exams/<%=exam.getId()%>/questions">Take Exam</a>
	                 <td><%=request.getAttribute("mark"+exam.getId()) %></td>
	                 <%-- <a class="sel_btn" href="./deleteExam?subject_code=<%=exam.getSubject() %>&id=<%=exam.getId()%>">Delete</a> --%>
	                 <%-- <a class="sel_btn" href="./deleteExam/<%=exam.getId()%>/<%= exam.getStatus() %>">Delete</a> --%>
	                <%--  <button type="button" onclick="deleteExam('<%=exam.getId()%>')">Delete</button> --%> 
	                 </td>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
         
    </div>
</body>
</html>