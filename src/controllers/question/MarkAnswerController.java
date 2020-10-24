package controllers.question;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.DBConnection;
import domain.Answer;
import domain.Question;
import mapper.AnswerMapper;
import service.QuestionService;

/**
 * Servlet implementation class MarkAnswerController
 */
//@WebServlet("/MarkAnswerController")
public class MarkAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarkAnswerController() {
        super();
        questionService = new QuestionService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("MarkAnswerController");
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int mark_before = 0;
		System.out.print(exam_id);
		System.out.print(user_id);
		Question question2 = new Question();
		 List<Answer> answers = new ArrayList<>();
		 List<Question> questions = new ArrayList<>();
		 questions = question2.getAllQuestions(exam_id);
		 for (Question question: questions) {
			 List<Answer> answers1 = new ArrayList<>();
             answers1 = AnswerMapper.getAllAnswer(question.getId(),user_id);
			// answers1 = questionService.getAllAnswers(question.getId());
			 answers.addAll(answers1);
		 }
		 System.out.print("answers"+answers);
		 for (Answer answer : answers) {
				int id = Integer.parseInt(request.getParameter("id"+answer.getId()));
				System.out.print(id);
		    	int qid = Integer.parseInt(request.getParameter("qid"+answer.getQuestion_id()));
//		    	int mark = Integer.parseInt(request.getParameter("mark"+answer.getId()+answer.getQuestion_id()));
//		    	System.out.print(mark);
		    	int mark=0;
		    	String Answer=answer.getAnswer();
		    	try {
		    	String stm1 = "select * from users_questions where user_id='"+id+"' and question_id='"+ qid+"' limit 1";
		    	String stm = "update users_questions set answer=?,mark=?,status=? where user_id=? and question_id=?";
		    	String sql1 = "select * from users_exams where user_id='"+id+"' and exam_id='"+exam_id+"' limit 1";
		    	String sql2 = "update users_exams set mark=?, status=1 where user_id=? and exam_id=?";
		    	int before_mark =0;
		    	PreparedStatement search1 = DBConnection.prepare(stm1);
		    	ResultSet rs1 = search1.executeQuery();
		    	if (rs1.next()) {
		    		before_mark = Integer.parseInt(rs1.getString(5));
		    		System.out.print("Previous mark"+before_mark);
		    	}
		    	if (before_mark!=0) {
		    		mark = before_mark;
		    	}else {
		    		mark = Integer.parseInt(request.getParameter("mark"+answer.getId()+answer.getQuestion_id())); 
		    	}
		    	
		    	PreparedStatement search = DBConnection.prepare(sql1);
		    	ResultSet rs = search.executeQuery();
		    
		    	while(rs.next()) {
//		    	if(Integer.parseInt(rs.getString(4))!=1){
		    	mark_before=Integer.parseInt(rs.getString(3));
//		    	}else {
//		    		flag=1;
//		    	}
		    	System.out.print(mark_before);
		    	}
		    	int mark_current = mark+mark_before;
		    	PreparedStatement update = DBConnection.prepare(sql2);
		    	update.setInt(1,mark_current);
		    	update.setInt(2,id);
		    	update.setInt(3,exam_id);
		    	update.executeUpdate();
		    	
		    	
		    	PreparedStatement insertStatement = DBConnection.prepare(stm);
		    	insertStatement.setString(1,Answer);
				insertStatement.setInt(2,mark);
				insertStatement.setInt(3, 1);
				insertStatement.setInt(4, id);
				insertStatement.setInt(5, qid);
				
				insertStatement.executeUpdate();
		
		    	
		    	}catch (SQLException e) {

					System.out.println(e.getMessage());
				}
			    
	    System.out.print("Success!");
	    	
	    }

//		  request.setAttribute("questions", questions);
	
	      response.sendRedirect("./markExamDone");
	}

}
