<%@ page import="domain.Subject" 
    import="domain.User"
    import="datasource.DBConnection"
    import="java.sql.*"
    import="java.util.*"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<% //In case, if Instructor session is not set, redirect to Login page
if((request.getSession(false).getAttribute("userName")== null) )
{
	 response.sendRedirect("./login.jsp");
}

%>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>	

</head>

<style>
/* table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
} */

 .row > div {
  flex: 1; 
/*   background: lightgrey; */
  border-right: 1px solid black;
} 

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
  
<meta charset="UTF-8">
<title>DashBoard</title>
</head>
<body>
<div>Name: <%= request.getSession(false).getAttribute("userName")%></div>
<div>Type: <%= request.getSession(false).getAttribute("userType")%></div>
    <div align="center">
        <table class="table table-bordered"  style="width:70%">
            <tr>
                <th>Subject Code</th>
                <th>Subject Name</th>
                 <%
                 String Type = (String)request.getAttribute("user_type");
                 if (Type.equals("Admin")){ %>
                 <th>Student list</th>
                 <th>Instructor list</th>
                 <% } else { %>
                <th>Operation</th>
                <% } %>
              
            </tr>
            
                <tr>
 			<%
 			     //String Type = (String)request.getAttribute("user_type");
 		         List<Subject> subjects = new ArrayList<>(); 
		         subjects = (List<Subject>)request.getAttribute("subjects");//获取request中名称为student的值
           		 for (Subject subject : subjects) {
           			 List<User> students =  (List<User>)request.getAttribute("students"+subject.getSubjectCode());
           			 List<User> tutors =  (List<User>)request.getAttribute("tutors"+subject.getSubjectCode());
           			
       		 %>
       		        <td><%= subject.getSubjectCode() %></td>
                    <td><%= subject.getName() %></td>
	         
	                 	<%-- <a href="./exams?subject_code=<%=subject.getSubjectCode()%>">Check</a> --%>
	                 <%-- <a href="<%=request.getContextPath()%>/exams">Check</a> --%>
	                 <%-- <a href="courses/<%=subject.getSubjectCode()%>/exams/<%= (String)request.getAttribute("user_type")%>">Check</a> --%>
	                 <%if (Type.equals("Admin")){ 
	                %>
<!-- 	                 <button type="button" class="btn btn-primary col-md-4 offset-md-4" data-toggle="modal" data-target="#studentListModal">
 Check Students 
</button> -->

    <div>
                 <form class="form-horizontal" method="post" action="">     
									<div class="modal fade" id="studentListModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title" id="updateModalLabel">
														Student List
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------form-------------------->
			
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Student ID</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateTitle" name="title"  placeholder="input new title" required>
												<label class="control-label" for="updateTitle" style="display: none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">Student Name</label>
												<div class="col-sm-7">
													
													<!-- <input type="text" class="form-control" id="updateStatus" name="status"  placeholder="input new status"> -->
												<label class="control-label" for="updateStatus" style="display: none;"></label>
												</div>
										</div>
										
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
	
                                 </form>
                                 
 </div>                                


	                <%	 
	                 }
	                 %>
	                 
	                 
	                 <%if (Type.equals("Admin")){ 
	                	 
	                %>
	                <td>
	                <% 
	                	 for (User user:students){
	                 %>
	                    <%= user.getName() %><br>
	                 <% } 
	                %>
	                </td>
	                <td>
	                <% 
	                   for(User user2: tutors){ %>
	                     <%= user2.getName() %><br>
	                     <% }%>
	                
	                </td>
	                
	                <%
	                 }
	                	 else { %>
	                 <td><a class="sel_btn" href="courses/<%=subject.getSubjectCode()%>/exams">Check</a></td>
	                 <% } %>
	                 
                </tr>
                
            <%
          		  } // for loop
        	%>
        </table>
    </div>
    
    

    
    <div style="text-align: mid">
<a class="btn btn-dark" href="<%=request.getContextPath()%>/logout">Logout</a></div>

     <hr class="rounded">
  <hr class="rounded">
   <%if (Type.equals("Admin")){ %>
   
   
   
 <div class="container">
  <div class="row">
    <div class="col-sm">
    
    <form  class="border border-light"
    name="addSubject" action="addSubject" method="post">
    		<div class="form-group">
			<label> Subject code:&nbsp;&nbsp;</label> 
			<input type="text" name="code" required>
		</div>
		    		<div class="form-group">
			<label> Subject Name: </label> 
			<input type="text" name="name" required>
		</div>
		 <div align="center">
         <input type = "submit" value = "Add New Subject" required>
         </div>
      </form>
  
    </div>
    <div class="col-sm">
      <form class="border border-light"
      name="addUser" action="addUser" method="post">
          		<div class="form-group">
			<label>User name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
			<input type="text" name="name" required>
		</div>
		    		<div class="form-group">
			<label>User Password:</label> 
			<input type="text" name="password" required>
		</div>
		
		<div class="form-group">
			<label>User type:</label> 
				<select name="type" id="type">
		<option value="2">Instructor</option>
		<option value="3">Student</option>
		</select>  
			</div>       
		 <div align="center">
         <input type = "submit" value = "Add New User" required>
         </div>
      </form>
    </div>
    <div class="col-sm">
         <form class="border border-light"
         name="assignUser" action="assignUser" method="post">
         
         
         	<div class="text-center">
			<!-- <p class="text-center h1 mb-4">Log in</p> -->
			<span class="center-block" style="color: red"><%=(request.getSession(false).getAttribute("errMessageExam") == null) ? ""
		: request.getSession(false).getAttribute("errMessageExam")%></span>
			<%
session.removeAttribute("errMessageExam");

%>
		</div>
		
                   		<div class="form-group">
			<label>User id:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
			<input type=number name="id" required>
		</div>
		          		<div class="form-group">
			<label>Subject code:</label> 
			<input type="text" name="code" required>
		</div>
		 <div align="center">
         <input type = "submit" value = "Assign User" required >
         </div>
      </form>

  </div>
</div>
   
    </div>
    
          <hr class="rounded">
  <hr class="rounded">
    
    
    
    
    
    
     <div align="center">
      <table class="table table-bordered"  style="width:70%">
            <tr>
                <th>User Id</th>
                <th>User Name</th>
                <th>User Password</th>
                <th>User Type</th>
            </tr>
            
                <tr>
 			<%
 			     List<User> users = new ArrayList<>(); 
 			     users = (List<User>)request.getAttribute("users");//获取request中名称为student的值
           		 for (User user: users) {
       		 %>
       		        <td><%= user.getId() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getPassword()  %></td>
                    <% if(user.getType()==2){ %>
                    <td> Instructor</td>
                    <% } else {%>
                    <td> Student</td>
                    <%} %>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
    <%} %>
   </div>


</body>
</html>