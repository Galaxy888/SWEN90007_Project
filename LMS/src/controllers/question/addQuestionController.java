package controllers.question;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.DBConnection;
import domain.Question;
import service.QuestionService;

/**
 * Servlet implementation class addQuestionController
 */
//@WebServlet("/addQuestion")
public class addQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addQuestionController() {
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
		String view = "/questions.jsp";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("addQuestioonController");
		int id = Integer.parseInt(request.getParameter("id"));
		int type = Integer.parseInt(request.getParameter("type"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		int mark = Integer.parseInt(request.getParameter("mark"));
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		
		
		Boolean success = questionService.createNewQuestion(id, type, title, content, answer, mark, exam_id);
		System.out.println("add question doPost success: "+success);
		if (success) {
			response.sendRedirect("./questions");
		}else {
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageQuestion", "something went wrong. The question is already exist");
			response.sendRedirect("./questions");
		}
		
		
		
		
//		Question question = new Question(id, type, title, content, answer, mark, exam_id);
//		System.out.print(question.insert());
//		List<Question> questions = new ArrayList<>();
//		String stm = "select * from questions where exam_id='" + exam_id + "'";
//		try {
//			PreparedStatement stmt = DBConnection.prepare(stm);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int id2 = Integer.parseInt(rs.getString(1));
//				int type2 = Integer.parseInt(rs.getString(2));
//				String title2 = rs.getString(3);
//				String content2 = rs.getString(4);
//				String answer2 = rs.getString(5);
//				int mark2 = Integer.parseInt(rs.getString(6));
//				int examId2 = Integer.parseInt(rs.getString(7));
//				questions.add(new Question(id2, type2, title2, content2, answer2, mark2, examId2));
//			}
//		} catch (SQLException e) {
//
//			System.out.println(e.getMessage());
//		}

//		  request.setAttribute("questions", questions);
//	      request.getRequestDispatcher("./questions.jsp").forward(request, response);
		
		
//		response.sendRedirect("./questions");
	}

}
