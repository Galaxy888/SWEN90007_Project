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

		System.out.println("deleteExamController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String sql = "select * from users_exams where exam_id='"+id+"' and status = 0";
		try {
			PreparedStatement stmt = DBConnection.prepare(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
			   int user_id = Integer.parseInt(rs.getString(1));
			   String sql3 = "update users_exams set status = 3 where user_id=? and exam_id = ?";
			   PreparedStatement stmt3 = DBConnection.prepare(sql3);
               try {
            	   	stmt3.setInt(1, user_id);
					stmt3.setInt(2, id);
                    stmt3.executeUpdate();
	} catch (SQLException e) {
//					// TODO Auto-generated catch block
				e.printStackTrace();
				}
			   
			}
			} catch (SQLException e) {
				
			}
			response.sendRedirect("./exams");
	
	}

}
