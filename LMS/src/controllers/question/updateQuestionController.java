package controllers.question;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Question;
import service.QuestionService;

/**
 * Servlet implementation class updateQuestionController
 */
//@WebServlet("/updateQuestion")
public class updateQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateQuestionController() {
		super();
		// TODO Auto-generated constructor stub
		questionService = new QuestionService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("updateQuestioonController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		int type = Integer.parseInt(request.getParameter("type"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		int mark = Integer.parseInt(request.getParameter("mark"));
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		
		
		Boolean success = questionService.updateQuestion(id, type, title, content,  answer,  mark,  exam_id);
		System.out.println("update question doPost success: "+success);
		if (success) {
			response.sendRedirect("./questions");
		}else {
			//TODO
//			request.setAttribute("errMessageExam", "something went wrong. The exam is already exist");
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageQuestion", "something went wrong. The question is update error");
			response.sendRedirect("./questions");
//			doGet(request,response);
//			response.sendRedirect("./exams");
		}
		
		

//		Question question = new Question();
//		try {
//			question.updateQuestion(id, type, title, content, answer, mark, exam_id);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
////		List<Question> questions = new ArrayList<>();
////		questions = question.getAllQuestions(exam_id);
////		request.setAttribute("questions", questions);
////		request.getRequestDispatcher("./questions.jsp").forward(request, response);
//		response.sendRedirect("./questions");
		
		
		
	}

}
