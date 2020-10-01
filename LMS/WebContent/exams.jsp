<%@ page import="domain.Exam" 
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
<title>Exams</title>
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
    function showInfo2(id,title,status,subject) {
        document.getElementById("updateId").value = id;
        document.getElementById("updateTitle").value = title;
        document.getElementById("updateStatus").value = status;
        document.getElementById("updateSubject").value = subject;
    }
    </script>
</head>
<body>
<a class="sel_btn" href="/dashboard">DashBoard</a> 
 <div align="center">
        <table  style="width:70%">
            <tr>
                <th>exam Id</th>
                <th>exam Title</th>
                <th>exam Status</th>
                <th>Subject</th>
                <th>Operation</th>
            </tr>
            
                <tr>
 			<%
 			     List<Exam> exams = new ArrayList<>(); 
 			     exams = (List<Exam>)request.getAttribute("exams");//获取request中名称为student的值
           		 for (Exam exam : exams) {
       		 %>
       		        <td><%= exam.getId() %></td>
                    <td><%= exam.getTitle() %></td>
                    <td><%= exam.getStatus() %>
                    <td><%= exam.getSubject()  %></td>
                    <td>
                    <button type="button" class="sel_btn" data-toggle="modal" data-target="#updateModal" id="btn_update" 
                    onclick="showInfo2('<%= exam.getId() %>','<%= exam.getTitle() %>','<%= exam.getStatus() %>','<%= exam.getSubject() %>')">
                    Update</button>
	                 <%-- <a class="sel_btn" href="./updateExam?id=<%=exam.getId()%>&title=<%=exam.getTitle()%>&status=<%=exam.getStatus()%>&subject_code=<%=exam.getSubject()%>">Edit</a> --%>
	                 <%-- <a class="sel_btn" href="./questions?exam_id=<%=exam.getId()%>">Edit Questions</a> --%>
	                 <a class="sel_btn" href="exams/<%=exam.getId()%>/questions">Edit Questions</a>
	                 <%--<a class="sel_btn" href="./deleteExam?subject_code=<%=exam.getSubject() %>&id=<%=exam.getId()%>">Delete</a>--%>
	                 <%-- <a class="sel_btn" href="./deleteExam/<%=exam.getId()%>/<%= exam.getStatus() %>">Delete</a> --%>
	                <%--  <button type="button" onclick="deleteExam('<%=exam.getId()%>')">Delete</button> --%>
	                 <form name="delete" method=post action="deleteExam">
	                 <input name="id" type="hidden" value=<%=exam.getId()%>>
	                 <input type = "submit" value = "Delete" />
	                 </form>

	                 
	                 </td>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
         
    </div>
                 <form class="form-horizontal" method="post" action="updateExam">     
									<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="updateModalLabel">
														Update exam info
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------form-------------------->
										
									<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">id</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateId" name="id"  placeholder="exam id">
												<label class="control-label" for="updateId" style="display: none;"></label>
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
											<label for="firstname" class="col-sm-3 control-label">Status</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateStatus" name="status"  placeholder="input new status">
												<label class="control-label" for="updateStatus" style="display: none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Subject</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateSubject" name="subject"  placeholder="subject">
												<label class="control-label" for="updateSubject" style="display: none;"></label>
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
    
    <form name="AddExamController" action="addExam" method="post">
         id : <input type = "text" name = "id">
         <br />
         title: <input type = "text" name = "title">
         <br />
         status:<input type = "text" name="status">
         <br />
         subject_code: <input type = "text" value="<%= (String)request.getAttribute("subject_code")%>" name = "subject_code" />
         <br />
         <input type = "submit" value = "Add New Exam" />
      </form>
    
    </div>
</body>
</html>