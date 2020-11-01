package controllers.exam;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
		String subject_code = (String) request.getAttribute("subject_code");
		int total_mark = 0;
		String sql = "select * from questions where exam_id = '"+exam_id+"'";
		try {
			PreparedStatement stmt = DBConnection.prepare(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
		      int mark = Integer.parseInt(rs.getString(6));
		      total_mark += mark;
			}
			
		}catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		System.out.print("total mark is: 111"+total_mark);
		
		
		List<Mark> marks = examService.getAllMarks(exam_id);
		Collections.sort(marks);
		System.out.println("View Exam Result doPost success: "+marks);
		if (marks!=null) {
			request.setAttribute("marks",marks);
			request.setAttribute("total_mark", total_mark);
			request.setAttribute("subject_code",subject_code);
			request.getRequestDispatcher("./exam_result.jsp").forward(request, response);
		}else {

			response.sendRedirect("/dashboard");
		}
	}
}
