package controllers.exam;

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
import domain.Exam;
import domain.Subject;
import service.ExamService;

/**
 * Servlet implementation class addExamController
 */
//@WebServlet("/addExamController")
public class addExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamService examService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addExamController() {
		super();
		examService = new ExamService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("addExamController doPost");
		//int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		int status = Integer.parseInt(request.getParameter("status"));
		String subject_code = request.getParameter("subject_code");

		Boolean success = examService.createNewExam(title, status, subject_code);
		System.out.println("add exam doPost success: "+success);
		if (success) {
			response.sendRedirect("./exams");
		}else {
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageExam", "something went wrong. The exam is already exist");
			response.sendRedirect("./exams");

		}
	}
}
