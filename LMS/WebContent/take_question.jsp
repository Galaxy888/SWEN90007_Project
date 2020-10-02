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
                <th>Question id</th>
                <th>Question Title</th>
                <th>Question content</th>
                <th>Answer</th>
            </tr>
            <form name="Answer" method="post" action="TakeQuestion">
                <input name="exam_id" type="hidden" value=<%=(int)request.getAttribute("exam_id") %>>
                
 			<%
 			     List<Question> questions = new ArrayList<>(); 
 			     questions = (List<Question>)request.getAttribute("questions");//获取request中名称为student的值 
           		 for (Question question : questions) {
       		 %> 
       		        <tr>
       		        <td><%= question.getId() %></td>
                    <td><%= question.getTitle() %></td>
                     
                   <%if(question.getType()==1){ %>
                     
                    <td><%= question.getContent() %></td>
                    <td>
                     <input name="id<%=question.getId() %>" type="hidden" value=<%=question.getId()%>>
	                 <input type="text" name="answer<%=question.getId() %>">
	                </td>
                   
                   <%}else{ %>
                     <td>
                 
                    <%
                    String [] arr = question.getContent().split("#");
                    for(String ss : arr){
                     %>
                    <input type="radio" name="answer<%=question.getId() %>" value=<%=ss %>><%=ss %>
                    <%
                    }
                    %>
                    <input name="id<%=question.getId() %>" type="hidden" value=<%=question.getId()%>>
                 
                    </td>
                    <%} %>
                    
                   </tr>
            <%
          		  } // for loop
        	%>
        	
        	     <tr>
        	     <input type = "submit" value = "Answer" />
        	   </form>
        	    </tr>
        </table>
         
    </div>
</body>
</html>