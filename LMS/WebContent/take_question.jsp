<%@ page import="domain.Question" 
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
<title>Take exam</title>
</head>
<body>
<div align="center">
        <table  style="width:70%">
            <tr>
                <th>Question Id</th>
                <th>Question Title</th>
                <th>Question content</th>
                <th>Question mark</th>
                <th>Operation</th>
            </tr>
            
                <tr>
 			<%
 			     List<Question> questions = new ArrayList<>(); 
 			     questions = (List<Question>)request.getAttribute("questions");//获取request中名称为student的值
           		 for (Question question : questions) {
       		 %>
       		        <td><%= question.getId() %></td>
                    <td><%= question.getTitle() %></td>
                    <td><%= question.getContent() %>
                    <td><%= question.getMark() %></td>
                    <td>
                    <button type="button" class="sel_btn" data-toggle="modal" data-target="#updateModal" id="btn_update" 
                    onclick="showInfo2('<%= question.getId() %>','<%= question.getType() %>','<%= question.getTitle() %>',
                    '<%= question.getContent() %>','<%= question.getMark()%>','<%= question.getAnswer()%>','<%= question.getExam() %>')">
                    Update</button>
	                 <%-- <a class="sel_btn" href="./deleteQuestion?exam_id=<%=question.getExam() %>&id=<%=question.getId()%>">Delete</a> --%>
	                 <form name="answer" method=post action="TakeQuestionController">
	                 <input name="id" type="hidden" value=<%=question.getId()%>>
	                 <input type = "submit" value = "Delete" />
	                 </form>
	                 </td>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
         
    </div>
</body>
</html>