<%@ page import="domain.Answer" 
    import = "domain.UserQuestion"
    import = "domain.Question"
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
<title>Answer</title>
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
<script type="text/javascript">
var value = $('#inputMark').val();
  

$( ".inputMark" ).on('input', function() {
    if ($(this).val().length>3) {
        alert('you have reached a limit of 3');       
    }
});

</script>


<%
String strError = (String)request.getSession(false).getAttribute("errMessageMark");
if (strError!=null){
	out.println("<script type=\"text/javascript\">");  
	out.println("alert('"+strError+"');");
	out.println("</script>");
}      
%>
<%
session.removeAttribute("errMessageMark");
%>  

<a class="sel_btn" href="/login.jsp">DashBoard</a> 
 <div align="center">
        <%  List<ArrayList<UserQuestion>> studentList = new ArrayList<ArrayList<UserQuestion>>(); 
            studentList = (List<ArrayList<UserQuestion>>)request.getAttribute("answerList");
            
            if(studentList.size()==0){
            	 %>
            	<h1>No one has submitted the exam yet</h1> 
            	 <% 
            }
            %>
           
            
         <%   
            for (List<UserQuestion> answers: studentList ) {
            	int id = 0;
        %>
 
        <table  class="table table-bordered"style="width:70%">
            <tr>
                <th>User Id</th>
                <th>Question Id</th>
                <th>Question Content</th>
                <th>Question Correct Answer</th>
                <th>Student Answer</th>
                <th>Input Mark</th>
                <th>Current Mark</th>
                <th>Version</th>
            </tr>
            <form name="mark" method="post" action="markAnswer">
            <input name="exam_id" type="hidden" value=<%=(int)request.getAttribute("exam_id") %>>
            
                <tr>
 			<%   
 		
           		 for (UserQuestion answer : answers) {
           			 Question question = new Question();
           			 id = answer.getQuestion_id();
           			 String sql = "select * from questions where id = '"+ id+"' limit 1";
       				 String sql2 = "select version from users_questions where user_id = '"+ answer.getUser_id()+"' and question_id = '"+answer.getQuestion_id()+"'";
           			 
           			PreparedStatement stmt2 = DBConnection.prepare(sql2);
       				ResultSet rs2 = stmt2.executeQuery();
       				int markVersion=0;
       				if(rs2.next()){
       					markVersion = Integer.parseInt(rs2.getString(1));
       				}
           			 
           			 try{
           				PreparedStatement stmt = DBConnection.prepare(sql);
           				ResultSet rs = stmt.executeQuery();
           				while (rs.next()) {
           					int q_id = Integer.parseInt(rs.getString(1));
           					int type = Integer.parseInt(rs.getString(2));
           					String title = rs.getString(3);
           					String content = rs.getString(4);
           					String q_answer = rs.getString(5);
           					int mark = Integer.parseInt(rs.getString(6));
           					int examId = Integer.parseInt(rs.getString(7));
           					int version = Integer.parseInt(rs.getString(10));
           					question = new Question(q_id,type,title,content,q_answer,mark,examId,version);
           				}
           			 } catch (SQLException e) {
           				 
           			 }
       		 %>
       		        <td><%= answer.getUser_id() %></td>
                    <td><%= answer.getQuestion_id() %></td>
                    <td><%= question.getContent() %>
                    <td><%= question.getAnswer() %>
                    <td><%= answer.getAnswer() %>
                    <td>
                     <input name="user_id" type="hidden" value=<%=answer.getUser_id() %>>
	                 <input name="id<%=answer.getUser_id() %>" type="hidden" value=<%=answer.getUser_id()%>>
	                 <input name="qid<%=answer.getQuestion_id() %>" type="hidden" value=<%=answer.getQuestion_id()%>>
	                 <input id='inputMark' type = "number" style="width:65px"
	                 maxlength="4" 
	                 oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
	                 
	                 name="mark<%=answer.getUser_id() %><%=answer.getQuestion_id() %>" required>/<%= question.getMark() %>
	                </td>
	                <td><%= answer.getMark() %>/<%= question.getMark() %></td>
	                <td><input type = "hidden" id="version" name="version" value=<%= markVersion%>><%= markVersion%></td>
	                
                </tr>
            <%
          		  } // for loop
        	%>
        </table>
           
             <input type = "submit" value = "Mark" />
          
         </form>
         <% } %>
         
    </div>
</body>
</html>