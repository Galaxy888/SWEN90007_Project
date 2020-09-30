package controllers.exam;

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

import dataMapper.ExamMapper;
import datasource.DBConnection;
import domain.Exam;

/**
 * Servlet implementation class deleteExamController
 */
//@WebServlet("/deleteExam")
public class deleteExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteExamController() {
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
		System.out.println("deleteExamController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//		int id = Integer.parseInt((String) request.getAttribute("exam_id"));
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("exam_id: " + id);
		// TODO need update
		Exam exam = new Exam(id,"",0,"");
		ExamMapper examMapper = new ExamMapper();
		examMapper.delete(exam);
//		exam.deleteExam(id);

//		String subject_code = request.getParameter("subject_code");
//	    List<Exam> exams = new ArrayList<>();
//	    exams = exam.getAllExams(subject_code);
//	    request.setAttribute("exams", exams);
//	    request.getRequestDispatcher("./exams.jsp").forward(request, response);
		// response.sendRedirect("exams.jsp");
		response.sendRedirect("./exams");
	}

}
