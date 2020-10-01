package controllers.exam;

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

import dataMapper.ExamMapper;
import domain.Exam;
import service.ExamService;

/**
 * Servlet implementation class updateExamController
 */
//@WebServlet("/updateExam")
public class updateExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamService examService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateExamController() {
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
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("update exam Controller_0: ");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		int status = Integer.parseInt(request.getParameter("status"));
		String subject = request.getParameter("subject");
		Exam exam = new Exam(id, title, status, subject);
		
//		ExamMapper examMapper = new ExamMapper();
//		try {
//			examMapper.update(exam);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		Boolean success = examService.updateExam(id, title, status, subject);
		System.out.println("update exam doPost success: "+success);
		if (success) {
			response.sendRedirect("./exams");
		}else {
			//TODO
//			request.setAttribute("errMessageExam", "something went wrong. The exam is already exist");
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageExam", "something went wrong. The exam is update error");
			response.sendRedirect("./exams");
//			doGet(request,response);
//			response.sendRedirect("./exams");
		}
		
		
		
		
		
		
		
//		try {
//			exam.updateExam(id, title, status, subject);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		List<Exam> exams = new ArrayList<>();
//		exams = exam.getAllExams(subject);
//		request.setAttribute("exams", exams);
//		request.getRequestDispatcher("./exams.jsp").forward(request, response);
		
		
		
//		response.sendRedirect("./exams");
	}

}
