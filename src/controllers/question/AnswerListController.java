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
import domain.Question;
import domain.UserQuestion;
import mapper.AnswerMapper;
import service.ExamService;
import service.MarkService;
import service.QuestionService;
import domain.Answer;
import domain.Mark;
/**
 * Servlet implementation class AnswerListController
 */
//@WebServlet("/AnswerListController")
public class AnswerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamService examService;
	private QuestionService questionService;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerListController() {
        super();
        examService = new ExamService();
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
		System.out.println("AnswerListController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int exam_id = Integer.parseInt((String)request.getAttribute("exam_id"));
		 System.out.print(exam_id);
		// find all the users by exam_id and find all questions at this user, 
		//Using sublist, the first elements of this sublist is the user id
		 List<Mark> users = new ArrayList<>();
		 users = examService.getAllMarks(exam_id);
		 System.out.print(users);
		 
		 List<ArrayList<UserQuestion>> StudentList = new ArrayList<ArrayList<UserQuestion>>();
		 for(Mark user:users) {
			 ArrayList<UserQuestion> questionList = new ArrayList<UserQuestion>();
			 int id = user.getId();
			 int mark_before = 0;
			 int flag = 0;
			 String sql3= "select * from users_exams where user_id='"+id+"' and exam_id='"+exam_id+"' limit 1";
			 try { 
			 PreparedStatement search2 = DBConnection.prepare(sql3);
		    	ResultSet rs3 = search2.executeQuery();
		    	if(rs3.next()) {
//		    	if(Integer.parseInt(rs.getString(4))!=1){
		    	mark_before=Integer.parseInt(rs3.getString(3));
//		    	}else {
//		    		flag=1;
//		    	}
		    	System.out.print(mark_before);
		    	}
		    	if (mark_before!=0) {
		    		flag = 1;
		    	} } catch (SQLException e) {

					System.out.println(e.getMessage());
				}
			 request.setAttribute(id+"flag",flag);
			 questionList = new UserQuestion().getAllQuestionsbyId(id,exam_id);
		     StudentList.add(questionList);
		 }
        System.out.print(StudentList);
         
         
         
         
//		 Question question2 = new Question();
		 List<Answer> answers = new ArrayList<>();
//		 List<Question> questions = new ArrayList<>();
//		 questions = question2.getAllQuestions(exam_id);
		 List<Question> questions = examService.getAllQuestions(exam_id);
		 
		 for (Question question: questions) {
			 List<Answer> answers1 = new ArrayList<>();
			
			 answers1 = questionService.getAllAnswers(question.getId());
			 answers.addAll(answers1);
		 }
		 
		request.setAttribute("answers", answers);
		request.setAttribute("answerList", StudentList);
		request.setAttribute("exam_id",exam_id);
		request.getRequestDispatcher("./answers.jsp").forward(request, response);
		 
	}

}
