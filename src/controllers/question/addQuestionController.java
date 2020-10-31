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
import pessimistic.LockManager;
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
//		Boolean success = false;
//		System.out.println("addQuestioonController");
//		int num =  Integer.parseInt(request.getParameter("num"));
//		System.out.print(num);
//		//int id = Integer.parseInt(request.getParameter("id"));
//		for (int i=1;i<=num;i++) {
//		int type = Integer.parseInt(request.getParameter(i+"type"));
//		String title = request.getParameter(i+"title");
//		String content = request.getParameter(i+"content");
//		String answer = request.getParameter(i+"answer");
//		int mark = Integer.parseInt(request.getParameter(i+"mark"));
//		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
//		
//		Boolean flag = questionService.createNewQuestion(type, title, content, answer, mark, exam_id);
//		System.out.println("add question doPost success: "+flag);
//		success = flag;
//		}
//		if (success) {
//			response.sendRedirect("./questions");
//		}else {
//			HttpSession session = request.getSession(); 
//			session.setAttribute("errMessageQuestion", "Something went wrong. The question is already exist");
//			response.sendRedirect("./questions");
//		}
		
		System.out.println("addQuestioonController");
		//int id = Integer.parseInt(request.getParameter("id"));
		int type = Integer.parseInt(request.getParameter("type"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		int mark = Integer.parseInt(request.getParameter("mark"));
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		
		HttpSession httpSession = request.getSession(true);
		
		Boolean success = questionService.createNewQuestion(type, title, content, answer, mark, exam_id);
		System.out.println("add question doPost success: "+success);
		if (success) {
			LockManager lockManager=new LockManager();
			lockManager.releaseLock(Integer.parseInt(request.getParameter("exam_id")), httpSession.getId());
			response.sendRedirect("./questions");
		}else {
//			HttpSession session = request.getSession(); 
//			session.setAttribute("errMessageQuestion", "Something went wrong. The question is already exist");
			LockManager lockManager=new LockManager();
			lockManager.releaseLock(Integer.parseInt(request.getParameter("exam_id")), httpSession.getId());
			response.sendRedirect("./questions");
		}
	}

}
