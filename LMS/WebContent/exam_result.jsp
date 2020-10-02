<%@ page import="domain.Mark" 
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
<title>Insert title here</title>
<script type="text/javascript">
    function showInfo2(id,eid,mark,status) {
        document.getElementById("updateId").value = id;
        document.getElementById("updateEId").value = eid;
        document.getElementById("updateMark").value = mark;
        document.getElementById("updateStatus").value = status;
    }
    </script>
</head>
<body>
<a class="sel_btn" href="/LMS/login.jsp">DashBoard</a> 
 <div align="center">
        <table  style="width:70%">
            <tr>
                <th>User Id</th>
                <th>Mark</th>
                <th>Status</th>
                <th>Operation</th>
            </tr>
            
                <tr>
 			<%
 			     List<Mark> marks = new ArrayList<>(); 
 			     marks = (List<Mark>)request.getAttribute("marks");//获取request中名称为student的值
           		 for (Mark mark : marks) {
       		 %>
       		        <td><%= mark.getId() %></td>
                    <td><%= mark.getMark() %></td>
                    <td><%= mark.getStatus() %></td>
                    <td>
	                <button type="button" class="sel_btn" data-toggle="modal" data-target="#updateModal" id="btn_update" 
                    onclick="showInfo2('<%= mark.getId() %>','<%= mark.getEId() %>','<%= mark.getMark() %>','<%= mark.getStatus() %>')">
                    Update</button>
	                 </td>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
          </div>
                 <form class="form-horizontal" method="post" action="updateResult">     
									<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="updateModalLabel">
														Update result info
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------form-------------------->
										
									<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">user_id</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateId" name="user_id"  placeholder="exam id">
												<label class="control-label" for="updateId" style="display: none;"></label>
												</div>
										</div>
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">exam_id</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateEId" name="exam_id"  placeholder="input new title">
												<label class="control-label" for="updateEId" style="display: none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">mark</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateMark" name="mark"  placeholder="input new status">
												<label class="control-label" for="updateMark" style="display: none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">status</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateStatus" name="status"  placeholder="subject">
												<label class="control-label" for="updateStatus" style="display: none;"></label>
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
                                 
    </div>
</body>
</html>