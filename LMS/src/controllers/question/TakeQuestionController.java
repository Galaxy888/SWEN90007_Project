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
import service.ExamService;
import service.UserQuestionService;

/**
 * Servlet implementation class TakeQuestionController
 */
//@WebServlet("/TakeQuestionController")
public class TakeQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamService examService;
	private UserQuestionService userQuestionService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TakeQuestionController() {
		super();
		examService = new ExamService();
		userQuestionService = new UserQuestionService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("TakeQuestionController");
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		HttpSession session = request.getSession(false);
		int user_id = (int) session.getAttribute("user_id");
		int flag = 0;
		int status = 0;
		String sql3 = "select * from users_exams where user_id='"+user_id+"' and exam_id='"+exam_id+"'limit 1";
		try {
			PreparedStatement stmt3 = DBConnection.prepare(sql3);
			ResultSet rs = stmt3.executeQuery();
			if (rs.next()) {
			 status = Integer.parseInt(rs.getString(4));
			 if (status ==3) {
			 flag = 1 ;
			 System.out.println("The exam is closed!!!!");
//			 HttpSession session = request.getSession();
				session.setAttribute("errMessageStudentTakeExamClosed", "The exam is closed!");
			 }
				
			}
			
		}catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		
		if (flag == 0) {
		
		String sql2 = "update users_exams set status=1,version=0 where user_id='"+user_id+"' and "
				+ "exam_id='"+exam_id+"'";
		try {
			PreparedStatement insertStatement = DBConnection.prepare(sql2);
			insertStatement.executeUpdate();
		}catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		List<Question> questions = examService.getAllQuestions(exam_id);

		for (Question question : questions) {
			int question_id = Integer.parseInt(request.getParameter("id" + question.getId()));
			String answer = request.getParameter("answer" + question.getId());

			Boolean success = userQuestionService.createNewUserQuestion(user_id, question_id,exam_id,answer);
             
//			try {
//				String stm = "INSERT INTO users_questions VALUES (?, ?, ?,0,0)";
//				PreparedStatement insertStatement = DBConnection.prepare(stm);
//				insertStatement.setInt(1, user_id);
//				insertStatement.setInt(2, question_id);
//				insertStatement.setString(3, answer);
//				insertStatement.execute();
//			} catch (SQLException e) {
//
//				System.out.println(e.getMessage());
//			}

			System.out.print("Success!: "+success);

		}
		}

//		  request.setAttribute("questions", questions);
//	      request.getRequestDispatcher("./questions.jsp").forward(request, response);
//		response.sendRedirect("/dashboard");
		response.sendRedirect("./examSubmit");

	}

}
