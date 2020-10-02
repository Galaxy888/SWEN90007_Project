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

import datasource.DBConnection;
import domain.Mark;
import domain.Subject;

/**
 * Servlet implementation class ViewExamResultController
 */
//@WebServlet("/ViewExamResultController")
public class ViewExamResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewExamResultController() {
        super();
        // TODO Auto-generated constructor stub
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
		System.out.print("View Exam results");
		int exam_id = Integer.parseInt((String) request.getAttribute("exam_id"));
		List<Mark> marks = new ArrayList<>();
		marks = new Mark().getAllMark(exam_id);
		request.setAttribute("marks",marks);
		request.getRequestDispatcher("./exam_result.jsp").forward(request, response);
	}
}
