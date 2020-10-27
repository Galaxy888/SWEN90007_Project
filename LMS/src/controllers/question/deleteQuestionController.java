package controllers.question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Question;
import service.QuestionService;

/**
 * Servlet implementation class deleteQuestionController
 */
//@WebServlet("/deleteQuestion")
public class deleteQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionService questionService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteQuestionController() {
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
		System.out.println("deleteQuestioonController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Boolean success = questionService.deleteQuestion(id);
		System.out.println("delete question doPost success: "+success);
		
		if (success) {
			response.sendRedirect("./questions");
		}else {
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageQuestion", "Something went wrong. The question cannot be deleted");
			response.sendRedirect("./questions");
		}
	}

}
