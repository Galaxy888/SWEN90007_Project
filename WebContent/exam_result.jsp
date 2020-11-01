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
<title>Exam result</title>
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
<script type="text/javascript">
    function showInfo2(id,eid,mark,status,version) {
        document.getElementById("updateId").value = id;
        document.getElementById("updateEId").value = eid;
        document.getElementById("updateMark").value = mark;
        document.getElementById("updateStatus").value = status;
        document.getElementById("updateVersion").value = version;
    }
    </script>
</head>
<body>


<%
String strError = (String)request.getSession(false).getAttribute("errMessageResult");
if (strError!=null){
	out.println("<script type=\"text/javascript\">");  
	out.println("alert('"+strError+"');");
	out.println("</script>");
}      
%>
<%
session.removeAttribute("errMessageResult");
%>  

<a class="sel_btn" href="/login.jsp">DashBoard</a>
<a class="sel_btn" href="/courses/<%=request.getAttribute("subject_code")%>/exams">Previous page</a>
<h1 style="text-align:center"><%=request.getAttribute("subject_code") %> Exam Mark</h1> 
 <div align="center" class="col-auto">
        <table class="table table-bordered" style="width:70%">
            <tr>
                <th>Student Id</th>
                <th>Final Mark</th>
                <!-- <th>Status</th> -->
                <th>Operation</th>
                <th>Version</th>
            </tr>
            
                <tr>
 			<%
 			     List<Mark> marks = new ArrayList<>(); 
 			     marks = (List<Mark>)request.getAttribute("marks");//获取request中名称为student的值
           		 for (Mark mark : marks) {
       		 %>
       		        <td><%= mark.getId() %></td>
                    <td><%= mark.getMark() %></td>
                 <%--    <td><%= mark.getStatus() %></td> --%>
                    <td>
	                <button type="button" class="sel_btn" data-toggle="modal" data-target="#updateModal" id="btn_update" 
                    onclick="showInfo2('<%= mark.getId() %>','<%= mark.getEId() %>','<%= mark.getMark() %>','<%= mark.getStatus() %>','<%= mark.getVersion() %>')">
                    Update</button>
	                 </td>
	                 <td><%= mark.getVersion() %></td>
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
														Update Final Mark
													</h4>
												</div>
												<div class="modal-body">
												
										<input type="hidden" class="form-control" id="updateId" name="user_id"  placeholder="exam id">
										<input type="hidden" class="form-control" id="updateEId" name="exam_id"  placeholder="input new title">
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Final Mark</label>
												<div class="col-sm-7">
													<input type="number" class="form-control"  maxlength="4" min="0" max=<%=request.getAttribute("total_mark")%> style="width:80px" id="updateMark" name="mark" >/ <%=request.getAttribute("total_mark") %>
												
												<label class="control-label" for="updateMark" style="display: none;"></label>
												</div>
										</div>
									
										
										<input type="hidden" class="form-control" id="updateStatus" name="status"  placeholder="subject">
										<input type = "hidden" id="updateVersion" name="version" />		
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