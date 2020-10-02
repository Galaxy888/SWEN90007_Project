<%@ page import="domain.Answer" 
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
                <th>user Id</th>
                <th>question id</th>
                <th>question answer</th>
                <th>answer mark</th>
            </tr>
            <form name="mark" method="post" action="markAnswer">
            <input name="exam_id" type="hidden" value=<%=(int)request.getAttribute("exam_id") %>>
                <tr>
 			<%
 			     List<Answer> answers = new ArrayList<>(); 
 			     answers = (List<Answer>)request.getAttribute("answers");//获取request中名称为student的值
           		 for (Answer answer : answers) {
       		 %>
       		        <td><%= answer.getId() %></td>
                    <td><%= answer.getQuestion_id() %></td>
                    <td><%= answer.getAnswer() %>
                    <td>
	                 <input name="id<%=answer.getId() %>" type="hidden" value=<%=answer.getId()%>>
	                 <input name="qid<%=answer.getQuestion_id() %>" type="hidden" value=<%=answer.getQuestion_id()%>>
	                 <input type="text" name="mark<%=answer.getId() %><%=answer.getQuestion_id() %>">
	                 </td>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
             <input type = "submit" value = "Mark" />
         </form>
    </div>
</body>
</html>