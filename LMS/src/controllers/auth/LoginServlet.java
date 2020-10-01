package controllers.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataMapper.UserMapper;
import datasource.DBConnection;
<<<<<<< HEAD
import domain.User;
=======
>>>>>>> master

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
//        response.setContentType("text/html");
//		System.out.println("Hello from GET method in LoginServlet");
//		String user = request.getParameter("userName");
//		String pass = request.getParameter("passWord");
//		
//		
//		PrintWriter writer = response.getWriter();
//		writer.println("<h3> Hello from Get "+user+  "   " +pass+ "</h3>");
//		doPost(request, response);
		response.sendRedirect("/LMS/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("text/html");

		String userName = request.getParameter("userName");
		String password = request.getParameter("passWord");
//		LoginUser loginuser = new LoginUser(userName,password);

		String correctUser = null;
		PrintWriter writer = response.getWriter();

		UserMapper userMapper = new UserMapper();
<<<<<<< HEAD
		User userValidate = userMapper.authenticateUser(userName, password);

		if (userValidate.getType()==0) {
=======
		String userValidate = userMapper.authenticateUser(userName, password);

		if (userValidate.equals("Admin")) {
>>>>>>> master
			System.out.println("Admin's Dashboard");

			HttpSession session = request.getSession(); // Creating a session
			session.setAttribute("userName", userName); // setting session attribute
			session.setAttribute("userType", "Admin");
<<<<<<< HEAD
			session.setAttribute("user_id",userValidate.getId());
=======
>>>>>>> master
//            request.setAttribute("userName", userName);
//            request.setAttribute("userType", "Admin");

			response.sendRedirect("/LMS/dashboard");
//            request.getRequestDispatcher("/dashboard").forward(request, response);

<<<<<<< HEAD
		} else if (userValidate.getType()==1) {
=======
		} else if (userValidate.equals("Instructor")) {
>>>>>>> master
			System.out.println("Instructor's Dashboard");

			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("userType", "Instructor");
<<<<<<< HEAD
			session.setAttribute("user_id",userValidate.getId());
=======
>>>>>>> master
//            request.setAttribute("userName", userName);
//            request.setAttribute("userType", "Instructor");

			response.sendRedirect("/LMS/dashboard");
//            request.getRequestDispatcher("/dashboard").forward(request, response);
<<<<<<< HEAD
		} else if (userValidate.getType()==2) {
=======
		} else if (userValidate.equals("Student")) {
>>>>>>> master
			System.out.println("Student's Dashboard");

			HttpSession session = request.getSession();
//            session.setMaxInactiveInterval(10*60);
			session.setAttribute("userName", userName);
			session.setAttribute("userType", "Student");
<<<<<<< HEAD
			session.setAttribute("user_id",userValidate.getId());
=======
>>>>>>> master
//            request.setAttribute("userName", userName);
//            request.setAttribute("userType", "Student");

			response.sendRedirect("/LMS/dashboard");
//            request.getRequestDispatcher("/dashboard").forward(request, response);
		} else {
			System.out.println("Error message = " + userValidate);
//            request.setAttribute("errMessage", userValidate);
			HttpSession session = request.getSession();
			session.setAttribute("errMessage", userValidate);
			response.sendRedirect("/LMS/login.jsp");

//            request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

//		String stm = ("Select * FROM users WHERE name = '" + userName + "' AND password = '" + password + "';" );
////		writer.println("<h3> Hello from Post: Your user name is: "+user+", Your password is: " +pass+ "</h3>");
//		try {
//        	PreparedStatement loginStatement = DBConnection.prepare(stm);
//
//        	ResultSet rs = loginStatement.executeQuery();
//			if (rs.next()) {
//				correctUser = rs.getString(2);
//				
//				
//			}
//	
//		} catch (SQLException e) {
//	
//		}
//		
//		if(correctUser != null) {
//			//login success
//			System.out.println("login success");
//			
//            //create session object
//            HttpSession session = request.getSession();
//            //store user data into the session object
//            session.setAttribute("userName", userName);
//            //go to dashboard.jsp
////            response.sendRedirect(request.getContextPath()+"/IndexServlet");
////			response.sendRedirect("success.jsp");
//            request.setAttribute("userName", userName);
//			request.getRequestDispatcher("success.jsp").forward(request, response);
//		}
//		else {
//			//login fail
//			System.out.println("login fail");
//			//response.sendRedirect(request.getContextPath() + "/fail.html");
//            request.setAttribute("errMessage", "Invalid username or password ");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//		}
	}

}
