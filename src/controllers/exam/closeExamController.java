package controllers.exam;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.DBConnection;

/**
 * Servlet implementation class CloseExamController
 */
//@WebServlet("/CloseExamController")
public class closeExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public closeExamController() {
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
		System.out.println("closeExamController");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String sql = "select * from users_exams where exam_id='"+id+"'";
		int flag = 0;
		try {
			PreparedStatement stmt = DBConnection.prepare(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
			  flag = 1;
			}
			} catch (SQLException e) {
				
			}
		System.out.print("the exam flag:"+flag);
		Boolean success = true;
		if (flag==1){
			success = false;
		    
		}else {
			
			String sql2 = "update exams set status=2 where id=?";
			try {
				PreparedStatement stmt = DBConnection.prepare(sql2);
				stmt.setInt(1, id);
				stmt.executeUpdate();
				success = true;
			} catch (SQLException e) {
				    success = false;
					e.printStackTrace();
					}
			
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
