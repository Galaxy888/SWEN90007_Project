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

import domain.Exam;
import pessimistic.LockManager;
import pessimistic.LockManagerEX;
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
		System.out.println("updateExamController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		int status = Integer.parseInt(request.getParameter("status"));
		String subject = request.getParameter("subject");
		System.out.println(id+title+status+subject);
		
		
		int oldStatus = examService.getExamStatus(id);
		HttpSession httpSession = request.getSession(true);
		
		
		if(status==0&&oldStatus==0) {
			System.out.println("httpSession.getId() 1: "+httpSession.getId());	
			Boolean success = examService.updateExam(id, title, 0, subject);
			System.out.println("update exam doPost success: " + success);
			if (success) {
				response.sendRedirect("./exams");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("errMessageExam", "Someone has already updated the exam. Please view the latsest version ");
				response.sendRedirect("./exams");

			}
			System.out.println("httpSession.getId() 2: "+httpSession.getId());
			LockManager lockManager=new LockManager();
			lockManager.releaseLock(id, httpSession.getId());
		}else if(status==1) {
			
			Boolean success = examService.updateExam(id, title, 1, subject);
			System.out.println("update exam doPost success: " + success);
			if (success) {
				response.sendRedirect("./exams");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("errMessageExam", "Someone has already updated the exam. Please view the latsest version ");
				response.sendRedirect("./exams");

			}
			
			LockManager lockManager=new LockManager();
			lockManager.releaseLock(id, httpSession.getId());
		}else {
			response.sendRedirect("./exams");
			
		}

		
	}

}
