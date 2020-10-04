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
<title>Answer</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>	
<style>
 .sel_btn{
            height: 21px;
            line-height: 21px;
            padding: 0 11px;
            background: #02bafa;
            border: 1px #26bbdb solid;
            border-radius: 3px;
            color: #fff;
            display: inline-block;
            text-decoration: none;
            font-size: 12px;
            outline: none;
        }
.form-popup {
  display: none;
 /*  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1; */
  z-index: 9;
}
</style>
</head>
<body>
<a class="sel_btn" href="/login.jsp">DashBoard</a> 
 <div align="center">
        <table  style="width:70%">
            <tr>
                <th>User Id</th>
                <th>Question Id</th>
                <th>Question Answer</th>
                <th>Answer Mark</th>
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
	                 <input type="number" name="mark<%=answer.getId() %><%=answer.getQuestion_id() %>" required>
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