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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>	
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
.form-popup {
  display: none;
 /*  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1; */
  z-index: 9;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script type="text/javascript">

	$( '#btn_test' ).click(function(){
		alert('test');
	}); 

/* 	 $.ajax({
		  method: "POST",
		  url: "/updateEditExam",
		  data: { username: "username", password: "password" }
		})
		.success(function(){
		  //do success stuff
		})
		.error(function(){
		  //do error handling stuff
		}); */

	  /* var thebuttonclicked =$(this).attr("value"); */
	  var id_global;
	  
	  function test(id){
		  id_global=id;
	        $.ajax({
	            url: 'updateEditExam',
	            type: 'post',
	            data:  'id='+id+'&option='+"edit",
	            /* data: { username: "username", password: "password" } */
	            success: function(conn, response, options){
	                /* alert(response); */
      /*       	if(response.status==422){
	                    window.location.reload();
	            	} */
	            },
	        error:function(response){
	        	window.location.reload();
	        }
	            
	        }); // end ajax call
	  }
	  
	  function btn_cancel(){
		  $.ajax({
	            url: 'updateEditExam',
	            type: 'post',
	            data:  'id='+id_global+'&option='+"cancel",
	            /* data: { username: "username", password: "password" } */
	            success: function(conn, response, options){
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
		  if(id_global!=null){
		    $.ajax({
		    	url: 'updateEditExam',
		        async: false,
		        type: 'post',
	            data:  'id='+id_global+'&option='+"cancel",
		    });
		  }
		});
	  
/* 	  var unloaded = false;
	  $(window).on('beforeunload', unload);
	  $(window).on('unload', unload);  
	  function unload(){      
	      if(!unloaded){
	          $.ajax({
	              type: 'post',
	              url: 'updateEditExam',
	              async: false,
	              data:  'id='+id_global+'&option='+"cancel",
	              success:function(){ 
	                  unloaded = true; 
	                 
	              },
	              timeout: 5000
	          });
	      }
	  }
 */

	  
	  
    function showInfo2(id,title,status) {
        document.getElementById("updateId").value = id;
        document.getElementById("updateTitle").value = title;
        document.getElementById("updateStatus").value = status;
       /*  document.getElementById("updateSubject").value = subject; */
        /* document.getElementById("updateVersion").value = version; */
        test(id)
       
    }
    
    function openForm() {
    	  document.getElementById("myForm").style.display = "block";
    	}
    
    </script>
</head>
<body>
<a class="sel_btn" href="/dashboard">DashBoard</a> 


<%
String strError = (String)request.getSession(false).getAttribute("errMessageExam");
if (strError!=null){
	out.println("<script type=\"text/javascript\">");  
	out.println("alert('"+strError+"');");
	out.println("</script>");
}      
%>
<%
session.removeAttribute("errMessageExam");
%>  

<div>
<h1 style="text-align:center"><%=request.getAttribute("subject_code") %></h1> 
</div>
 <button type="button" class="btn btn-primary col-md-4 offset-md-4" data-toggle="modal" data-target="#addExamModal">
 Add new Exam
</button>
 <div align="center">
        <table class="table table-bordered" style="width:70%">
            <tr>
<!--                 <th>Exam Id</th> -->
				<th>#Exam</th>
                <th>Exam Title</th>
                <th>Exam Status</th>
                <th>Operation</th>
                <!-- <th>Version</th> -->
            </tr>
            
                <tr>
 			<%
 			     List<Exam> exams = new ArrayList<>(); 
 			     exams = (List<Exam>)request.getAttribute("exams");//获取request中名称为student的值
 			     int i=0;
           		 for (Exam exam : exams) {
           		    int flag=0;
           			String sql = "select * from users_exams where exam_id='"+exam.getId()+"'";
    				try {
    					PreparedStatement stmt = DBConnection.prepare(sql);
    					ResultSet rs = stmt.executeQuery();
    					if (rs.next()) {
    					  flag = 1;
    					}
    					} catch (SQLException e) {
    					}
       		 %>
<%--        		        <td><%= exam.getId() %></td> --%>
                    <td><%= i=i+1 %>
                    <td><%= exam.getTitle() %></td>
                    <td><%= exam.getStatus()==0 ? "unpublished":"published" %>
                    <td>
                    <div>
                    <button type="button" class="sel_btn" data-toggle="modal" data-target="#updateModal" id="btn_update" 
                    onclick="showInfo2('<%= exam.getId() %>','<%= exam.getTitle() %>','<%= exam.getStatus() %>')">
                    Update Title</button>
                    
                    <button onclick="test('<%= exam.getId() %>')" type="button" class="sel_btn" id="btn_test" >test</button>
                   <a class="sel_btn" href="./updateEditExam">test2</a>
                
                     <form  style="display: inline"  name="publish" method="post" action="updateExam">
	                 <input class = "sel_btn"  name="id" type="hidden" value=<%=exam.getId()%>>
	                 <input class = "sel_btn"  name="title" type="hidden" value=<%=exam.getTitle()%>>
	                  <input class = "sel_btn"  name="status" type="hidden" value="1">
	                  <input type = "hidden" value="<%= (String)request.getAttribute("subject_code")%>" name = "subject" />
	                  <%-- <input class = "sel_btn"  name="version" type="hidden" value=<%=exam.getVersion()%>> --%>
	                 <input class = "sel_btn"  type = "submit" value = "Publish" />
	                 </form>
                    
            
	                 <%-- <a class="sel_btn" href="./updateExam?id=<%=exam.getId()%>&title=<%=exam.getTitle()%>&status=<%=exam.getStatus()%>&subject_code=<%=exam.getSubject()%>">Edit</a> --%>
	                 <%-- <a class="sel_btn" href="./questions?exam_id=<%=exam.getId()%>">Edit Questions</a> --%>
	                 <% if (flag!=1){ %>
	                 <a class="sel_btn" href="exams/<%=exam.getId()%>/questions">Edit Questions</a>
	                 <% } else{ %>
	                 <a class="sel_btn" >Cannot be updated</a>
	                 <%} %>
	                 <a class="sel_btn" href="exams/<%=exam.getId()%>/ViewAnswer">View answers</a>
	                 <a class="sel_btn" href="exams/<%=exam.getId()%>/ViewMark">View exam results</a>
	                
	                  <form  style="display: inline" name="close" method="post" action="closeExam">
	                 <input class = "sel_btn"  name="id" type="hidden" value=<%=exam.getId()%>>
	                 <input class = "sel_btn"  type = "submit" value = "Delete" />
	                 </form>
	               
	                 </div>

	                 
	                 </td>
	                 
	                 
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
         
    </div>
    
    <div>
                 <form class="form-horizontal" method="post" action="updateExam">     
									<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" data-backdrop="static" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="updateModalLabel">
														Update Exam Title
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------form-------------------->
										
<!-- 									<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Exam Id:</label>
												<div class="col-sm-7">
													<input type="number" class="form-control" id="updateId" name="id"  placeholder="exam id" required>
												<label class="control-label" for="updateId" style="display: none;"></label>
												</div>
										</div> -->
										
										<input type = "hidden" id="updateId" name="id" />
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Exam Title:</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateTitle" name="title"  placeholder="input new title" required>
												<label class="control-label" for="updateTitle" style="display: none;"></label>
												</div>
										</div>
										
										<!-- <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Exam Status:</label>
												<div class="col-sm-7">
														<select name="status" id="status">
														<option value="0">unpublished</option>
														<option value="1">published</option>
														</select>
													<input type="text" class="form-control" id="updateStatus" name="status"  placeholder="input new status">
												<label class="control-label" for="updateStatus" style="display: none;"></label>
												</div>
										</div> -->
										
										<input type = "hidden" value="<%= (String)request.getAttribute("subject_code")%>" name = "subject" />
										<input type = "hidden" id="updateVersion" name="version" />	
										<input type = "hidden" id="updateStatus" name="status" value="0"/>	
										</div>
												<div class="modal-footer">
				
													<button type="submit" class="btn btn-primary" >
														submit
													</button>
													<button onClick="btn_cancel()" type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
	
                                 </form>
                                 
 </div>                                
                                 
      <hr class="rounded">
  <hr class="rounded">
  

 <!--    <div align="center"> -->
<%-- <div class="text-center">
 <span style="color:red"><%=(request.getSession(false).getAttribute("errMessageExam") == null) ? "" : request.getSession(false).getAttribute("errMessageExam")%></span>
 <%
session.removeAttribute("errMessageExam");
s
%>
</div> --%>

		<form class="border border-light p-5 col-md-4 offset-md-4" name="AddExamController" action="addExam" method="post">
		
			
			<div class="modal fade" id="addExamModal" tabindex="-1" role="dialog" aria-labelledby="addExamModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="addExamModalLabel">
														Add new exam
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------form-------------------->
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Exam Title:</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="title" name="title"  placeholder="input new title" required>
												<label class="control-label" for="title" style="display: none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Exam Status:</label>
												<div class="col-sm-7">
														<select name="status" id="status">
														<option value="0">unpublished</option>
														<!-- <option value="1">published</option> -->
														</select>
													<!-- <input type="text" class="form-control" id="updateStatus" name="status"  placeholder="input new status"> -->
												<label class="control-label" for="status" style="display: none;"></label>
												</div>
										</div>
										
													<input type="hidden" value="<%=(String) request.getAttribute("subject_code")%>" name="subject_code" />
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
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
<!-- 			<div class="form-group row">
				<label class="col-md-4">Exam Title:</label> 
				<div >
				<input type="text" name="title" class="form-control" required>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-md-4">Exam Status:</label> 
				<div >
						<select name="status" id="status">
				<option value="0">0</option>
				<option value="1">1</option>
			</select> 
				</div>
			</div> -->
			
			
			
			
			
			
			
<!-- 			<div class="form-group">
				<label>Title:</label> <input type="text" name="id" class="form-control">
			</div> -->
			
<!-- 			Exam Id : <input type="text" name="id"> <br /> 
			Title: <input type="text" name="title"> <br /> 
			<label for="status">Status:</label>
			<input type = "text" name="status">
			<select name="status" id="status">
				<option value="0">0</option>
				<option value="1">1</option>
			</select> <br /> -->


			<!--  subject_code:  -->
<%-- 			<input type="hidden"
				value="<%=(String) request.getAttribute("subject_code")%>"
				name="subject_code" /> <br /> 
				
			<input class="btn btn-primary col-md-6  offset-md-2" type="submit"
				value="Add New Exam" /> --%>
		</form>

<!-- 	</div> -->
</body>
</html>