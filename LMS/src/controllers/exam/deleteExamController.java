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
import javax.servlet.http.HttpSession;

import dataMapper.ExamMapper;
import datasource.DBConnection;
import domain.Exam;
import service.ExamService;

/**
 * Servlet implementation class deleteExamController
 */
//@WebServlet("/deleteExam")
public class deleteExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamService examService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteExamController() {
		super();
		// TODO Auto-generated constructor stub
		examService = new ExamService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.sendRedirect("./exams");
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
//		Exam exam = new Exam(id,"",0,"");
//		ExamMapper examMapper = new ExamMapper();
//		examMapper.delete(exam);
//		System.out.println(Integer.parseInt(request.getParameter("status")));

		Boolean success = examService.deleteExam(id, "", 0, "");
		System.out.println("delete exam doPost success: "+success);
		
		if (success) {
			response.sendRedirect("./exams");
		}else {
			//TODO
//			request.setAttribute("errMessageExam", "something went wrong. The exam is already exist");
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageExam", "something went wrong. The exam cannot be deleted");
			response.sendRedirect("./exams");
//			doGet(request,response);
//			response.sendRedirect("./exams");
		}
		
		
		
//		exam.deleteExam(id);

//		String subject_code = request.getParameter("subject_code");
//	    List<Exam> exams = new ArrayList<>();
//	    exams = exam.getAllExams(subject_code);
//	    request.setAttribute("exams", exams);
//	    request.getRequestDispatcher("./exams.jsp").forward(request, response);
		// response.sendRedirect("exams.jsp");
		
		
		
//		response.sendRedirect("./exams");
	}

}
