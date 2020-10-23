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
import domain.User;
import mapper.SubjectMapper;

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
						String sql = "select * from users where id='"+id+"' limit 1"; 
		                PreparedStatement sqlt = DBConnection.prepare(sql);
		                 ResultSet rs1 = sqlt.executeQuery();
		                	if(rs1.next()){
		                		request.setAttribute("name"+id, rs1.getString(2));
		                		}
						Subject subject = new Subject(code, name, id);
						subjects.add(subject);
						System.out.print("result:"+subject+"11111");
			          } 	
					} catch (Exception exp) {
						System.out.println(exp);}
					request.setAttribute("subjects", subjects);
					request.setAttribute("user_type", userType);
			// TODO student and admin
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
						String sql = "select * from users where id='"+id+"' limit 1"; 
		                PreparedStatement sqlt = DBConnection.prepare(sql);
		                 ResultSet rs1 = sqlt.executeQuery();
		                	if(rs1.next()){
		                		request.setAttribute("name"+id, rs1.getString(2));
		                		}
						Subject subject = new Subject(code, name, id);
						subjects.add(subject);
						System.out.print("result:"+subject+"11111");
			          } 	
					} catch (Exception exp) {
						System.out.println(exp);}
					request.setAttribute("subjects", subjects);
					request.setAttribute("user_type", userType);
			// TODO student and admin
			        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		
				} else if (userType.equals("Admin")) {
//					Subject subject = new Subject();
					List<Subject> subjects = new ArrayList<>();
					List<User> users = new ArrayList<>();
					users  = new User().getUser();
					subjects = SubjectMapper.getAllAdminSubjects();
					for (Subject subject2:subjects) {
						int id = subject2.getCoordinator();
						String stm = "select * from users where id='"+id+"' limit 1"; 
		                PreparedStatement stmt = DBConnection.prepare(stm);
		                 ResultSet rs = stmt.executeQuery();
		                	if(rs.next()){
		                		request.setAttribute("name"+subject2.getCoordinator(), rs.getString(2));
		                		}
					}
					request.setAttribute("subjects", subjects);
					request.setAttribute("user_type", userType);
					request.setAttribute("users", users);
					request.getRequestDispatcher("dashboard.jsp").forward(request, response);
					
				}else {
					response.sendRedirect("/login.jsp");

				}
				
			
			
			
			
			
			
			
				} catch (Exception exp) {
					response.sendRedirect("/login.jsp");
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
