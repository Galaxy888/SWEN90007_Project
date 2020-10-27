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
		String sql = "select * from users_exams where exam_id='"+id+"'";
		int flag = 0;
		try {
			PreparedStatement stmt = DBConnection.prepare(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
			   int status = Integer.parseInt(rs.getString(4));
			   System.out.print("the exam status:"+status);
			   if (status==0) {
				   flag =1;
			   }
			}
			} catch (SQLException e) {
				
			}
		System.out.print("the exam flag:"+flag);
		Boolean success = true;
		if (flag==1){
			success = false;
		    
		}else {
			String deleteExamStatement = "delete from users_exams where exam_id=?";
			String deleteUserQuestionStatement = "delete from users_questions where exam_id=?";
			String deleteQuestionStatement = "delete from questions where exam_id=?";
			try {
				PreparedStatement stmt = DBConnection.prepare(deleteExamStatement);
				PreparedStatement stm = DBConnection.prepare(deleteUserQuestionStatement);
				PreparedStatement st = DBConnection.prepare(deleteQuestionStatement);
				stmt.setInt(1, id);
				stm.setInt(1, id);
				st.setInt(1, id);
				System.out.println("Delete Users_exams table");
				stmt.executeUpdate();
				stmt.close();
				System.out.println("Delete Users_questions table");
				stm.executeUpdate();
				stm.close();
				System.out.println("Delete Questions table");
				st.executeUpdate();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
			
			  success = examService.deleteExam(id);
			  System.out.println("delete exam doPost success: "+success);
		}
		if (success) {
			response.sendRedirect("./exams");
		}else {
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageExam", "The exam cannot be deleted. The exam has been published / Some students have submitted the exxam.");
			response.sendRedirect("./exams");
		}
	}

}
