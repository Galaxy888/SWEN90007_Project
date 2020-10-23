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
    function showInfo2(id,type,title,content,mark,answer,exam,version) {
    	document.getElementById("updateId").value = id;
        document.getElementById("updateType").value = type;
        document.getElementById("updateTitle").value = title;
        document.getElementById("updateContent").value = content;
        document.getElementById("updateMark").value = mark;
        document.getElementById("updateAnswer").value = answer;
        document.getElementById("updateExam").value = exam;
        document.getElementById("updateVersion").value = version;
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
 <button type="button" class="btn btn-primary col-md-4 offset-md-4" data-toggle="modal" data-target="#addQuestionModal">
 Add new question
</button>
 <div align="center">
        <table  style="width:70%">
            <tr>
<!--                 <th>Question Id</th> -->
				<th>#Question</th>
				<th>Question Type</th>
                <th>Question Title</th>
                <th>Question content</th>
                <th>Question mark</th>
                <th>Operation</th>
                <th>Version</th>
            </tr>
            
                <tr>
 			<%
 			     List<Question> questions = new ArrayList<>(); 
 			     questions = (List<Question>)request.getAttribute("questions");//获取request中名称为student的值
           		 int i = 0;
 			     for (Question question : questions) {
       		 %>
<%--        		        <td><%= question.getId() %></td> --%>
					<td><%= i=i+1 %>
					<td><%= (question.getType()==1) ? "Short answer question": "Multiple-choice question" %></td>
                    <td><%= question.getTitle() %></td>
                    <td><%= question.getContent() %></td>
                    <td><%= question.getMark() %></td>
                    
                    <td>
                    <button type="button" class="sel_btn" data-toggle="modal" data-target="#updateModal" id="btn_update" 
                    onclick="showInfo2('<%= question.getId() %>','<%= question.getType() %>','<%= question.getTitle() %>',
                    '<%= question.getContent() %>','<%= question.getMark()%>','<%= question.getAnswer()%>','<%= question.getExam() %>','<%= question.getVersion() %>')">
                    Update</button>
	                 <%-- <a class="sel_btn" href="./deleteQuestion?exam_id=<%=question.getExam() %>&id=<%=question.getId()%>">Delete</a> --%>
	                 <form name="delete" method=post action="deleteQuestion">
	                 <input name="id" type="hidden" value=<%=question.getId()%>>
	                 <input type = "submit" value = "Delete" />
	                 </form>
	                 </td>
	                 <td><%= question.getVersion() %></td>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
         
    </div>
                 <form class="form-horizontal" method="post" action="updateQuestion">     
									<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="updateModalLabel">
														Update question info
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------form-------------------->
<!-- 									<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">id</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateId" name="id"  placeholder="question id">
												<label class="control-label" for="updateId" style="display: none;"></label>
												</div>
										</div> -->
										
										<input type = "hidden" id="updateId" name="id" />
										
									<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Question Type:</label>
												<div class="col-sm-7">
													<!-- <input type="text" class="form-control" id="updateType" name="type"  placeholder="question type"> -->
																<div >
																<select name="type" id="updateType">
																<option value="1">Short answer question</option>
																<option value="2">Multiple-choice question</option>
																</select> 
												<label class="control-label" for="updateType" style="display: none;"></label>
												</div>
										</div>
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Question Title:</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateTitle" name="title"  placeholder="input new title">
												<label class="control-label" for="updateTitle" style="display: none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Question Content:</label>
												<div class="col-sm-7">
													<textarea type="text" class="form-control" id="updateContent" name="content"  placeholder="input new content"></textarea>
												<label class="control-label" for="updateContent" style="display: none;"></label>
												</div>
										</div>
											
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Question Answer:</label>
												<div class="col-sm-7">
													<textarea type="text" class="form-control" id="updateAnswer" name="answer"  placeholder="input new answer"></textarea>
												<label class="control-label" for="updateAnswer" style="display: none;"></label>
												</div>
										</div>
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Question Mark:</label>
												<div class="col-sm-7">
													<input type="number" class="form-control" id="updateMark" name="mark"  placeholder="input new mark" required>
												<label class="control-label" for="updateMark" style="display: none;"></label>
												</div>
										</div>
<!-- 										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">exam_id</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateExam" name="exam_id"  placeholder="exam_id">
												<label class="control-label" for="updateExam" style="display: none;"></label>
												</div>
										</div> -->
										
										<input type = "hidden" id="updateExam" name="exam_id" />
										<input type = "hidden" id="updateVersion" name="version" />
											
										</div>
												<div class="modal-footer">
				
													<button type="submit" class="btn btn-primary" >
														submit
													</button>
													<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
									</div>
	
                                 </form>
                                 
                                 
                                 
      <hr class="rounded">
  <hr class="rounded">
    

    
<!--     <div align="center"> -->

		<form class="form-horizontal" name="AddQuestionController" action="addQuestion" method="post">
		
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
							<label for="firstname" class="col-sm-3 control-label">Question Type:</label>
							<div class="col-sm-7">
							<div >
								<select name="type" id="type">
									<option value="1">Short answer question</option>
									<option value="2">Multiple-choice question</option>
								</select>
							<label class="control-label" for="type" style="display: none;"></label>
							</div>
							</div>
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Question Title:</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="title" name="title"  placeholder="input new title">
												<label class="control-label" for="updateTitle" style="display: none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Question Content:</label>
												<div class="col-sm-7">
													<textarea type="text" class="form-control" id="content" name="content"  placeholder="input new content"></textarea>
												<label class="control-label" for="content" style="display: none;"></label>
												</div>
										</div>
											
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Question Answer:</label>
												<div class="col-sm-7">
													<textarea type="text" class="form-control" id="answer" name="answer"  placeholder="input new answer"></textarea>
												<label class="control-label" for="answer" style="display: none;"></label>
												</div>
										</div>
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Question Mark:</label>
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






<%-- 							<div class="form-group row">
								<label class="col-md-4">Question Type:</label>
								<div>
									<select name="type" id="type">
										<option value="1">Short answer question</option>
										<option value="2">Multiple-choice question</option>
									</select>
								</div>

								<!--          Question Type:<input type = "text" name = "type"> -->
								<br /> Question Title: <input type="text" name="title" required>
								<br /> Question Content:<input type="text" name="content">
								<br /> Question Answer:<input type="text" name="answer">
								<br /> Question Mark:<input type="number" name="mark" required>
								<br />
								<!--          exam_id:  -->
								<input type="hidden"
									value="<%= (int)request.getAttribute("exam_id") %>"
									name="exam_id" /> <br /> <input type="submit"
									value="Add New Question" /> --%>
		</form>

<!-- 	</div> -->
</body>
</html>