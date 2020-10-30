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
import service.QuestionService;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/questions")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamService examService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionController() {
		super();
		examService = new ExamService();
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int exam_id = Integer.parseInt((String) request.getAttribute("exam_id"));
	
		String operation = (String) request.getAttribute("operation");
		String subject_code = (String) request.getAttribute("subject_code");
		System.out.println("Question Controller_0: " + exam_id + " operation: " + operation);
		HttpSession session = request.getSession(false);
		String userType = (String) session.getAttribute("userType");
		if (operation.equals("questions")) {
			if (userType.equals("Instructor")) {
				List<Question> questions = examService.getAllQuestions(exam_id);
//			String stm = "select * from questions where exam_id='" + exam_id + "'";
//			try {
//				PreparedStatement stmt = DBConnection.prepare(stm);
//				ResultSet rs = stmt.executeQuery();
//				while (rs.next()) {
//					int id2 = Integer.parseInt(rs.getString(1));
//					int type2 = Integer.parseInt(rs.getString(2));
//					String title2 = rs.getString(3);
//					String content2 = rs.getString(4);
//					String answer2 = rs.getString(5);
//					int mark2 = Integer.parseInt(rs.getString(6));
//					int examId2 = Integer.parseInt(rs.getString(7));
//					questions.add(new Question(id2, type2, title2, content2, answer2, mark2, examId2));
//				}
//			} catch (SQLException e) {
//
//				System.out.println(e.getMessage());
//			}
				if (questions != null) {
					request.setAttribute("exam_id", exam_id);
					request.setAttribute("questions", questions);
					request.setAttribute("subject_code", subject_code);

					request.getRequestDispatcher("./questions.jsp").forward(request, response);

				} else {
					// TODO
					session.setAttribute("errMessageQuestion", "something went wrong.");
					response.sendRedirect("./questions");
				}

			} else if (userType.equals("Student")) { // show all questions and if the user take the exam before,flag =1, else flag=0
				System.out.print("Student");
				int flag = 0;
				int user_id = (int) session.getAttribute("user_id");								
				String sql1 = "select * from users_exams where user_id='" + user_id + "'and exam_id='" + exam_id + "'";
				String sql2 = "INSERT INTO users_exams VALUES (?, ?,0,0,0)";
				String sql3 = "select * from exams where id = '"+ exam_id+"' limit 1";
				try {
					PreparedStatement stmt = DBConnection.prepare(sql1);
					ResultSet rs = stmt.executeQuery();
					if (!rs.next()) {
						flag = 1;
						PreparedStatement insertStatement = DBConnection.prepare(sql2);
						insertStatement.setInt(1, user_id);
						insertStatement.setInt(2, exam_id);
						insertStatement.execute();
					}
					else {
						flag = 0;
					}
					int status = 0;
					PreparedStatement stm = DBConnection.prepare(sql3);
					ResultSet result = stm.executeQuery();
					if(result.next()) {
						status = Integer.parseInt(rs.getString(3));
					}
					if (status==3) {
						flag = 0;
					}
				} catch (SQLException e) {

					System.out.println(e.getMessage());
				} 
				    
				
				
				
				
//					List<Question> questions = new ArrayList<>();
//					String stm = "select * from questions where exam_id='" + exam_id + "'";
//					try {
//						PreparedStatement stmt = DBConnection.prepare(stm);
//						ResultSet rs = stmt.executeQuery();
//						while (rs.next()) {
//							int id2 = Integer.parseInt(rs.getString(1));
//							int type2 = Integer.parseInt(rs.getString(2));
//							String title2 = rs.getString(3);
//							String content2 = rs.getString(4);
//							String answer2 = rs.getString(5);
//							int mark2 = Integer.parseInt(rs.getString(6));
//							int examId2 = Integer.parseInt(rs.getString(7));
//							questions.add(new Question(id2, type2, title2, content2, answer2, mark2, examId2));
//						}
//					} catch (SQLException e) {
//
//						System.out.println(e.getMessage());
//					}
					request.setAttribute("flag", flag);
					List<Question> questions = examService.getAllQuestions(exam_id);
					request.setAttribute("exam_id", exam_id);
					request.setAttribute("questions", questions);
					request.getRequestDispatcher("./take_question.jsp").forward(request, response);
				
			}

		} else if (operation.equals("addQuestion")) {
			request.getRequestDispatcher("/addQuestion").forward(request, response);

		} else if (operation.equals("updateQuestion")) {
			request.getRequestDispatcher("/updateQuestion").forward(request, response);
		} else if (operation.equals("deleteQuestion")) {
			request.getRequestDispatcher("/deleteQuestion").forward(request, response);
		} else if (operation.equals("TakeQuestion")) {
			request.getRequestDispatcher("/TakeQuestion").forward(request, response);
		} else if (operation.equals("exams")) {
			request.getRequestDispatcher("/exams").forward(request, response);
		} else if (operation.equals("ViewAnswer")) {
			request.getRequestDispatcher("/ViewAnswer").forward(request, response);
		} else if (operation.equals("markAnswerEdit")) {
			request.getRequestDispatcher("/markAnswerEdit").forward(request, response);
		} else if (operation.equals("markAnswer")) {
			request.getRequestDispatcher("/markAnswer").forward(request, response);
		} else if (operation.equals("ViewMark")) {
			request.getRequestDispatcher("/ViewMark").forward(request, response);
		} else if (operation.equals("updateResult")) {
			request.getRequestDispatcher("/updateResult").forward(request, response);
		}
		System.out.println();
	}

}
