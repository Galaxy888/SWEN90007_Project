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

import datasource.DBConnection;
import domain.Mark;
import domain.Subject;
import service.ExamService;
import service.MarkService;

/**
 * Servlet implementation class ViewExamResultController
 */
//@WebServlet("/ViewExamResultController")
public class ViewExamResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamService examService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewExamResultController() {
        super();
        examService = new ExamService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("View Exam results");
		int exam_id = Integer.parseInt((String) request.getAttribute("exam_id"));
		
//		List<Mark> marks = new ArrayList<>();
//		marks = new Mark().getAllMark(exam_id);
//		request.setAttribute("marks",marks);
//		request.getRequestDispatcher("./exam_result.jsp").forward(request, response);
		
		List<Mark> marks = examService.getAllMarks(exam_id);
		System.out.println("View Exam Result doPost success: "+marks);
		if (marks!=null) {
			request.setAttribute("marks",marks);
			request.getRequestDispatcher("./exam_result.jsp").forward(request, response);
		}else {
//			HttpSession session = request.getSession(); 
//			session.setAttribute("errMessageQuestion", "something went wrong. update result error");
//			response.sendRedirect("./ViewMark");
//			request.getRequestDispatcher("./exam_result.jsp").forward(request, response);
			response.sendRedirect("/dashboard");
		}
	}
}
