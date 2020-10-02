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

/**
 * Servlet implementation class TakeQuestionController
 */
//@WebServlet("/TakeQuestionController")
public class TakeQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeQuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("TakeQuestionController");
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		HttpSession session = request.getSession(false);
		int user_id=(int)session.getAttribute("user_id");
		Question question2 = new Question();
		List<Question> questions = new ArrayList<>();
	    questions = question2.getAllQuestions(exam_id);
	    for (Question question:questions) {
	    	int id = Integer.parseInt(request.getParameter("id"+question.getId()));
	    	String answer = request.getParameter("answer"+question.getId());
	    	try {
	    	String stm = "INSERT INTO users_questions VALUES (?, ?, ?,0,0)";
	    	PreparedStatement insertStatement = DBConnection.prepare(stm);
			insertStatement.setInt(1,user_id);
			insertStatement.setInt(2, id);
			insertStatement.setString(3, answer);
			insertStatement.execute();
	    	}catch (SQLException e) {

				System.out.println(e.getMessage());
			}
	    	
	    System.out.print("Success!");
	    	
	    }

//		  request.setAttribute("questions", questions);
//	      request.getRequestDispatcher("./questions.jsp").forward(request, response);
	    response.sendRedirect("/LMS/dashboard");
		
	}
	

}
