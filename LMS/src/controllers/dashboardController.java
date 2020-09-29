package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import domain.Subject;

/**
 * Servlet implementation class dashboardController
 */
//@WebServlet("/dashboard")
public class dashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dashboardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			response.setContentType("text/html");
			PrintWriter pwriter = response.getWriter();
			HttpSession session = request.getSession(false);
			String userName = (String) session.getAttribute("userName");
			String userType = (String) session.getAttribute("userType");

			if (userType.equals("Instructor")) {
				int user_id=(int)session.getAttribute("user_id");
				Subject subject = new Subject();
				List<Subject> subjects = new ArrayList<>();
				subjects = subject.getAllSubjectsById(user_id);
				
				request.setAttribute("subjects", subjects);
				request.setAttribute("user_type", userType);
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			}
			else if (userType.equals("Student")) {
				int user_id=(int)session.getAttribute("user_id");
				String stm = "SELECT * FROM subjects as subject LEFT JOIN users_subjects as us on subject.code=us.subject_code "
						+ "LEFT JOIN users as u on us.user_id=u.id WHERE u.id= '" + user_id + "'";
				List<Subject> subjects= new ArrayList<>();
				try {
					PreparedStatement stmt = DBConnection.prepare(stm);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						String code = rs.getString(1); 
						String name = rs.getString(2);
						int id = Integer.parseInt(rs.getString(3));
						Subject subject = new Subject(code, name, id);
						subjects.add(subject);
			      } 	} catch (Exception exp) {
						System.out.println(exp);}
					request.setAttribute("subjects", subjects);
					request.setAttribute("user_type", userType);
			// TODO student and admin
			        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		
				}
				
				} catch (Exception exp) {
			System.out.println(exp);}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
