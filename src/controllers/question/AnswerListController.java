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
import domain.Answer;
/**
 * Servlet implementation class AnswerListController
 */
//@WebServlet("/AnswerListController")
public class AnswerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerListController() {
        super();
        // TODO Auto-generated constructor stub
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
		 Question question2 = new Question();
		 List<Answer> answers = new ArrayList<>();
		 List<Question> questions = new ArrayList<>();
		 questions = question2.getAllQuestions(exam_id);
		 for (Question question: questions) {
			 List<Answer> answers1 = new ArrayList<>();
			 answers1 = new Answer().getAllAnswer(question.getId());
			 answers.addAll(answers1);
		 }
		 
		request.setAttribute("answers", answers);
		request.setAttribute("exam_id",exam_id);
		request.getRequestDispatcher("./answers.jsp").forward(request, response);
		 
	}

}
