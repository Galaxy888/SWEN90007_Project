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
    function showInfo2(id,type,title,content,mark,answer,exam) {
    	document.getElementById("updateId").value = id;
        document.getElementById("updateType").value = type;
        document.getElementById("updateTitle").value = title;
        document.getElementById("updateContent").value = content;
        document.getElementById("updateMark").value = mark;
        document.getElementById("updateAnswer").value = answer;
        document.getElementById("updateExam").value = exam;
    }
    </script>
</head>
<body>
<a class="sel_btn" href="/dashboard">Dashboard</a> 
<span style="color:red"><%=(request.getSession(false).getAttribute("errMessageQuestion") == null) ? "" : request.getSession(false).getAttribute("errMessageQuestion")%></span>
 <%
session.removeAttribute("errMessageQuestion");

%>
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
	                 <form name="delete" method=post action="deleteQuestion">
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
									<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">id</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateId" name="id"  placeholder="question id">
												<label class="control-label" for="updateId" style="display: none;"></label>
												</div>
										</div>
										
									<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">type</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateType" name="type"  placeholder="question type">
												<label class="control-label" for="updateType" style="display: none;"></label>
												</div>
										</div>
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">title</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateTitle" name="title"  placeholder="input new title">
												<label class="control-label" for="updateTitle" style="display: none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">content</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateContent" name="content"  placeholder="input new content">
												<label class="control-label" for="updateContent" style="display: none;"></label>
												</div>
										</div>
											
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">answer</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateAnswer" name="answer"  placeholder="input new answer">
												<label class="control-label" for="updateAnswer" style="display: none;"></label>
												</div>
										</div>
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">mark</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateMark" name="mark"  placeholder="input new mark">
												<label class="control-label" for="updateMark" style="display: none;"></label>
												</div>
										</div>
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">exam_id</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateExam" name="exam_id"  placeholder="exam_id">
												<label class="control-label" for="updateExam" style="display: none;"></label>
												</div>
										</div>
											
										</div>
												<div class="modal-footer">
				
													<button type="submit" class="btn btn-primary" >
														modify
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
	
                                 </form>
                                 
                                 
                                 
      <hr class="rounded">
  <hr class="rounded">
    
    <div align="center">
    
    <form name="AddQuestionController" action="addQuestion" method="post">
       
         type :<input type = "text" name = "type">
         <br />
         title: <input type = "text" name = "title">
         <br />
         content:<input type = "text" name="content">
         <br />
         Answer:<input type = "text" name="answer">
         <br />
         mark:<input type = "text" name="mark">
         <br />
         exam_id: <input type = "text" value="<%= (int)request.getAttribute("exam_id") %>" name = "exam_id" />
         <br />
         <input type = "submit" value = "Add New Question" />
      </form>
    
    </div>
</body>
</html>