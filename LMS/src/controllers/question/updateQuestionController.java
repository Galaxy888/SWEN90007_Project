package controllers.question;

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

import domain.Question;
import pessimistic.LockManager;
import service.QuestionService;

/**
 * Servlet implementation class updateQuestionController
 */
//@WebServlet("/updateQuestion")
public class updateQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateQuestionController() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("updateQuestionController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Boolean success = false;
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.print(num);
		// int id = Integer.parseInt(request.getParameter("id"));
		HttpSession httpSession = request.getSession(true);
		for (int i = 1; i <= num; i++) {

			int flag = Integer.parseInt(request.getParameter(i + "flag"));
			if (flag == 0) {
				int id = Integer.parseInt(request.getParameter(i + "id"));
				int type = Integer.parseInt(request.getParameter(i + "type"));
				String title = request.getParameter(i + "title");
				String content = request.getParameter(i + "content");
				String answer = request.getParameter(i + "answer");
				int mark = Integer.parseInt(request.getParameter(i + "mark"));
				int exam_id = Integer.parseInt(request.getParameter("exam_id"));
//				int version = Integer.parseInt(request.getParameter(i + "version"));

				Boolean s = questionService.updateQuestion(id, type, title, content, answer, mark, exam_id);
				success = s;
				System.out.println("update question doPost success: " + s);
			} else if (flag == 1) {
				int id = Integer.parseInt(request.getParameter(i + "id"));
				Boolean sc = questionService.deleteQuestion(id);
				System.out.println("delete question doPost success: " + sc);
			}
		}
		if (success) {
			LockManager lockManager=new LockManager();
			lockManager.releaseLock(Integer.parseInt(request.getParameter("exam_id")), httpSession.getId());
			response.sendRedirect("./questions");
		} else {
			HttpSession session = request.getSession();
			LockManager lockManager=new LockManager();
			lockManager.releaseLock(Integer.parseInt(request.getParameter("exam_id")), httpSession.getId());
//			session.setAttribute("errMessageQuestion",
//					"Someone has already updated the question. Please view the latsest version");
//			session.setAttribute("errMessageQuestion",
//					"Someone is updaing the exam questions. Please try again later");
			response.sendRedirect("./questions");
		}
	}

}
