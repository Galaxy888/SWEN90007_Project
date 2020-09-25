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
import domain.Exam;
import domain.Subject;

/**
 * Servlet implementation class ExamController
 */
//@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String subject_code = request.getParameter("subject_code");
		String stm = "select * from exams where subject_code='"+subject_code+"'";
	    System.out.print("test2");
	    List<Exam> exams = new ArrayList<>();
	    try {
	    PreparedStatement stmt = DBConnection.prepare(stm);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) { 
	        		int id = Integer.parseInt(rs.getString(1));
	        		String title = rs.getString(2);
	        		int status = Integer.parseInt(rs.getString(3));
	        		String code = rs.getString(4);
	        	    Exam exam = new Exam(id,title,status,code);
	        	    exams.add(exam);
	     }
	    }catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	    request.setAttribute("exams", exams);
	    request.getRequestDispatcher("./exams.jsp").forward(request, response);
		//response.sendRedirect("exams.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
