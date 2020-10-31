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
<meta charset="UTF-8">
<title>Exam</title>
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
</head>
<body>
<a class="sel_btn" href="/login.jsp">DashBoard</a> 
<h1 style="text-align:center"><%=request.getAttribute("subject_code") %> Exams</h1> 
<div class="text-center">
 <span style="color:red"><%=(request.getSession(false).getAttribute("errMessageStudentTakeExam") == null) ? "" : request.getSession(false).getAttribute("errMessageStudentTakeExam")%></span>
 <%
session.removeAttribute("errMessageStudentTakeExam");

%>

<%
String strError = (String)request.getSession(false).getAttribute("errMessageStudentTakeExamClosed");
if (strError!=null){
	out.println("<script type=\"text/javascript\">");  
	out.println("alert('"+strError+"');");
	out.println("</script>");
}      
%>
<%
session.removeAttribute("errMessageStudentTakeExamClosed");
%>  
</div>
 <div align="center">
        <table class="table table-bordered" style="width:70%">
            <tr>
                <th>#Exam</th>
                <th>Exam Title</th>
               <!--  <th>Subject</th> -->
                <th>Operation</th>
                <th>Status</th>
                <th>Mark</th>
            </tr>
            
                <tr>
 			<%
 			     List<Exam> exams = new ArrayList<>(); 
 			     exams = (List<Exam>)request.getAttribute("exams");//获取request中名称为student的值
           		 int i=0;
 			     for (Exam exam : exams) {
       		 %>
       		        <%-- <td><%= exam.getId() %></td> --%>
                    <td><%= i=i+1 %></td>
                    <td><%= exam.getTitle() %></td>
                    <%-- <td><%= exam.getSubject()  %></td> --%>
                    <td>
	                 <%-- <a class="sel_btn" href="./updateExam?id=<%=exam.getId()%>&title=<%=exam.getTitle()%>&status=<%=exam.getStatus()%>&subject_code=<%=exam.getSubject()%>">Edit</a> --%>
	                 <%-- <a class="sel_btn" href="./questions?exam_id=<%=exam.getId()%>">Edit Questions</a> --%>
	               <a class="sel_btn" href="exams/<%=exam.getId()%>/questions">Take Exam</a>
	               <% int mark = (int) request.getAttribute("mark"+exam.getId());
	                  int flag =(int) request.getAttribute("flag"+exam.getId());%>
	                <%--  <td><%=request.getAttribute("mark"+exam.getId())%></td> --%>
	                 <% if (flag==1){ %>
	                 <td>Unfinished</td>
	                 <%  }else { %>
	                  <td>Finished</td>
	                  <%} %>
	                 <td><%= mark==0? "Unmarked":mark %>
	                 <%-- <a class="sel_btn" href="./deleteExam?subject_code=<%=exam.getSubject() %>&id=<%=exam.getId()%>">Delete</a> --%>
	                 <%-- <a class="sel_btn" href="./deleteExam/<%=exam.getId()%>/<%= exam.getStatus() %>">Delete</a> --%>
	                <%--  <button type="button" onclick="deleteExam('<%=exam.getId()%>')">Delete</button> --%> 
	                 </td>
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
         
    </div>
</body>
</html>