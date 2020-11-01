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
<div align="center">
        <table class="table table-bordered" style="width:70%">
            <tr>
                <th>#Question</th>
                <th>Question Title</th>
                <th>Question Content</th>
                <th>Answer</th>
            </tr>
            <form name="Answer" method="post" action="TakeQuestion">
                <input name="exam_id" type="hidden" value=<%=(int)request.getAttribute("exam_id") %>>
                
 			<%
 			     List<Question> questions = new ArrayList<>(); 
 			     questions = (List<Question>)request.getAttribute("questions");//获取request中名称为student的值 
           		 int i=0;
 			     for (Question question : questions) {
       		 %> 
       		        <tr>
       		        <%-- <td><%= question.getId() %></td> --%>
       		         <td><%= i=i+1 %></td>
                    <td><%= question.getTitle() %></td>
                     
                   <%if(question.getType()==1){ %>
                     
                    <td><%= question.getContent() %></td>
                    <td>
                     <input name="id<%=question.getId() %>" type="hidden" value=<%=question.getId()%>>
	                 <textarea type="text" name="answer<%=question.getId() %>"></textarea>
	                </td>
                   
                   <%}else{ %>
                     <td>
                 
                    <%
                    String [] arr = question.getContent().split("#");
                    for(String ss : arr){
                    	String s =ss;
                    	s.replaceAll(" ", "");
                     %>
                    <input maxlength=-1 type="radio" name="answer<%=question.getId() %>" value=<%=s %>><%=ss %>
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
        	     <% int flag = (int)request.getAttribute("flag");
        	     if(flag==1){ %>
        	    <td> <input class="sel_btn" type = "submit" value = "Submit" /> </td>
        	     <%} else{
        	    	 response.sendRedirect("./exams/done");
        	     }%>
        	   </form>
        	    </tr>
        </table>
         
    </div>
</body>
</html>