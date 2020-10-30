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
		span1.setAttribute('id',num+'span1');
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
		select.setAttribute('id',num+'type');
		div.appendChild(select);
		div.appendChild(br);
		
		let span2 = document.createElement('span');
		span2.setAttribute('id',num+'span2');
		span2.innerHTML = 'Question Title'
		div.appendChild(span2);
		let input = document.createElement('input');
		input.setAttribute('id',num+'title');
		input.setAttribute('class','form-control');
		input.setAttribute('type','text');
		input.setAttribute('name',num+'title');
		input.setAttribute('palceholder','input question title')
		//input.setAttribute('value',code);
		div.appendChild(input);
		div.appendChild(br);
		
		let span3 = document.createElement('span');
		span3.setAttribute('id',num+'span3');
		span3.innerHTML = 'Question Content'
		div.appendChild(span3);
		let input1 = document.createElement('input');
		input1.setAttribute('class','form-control');
		input1.setAttribute('id',num+'content');
		input1.setAttribute('type','text');
		input1.setAttribute('name',num+'content');
		input1.setAttribute('palceholder','input question content')
		//input.setAttribute('value',code);
		div.appendChild(input1);
		
		let span4 = document.createElement('span');
		span4.setAttribute('id',num+'span4');
		span4.innerHTML = 'Question Answer'
		div.appendChild(span4);
		let input2 = document.createElement('input');
		input2.setAttribute('class','form-control');
		input2.setAttribute('type','text');
		input2.setAttribute('id',num+'answer');
		input2.setAttribute('name',num+'answer');
		input2.setAttribute('palceholder','input question answer')
		//input.setAttribute('value',code);
		div.appendChild(input2);
		div.appendChild(br);
		
		let span5 = document.createElement('span');
		span5.setAttribute('id',num+'span5');
		span5.innerHTML = 'Question Mark'
		div.appendChild(span5);
		let input3 = document.createElement('input');
		input3.setAttribute('class','form-control');
		input3.setAttribute('type','text');
		input3.setAttribute('name',num+'mark');
		input3.setAttribute('id',num+'mark');
		input3.setAttribute('palceholder','input question mark')
		//input.setAttribute('value',code);
		div.appendChild(input3);
		div.appendChild(br);
		
	    
		document.getElementById("num").value = num;
		
		num += 1;
		
	}
	
	function deleteInput() {
		num = num-1;
		var div = document.getElementById('div');
		var select = document.getElementById(num+'type');
		var span1 = document.getElementById(num+'span1');
		div.removeChild(select);
		div.removeChild(span1);
		var input = document.getElementById(num+'title');
		var span2 = document.getElementById(num+'span2');
		div.removeChild(input);
		div.removeChild(span2);
		var input1 = document.getElementById(num+'content');
		var span3 = document.getElementById(num+'span3');
		div.removeChild(input1);
		div.removeChild(span3);
		var input2 = document.getElementById(num+'answer');
		var span4 = document.getElementById(num+'span4');
		div.removeChild(input2);
		div.removeChild(span4);
		var input3 = document.getElementById(num+'mark');
		var span5 = document.getElementById(num+'span5');
		div.removeChild(input3);
		div.removeChild(span5);
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




 <div align="center">
 <form class="form-horizontal" name="AddQuestionController" action="addQuestion" method="post">
    <div id="div">
		<input type="button" name="add_question" value="Add a new question" onclick="addInput();">
		<input type="button" name="delete_question" value="delete one question" onclick="deleteInput();">
	</div>
	<input type="hidden" value="<%= (int)request.getAttribute("exam_id") %>"name="exam_id" /> 	
	<input type="hidden" name="num" id="num" />
	<button type="submit" class="btn btn-primary" >Submit</button>
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