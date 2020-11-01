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
        
.no-outline{
  outline: none;
  border-top-style: hidden;
  border-right-style: hidden;
  border-left-style: hidden;
  border-bottom-style: hidden;
  
/*   background-color: #eee; */
}

input[type=number]:disabled {
   color: #444;
   background:#cccccc;
}
input[type=text]:disabled {
   color: #444;
   background:#cccccc;
}
textarea:disabled {
   color: #444;
   background:#cccccc;
}
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script type="text/javascript">
 function Delete(id) {
 	 let input = document.getElementById(id+'flag');
     input.setAttribute('type','hidden');
     input.setAttribute('value',1);
 	 let input1 = document.getElementById(id+'title');
     input1.setAttribute('type','hidden');
	 let input2 = document.getElementById(id+'content');
     input2.setAttribute('type','hidden');
     input2.setAttribute('hidden','hidden');

	 let input3 = document.getElementById(id+'answer');
     input3.setAttribute('type','hidden');
     input3.setAttribute('hidden','hidden');
	 let input4 = document.getElementById(id+'mark');
     input4.setAttribute('type','hidden');
     let input5 = document.getElementById(id+'index');
     input5.setAttribute('type','hidden');
     let input6 = document.getElementById(id+'type2');
     input6.setAttribute('type','hidden');
	 let input7 = document.getElementById(id+'btn_update');
     input7.setAttribute('hidden','hidden');
     
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
	
	
	
	
	
	
	
	
	function editInput(eid) {
		var input = document.getElementsByClassName('editable');
		
	
	   $.ajax({
	       url: 'editQuestion',
	       type: 'post',
	       data:  'id='+eid+'&option='+"edit",
	       success: function(conn, response, options){
	          /*  alert(response); */
	/*       	if(response.status==422){
	               window.location.reload();
	       	} */

			for (i = 0; i < input.length; i++) {
				input[i].readOnly=false;
				input[i].disabled=false;
			}
	       },
	   error:function(response){
	   /* 	window.location.reload(); */
	   alert("Someone is updating Exam questions. Please try again later")
	   }
	       
	   }); // end ajax call
	}
	
	
	function btn_cancel(eid){
		window.location.reload();
		 var input = document.getElementsByClassName('editable');
			for (i = 0; i < input.length; i++) {
				input[i].readOnly=true
				input[i].disabled=true
			}
		
		  $.ajax({
	          url: 'editQuestion',
	          type: 'post',
	          data:  'id='+eid+'&option='+"cancel",
	          /* data: { username: "username", password: "password" } */
	          success: function(conn, response, options){
	        	  window.location.reload();
	              /* alert(response); */
	/*       	if(response.status==422){
	                  window.location.reload();
	          	} */
	          },
	/*         error:function(response){
	      	window.location.reload();
	      } */
	          
	      }); // end ajax call
	}
	
	$(window).bind('unload', function(){
		
	/* 	 var input = document.getElementsByClassName('editable');
			for (i = 0; i < input.length; i++) {
				input[i].readOnly=true
				input[i].disabled=true
			} */
	    $.ajax({
	    	url: 'editQuestion',
	        async: false,
	        type: 'post',
          data:  'id='+"00000"+'&option='+"cancel",
	    });
	  
	}); 
	
	
	 function changeFunc($i) {
		 var input = document.getElementsByClassName('note');
		 if($i==1){
			 
			 input[0].type="hidden"
			 input[1].type="hidden"
			 
		 }else if($i==2){
			 input[0].type="text"
			 input[1].type="text"
			 
		 }
		    
	 }
	
	
	
	
	
    </script>
    
</head>
<body>


<a class="sel_btn" href="/dashboard">Dashboard</a>
<a class="sel_btn" href="/courses/<%=request.getAttribute("subject_code")%>/exams">Previous page</a>
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
<button type="button" class="btn btn-primary" id="btn_edit" onclick="editInput(<%=request.getAttribute("exam_id") %>)">Edit Exam</button>
<button type="button" class="btn btn-primary editable" disabled id="btn_cancel" onclick="btn_cancel(<%=request.getAttribute("exam_id") %>)">Cancel Edit</button>  
<br>
<br>
<!--  <button type="button" class="btn btn-primary col-md-4 offset-md-4" data-toggle="modal" data-target="#addQuestionModal">
 Add new question
</button> -->



<%-- <form class="form-horizontal" name="AddQuestionController" action="addQuestion" method="post">
    <div id="div">
		<input type="button" name="add_question" value="Add a new question" onclick="addInput();">
		<input type="button" name="delete_question" value="delete one question" onclick="deleteInput();">
	</div>
<input type="hidden" value="<%= (int)request.getAttribute("exam_id") %>"name="exam_id" /> 	
<input type="hidden" name="num" id="num" />
<button type="submit" class="btn btn-primary" >Submit</button>
</form> --%>


 <div align="center">
  <form name="update" method=post action="updateQuestion">
        <table  class="table table-bordered" style="width:70%">
            <tr>
				<th>#Question</th>
				<th>Question Type</th>
                <th>Question Title</th>
                <th>Question content</th>
                <th>Question Answer</th>
                <th>Question mark</th>
                
                <th>Operation</th>
                <!-- <th>Version</th> -->
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
					<td>
					<input id="<%=i+1%>index" name="<%=i%>type" readOnly="true" class="no-outline" style="width:65px" value=<%= i=i+1%>>
					</td>
					<td>
					<input id="<%=i%>flag" name="<%=i%>flag" type="hidden" value=<%=flag %> >
					<input id="<%=i%>id" name="<%=i%>id" type="hidden" value=<%=question.getId()%>>
					 <input id="<%=i%>type" name="<%=i%>type" type="hidden" value=<%=question.getType()%>>
					<input id="<%=i%>type2" name="<%=i%>type" readOnly="true" class="no-outline" style="width:130px" value=<%= (question.getType()==1) ? "Short-answer question": "Multiple-choice question" %>>
					
					</td>
					<td><input id="<%=i%>title"  name="<%=i%>title" type="text" class="editable" disabled="disabled"readonly=true  value=<%=question.getTitle()%>></td>
                    <td><textarea id="<%=i%>content" name="<%=i%>content" type="text" class="editable" disabled="disabled"readonly=true value=<%=question.getContent()%>><%=question.getContent()%></textarea></td>
                    
                    <td><textarea id="<%=i%>answer" name="<%=i%>answer" type="text" class="editable" disabled="disabled"readonly=true  value=<%=question.getAnswer()%>><%=question.getAnswer()%></textarea></td>
                    <td><input id="<%=i%>mark" name="<%=i%>mark" type="number"  style="width:65px" class="editable" disabled="disabled"readonly=true  required value=<%=question.getMark()%>></td>
                    <td><button type="button" class="btn btn-danger editable" data-toggle="modal" data-target="#updateModal" id="<%=i%>btn_update" disabled onclick="Delete('<%=i%>')">Delete</button></td>
<%-- 	                <td><input id="<%=i%>version" name="<%=i%>version" type="text" style="width:65px" readOnly="true" value=<%=question.getVersion()%>></td>  --%>
	                </tr>
	       <%
          		  } // for loop
        	%>
        	    
        	    <tr>
        	    <td><input type="hidden" value="<%=i %>" name="num" /></td>
                <td><input type="hidden" value="<%= (int)request.getAttribute("exam_id") %>" name="exam_id" /></td>
               
                </tr>  
        </table>
         <button type="button" class="btn btn-primary editable" disabled data-toggle="modal" data-target="#addQuestionModal">
         Add new question
         </button>
        <button type="submit" class="btn btn-primary editable" disabled>Submit</button>

         </form>
         
    </div>
    
    
    
    
    
    
    
    
    
    
    
    
    <form  id="contactForm1" class="form-horizontal" name="AddQuestionController" action="addQuestion" method="post">
		
			<div class="modal fade" id="addQuestionModal" tabindex="-1" role="dialog"
				aria-labelledby="addQuestionModalLabel" aria-hidden="true">
				
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="addQuestionModal">Add new
								question</h4>
						</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="firstname" class="col-sm-6 control-label">Question Type:</label>
							<div class="col-sm-7">
							<div >
								<select onchange="changeFunc(value);" name="type" id="type">
									<option value="1">Short answer question</option>
									<option value="2">Multiple-choice question</option>
								</select>
							<label class="control-label" for="type" style="display: none;"></label>
							</div>
							</div>
										 <div class="form-group">
											<label for="firstname" class="col-sm-6 control-label">Question Title:</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="title" name="title"  placeholder="input new title">
												<label class="control-label" for="updateTitle" style="display: none;"></label>
												</div>
										</div>
										
										<div>
										<input  readOnly="true" class="note no-outline col-sm-10" type="hidden" style="color:red" value="Note: Please use # to separate each option.">
										<input  readOnly="true" class="note no-outline col-sm-10" type="hidden" style="color:red" value="For example: Choice A # Choice B # Choice C"/>
										</div>
										<div class="form-group">
										
											<label for="firstname" class="col-sm-6 control-label">Question Content:</label>
												<div class="col-sm-7">
													<textarea type="text" class="form-control" id="content" name="content"  placeholder="input new content"></textarea>
												<label class="control-label" for="content" style="display: none;"></label>
												</div>
										</div>
											
										<div class="form-group">
											<label for="firstname" class="col-sm-8 control-label">Question Answer:</label>
												<div class="col-sm-7">
													<textarea type="text" class="form-control" id="answer" name="answer"  placeholder="input new answer"></textarea>
												<label class="control-label" for="answer" style="display: none;"></label>
												</div>
										</div>
										<div class="form-group">
											<label for="firstname" class="col-sm-6 control-label">Question Mark:</label>
												<div class="col-sm-7">
													<input type="number" class="form-control" id="mark" name="mark"  placeholder="input new mark" required>
												<label class="control-label" for="mark" style="display: none;"></label>
												</div>
										</div>
																		<input type="hidden"
									value="<%= (int)request.getAttribute("exam_id") %>"
									name="exam_id" /> 	
									
								<button type="submit" class="btn btn-primary" >
														Add New Question
								</button>
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>			
				</div>
				</div>
				</div>
				</div>
				</div>
		</form>
              
                                  
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