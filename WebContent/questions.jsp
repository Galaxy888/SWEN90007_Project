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
<meta charset="ISO-8859-1">
<title>Question list</title>
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
</style>
 <script type="text/javascript">
 function Delete(id) {
 	 let input = document.getElementById(id+'flag');
     input.setAttribute('type','hidden');
     input.setAttribute('value',1);
 	 let input1 = document.getElementById(id+'title');
     input1.setAttribute('type','hidden');
	 let input2 = document.getElementById(id+'content');
     input2.setAttribute('type','hidden');
	 let input3 = document.getElementById(id+'answer');
     input3.setAttribute('type','hidden');
	 let input4 = document.getElementById(id+'mark');
     input4.setAttribute('type','hidden');
 }

	var num = 1;
	function addInput() {
		let br = document.createElement('br');
		let div = document.getElementById('div');
		div.appendChild(br);

		let span1 = document.createElement('span');
		span1.innerHTML = 'Question Type'
		div.appendChild(span1);
		let select = document.createElement('select');
		select.setAttribute('name',num+'type');
		let option1 = document.createElement('option');
		let option2 = document.createElement('option');
		option1.setAttribute('value','1');
		option1.appendChild(document.createTextNode("Short answer question")); 
		select.appendChild(option1);
		option2.setAttribute('value','2');
		option2.appendChild(document.createTextNode("Multiple-choice question")); 
		select.appendChild(option2);
		div.appendChild(select);
		div.appendChild(br);
		
		let span2 = document.createElement('span');
		span2.innerHTML = 'Question Title'
		div.appendChild(span2);
		let input = document.createElement('input');
		input.setAttribute('class','form-control');
		input.setAttribute('type','text');
		input.setAttribute('name',num+'title');
		input.setAttribute('palceholder','input question title')
		//input.setAttribute('value',code);
		div.appendChild(input);
		div.appendChild(br);
		
		let span3 = document.createElement('span');
		span3.innerHTML = 'Question Content'
		div.appendChild(span3);
		let input1 = document.createElement('input');
		input1.setAttribute('class','form-control');
		input1.setAttribute('type','text');
		input1.setAttribute('name',num+'content');
		input1.setAttribute('palceholder','input question content')
		//input.setAttribute('value',code);
		div.appendChild(input1);
		
		let span4 = document.createElement('span');
		span4.innerHTML = 'Question Answer'
		div.appendChild(span4);
		let input2 = document.createElement('input');
		input2.setAttribute('class','form-control');
		input2.setAttribute('type','text');
		input2.setAttribute('name',num+'answer');
		input2.setAttribute('palceholder','input question answer')
		//input.setAttribute('value',code);
		div.appendChild(input2);
		div.appendChild(br);
		
		let span5 = document.createElement('span');
		span5.innerHTML = 'Question Mark'
		div.appendChild(span5);
		let input3 = document.createElement('input');
		input3.setAttribute('class','form-control');
		input3.setAttribute('type','text');
		input3.setAttribute('name',num+'mark');
		input3.setAttribute('palceholder','input question mark')
		//input.setAttribute('value',code);
		div.appendChild(input3);
		div.appendChild(br);
		
	    
		document.getElementById("num").value = num;
		
		num += 1;
		
	}
    </script>
    
</head>
<body>


<a class="sel_btn" href="/dashboard">Dashboard</a> 
<div>
<h1 style="text-align:center"><%=request.getAttribute("subject_code") %> Exam</h1> 
</div>

<%-- <span style="color:red"><%=(request.getSession(false).getAttribute("errMessageQuestion") == null) ? "" : request.getSession(false).getAttribute("errMessageQuestion")%></span>
 <%
session.removeAttribute("errMessageQuestion");
%> --%>

<%
String strError = (String)request.getSession(false).getAttribute("errMessageQuestion");
if (strError!=null){
	out.println("<script type=\"text/javascript\">");  
	out.println("alert('"+strError+"');");
	out.println("</script>");
}      
%>
<%
session.removeAttribute("errMessageQuestion");
%>  

<form class="form-horizontal" name="AddQuestionController" action="addQuestion" method="post">
    <div id="div">
		<input type="button" name="add_question" value="Add a new question" onclick="addInput();">
	</div>
<input type="hidden" value="<%= (int)request.getAttribute("exam_id") %>"name="exam_id" /> 	
<input type="hidden" name="num" id="num" />
<button type="submit" class="btn btn-primary" >Submit</button>
</form>


 <div align="center">
  <form name="update" method=post action="updateQuestion">
        <table  style="width:70%">
            <tr>
				<th>#Question</th>
				<th>Question Type</th>
                <th>Question Title</th>
                <th>Question content</th>
                <th>Question mark</th>
                <th>Question Answer</th>
                <th>Operation</th>
                <th>Version</th>
            </tr>
           <tr>
          
 			<%
 			     List<Question> questions = new ArrayList<>(); 
 			     questions = (List<Question>)request.getAttribute("questions");//获取request中名称为student的值
           		 int i = 0;
 			     int flag = 0;
 			     for (Question question : questions) {
       		 %>
                    <tr>
					<td><%= i=i+1%></td>
					<td>
					<input id="<%=i%>flag" name="<%=i%>flag" type="hidden" value=<%=flag %> >
					<input id="<%=i%>id" name="<%=i%>id" type="hidden" value=<%=question.getId()%>>
					<input id="<%=i%>type" name="<%=i%>type" type="hidden" value=<%=question.getType()%>>
					<%= (question.getType()==1) ? "Short answer question": "Multiple-choice question" %>
					</td>
					<td><input id="<%=i%>title"  name="<%=i%>title" type="text" value=<%=question.getTitle()%>></td>
                    <td><input id="<%=i%>content" name="<%=i%>content" type="text" value=<%=question.getContent()%>></td>
                    <td><input id="<%=i%>mark" name="<%=i%>mark" type="text" value=<%=question.getMark()%>></td>
                    <td><input id="<%=i%>answer" name="<%=i%>answer" type="text" value=<%=question.getAnswer()%>></td>
                    <td><button type="button" class="sel_btn" data-toggle="modal" data-target="#updateModal" id="btn_update" onclick="Delete('<%=i%>')">Delete</button></td>
	                <td><input id="<%=i%>version" name="<%=i%>version" type="text"  readOnly="true" value=<%=question.getVersion()%>></td> 
	                </tr>
	       <%
          		  } // for loop
        	%>
        	    
        	    <tr>
        	    <td><input type="hidden" value="<%=i %>" name="num" /></td>
                <td><input type="hidden" value="<%= (int)request.getAttribute("exam_id") %>" name="exam_id" /></td>
                <td><button type="submit" class="btn btn-primary">Submit</button></td>
                </tr>  
        </table>
         </form>
         
    </div>
              
                                  
<!--     <div align="center">
   <td> 
	                 <form name="delete" method=post action="deleteQuestion">
	                 <input name="id" type="hidden" value=>
	                 <input type = "submit" value = "Delete" />
	                 </form>
	                 </td>
	 -->

<!-- 	</div> -->
</body>
</html>