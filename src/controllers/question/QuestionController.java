<<<<<<< HEAD
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

import datasource.DBConnection;
import domain.Question;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/questions")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionController() {
		super();
		// TODO Auto-generated constructor stub
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
//		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
//        String pathInfo = request.getPathInfo(); // /exams/{exam_id}/questions
//        String[] pathParts = pathInfo.split("/");
//        String exam_id=pathParts[1]; ; // {subject}
//        String route = ""; // exams
		int exam_id = Integer.parseInt((String) request.getAttribute("exam_id"));
		String operation = (String) request.getAttribute("operation");
		System.out.println("Question Controller_0: " + exam_id + " operation: " + operation);

		if (operation.equals("questions")) {
			List<Question> questions = new ArrayList<>();
			String stm = "select * from questions where exam_id='" + exam_id + "'";
			try {
				PreparedStatement stmt = DBConnection.prepare(stm);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id2 = Integer.parseInt(rs.getString(1));
					int type2 = Integer.parseInt(rs.getString(2));
					String title2 = rs.getString(3);
					String content2 = rs.getString(4);
					String answer2 = rs.getString(5);
					int mark2 = Integer.parseInt(rs.getString(6));
					int examId2 = Integer.parseInt(rs.getString(7));
					questions.add(new Question(id2, type2, title2, content2, answer2, mark2, examId2));
				}
			} catch (SQLException e) {

				System.out.println(e.getMessage());
			}
			request.setAttribute("exam_id",exam_id );
			request.setAttribute("questions", questions);
			request.getRequestDispatcher("./questions.jsp").forward(request, response);

		} else if (operation.equals("addQuestion")) {
			request.getRequestDispatcher("/addQuestion").forward(request, response);

		} else if (operation.equals("updateQuestion")) {
			request.getRequestDispatcher("/updateQuestion").forward(request, response);
		} else if (operation.equals("deleteQuestion")) {
			request.getRequestDispatcher("/deleteQuestion").forward(request, response);
		}

		System.out.println();
	}

}
=======
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

import datasource.DBConnection;
import domain.Question;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/questions")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionController() {
		super();
		// TODO Auto-generated constructor stub
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
//		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
//        String pathInfo = request.getPathInfo(); // /exams/{exam_id}/questions
//        String[] pathParts = pathInfo.split("/");
//        String exam_id=pathParts[1]; ; // {subject}
//        String route = ""; // exams
		int exam_id = Integer.parseInt((String) request.getAttribute("exam_id"));
		String operation = (String) request.getAttribute("operation");
		System.out.println("Question Controller_0: " + exam_id + " operation: " + operation);

		if (operation.equals("questions")) {
			List<Question> questions = new ArrayList<>();
			String stm = "select * from questions where exam_id='" + exam_id + "'";
			try {
				PreparedStatement stmt = DBConnection.prepare(stm);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					int id2 = Integer.parseInt(rs.getString(1));
					int type2 = Integer.parseInt(rs.getString(2));
					String title2 = rs.getString(3);
					String content2 = rs.getString(4);
					String answer2 = rs.getString(5);
					int mark2 = Integer.parseInt(rs.getString(6));
					int examId2 = Integer.parseInt(rs.getString(7));
					questions.add(new Question(id2, type2, title2, content2, answer2, mark2, examId2));
				}
			} catch (SQLException e) {

				System.out.println(e.getMessage());
			}

			request.setAttribute("questions", questions);
			request.getRequestDispatcher("./questions.jsp").forward(request, response);

		} else if (operation.equals("addQuestion")) {
			request.getRequestDispatcher("/addQuestion").forward(request, response);

		} else if (operation.equals("updateQuestion")) {
			request.getRequestDispatcher("/updateQuestion").forward(request, response);
		} else if (operation.equals("deleteQuestion")) {
			request.getRequestDispatcher("/deleteQuestion").forward(request, response);
		}

		System.out.println();
	}

}
>>>>>>> master
