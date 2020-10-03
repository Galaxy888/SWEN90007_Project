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

import datasource.DBConnection;
import domain.User;
import mapper.UserMapper;

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
		response.sendRedirect("/login.jsp");
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
		User userValidate = userMapper.authenticateUser(userName, password);
        System.out.print(userValidate);
		if (userValidate.getType()==1) {
			System.out.println("Admin's Dashboard");

			HttpSession session = request.getSession(); // Creating a session
			session.setAttribute("userName", userName); // setting session attribute
			session.setAttribute("userType", "Admin");
			session.setAttribute("user_id",userValidate.getId());
//            request.setAttribute("userName", userName);
//            request.setAttribute("userType", "Admin");

			response.sendRedirect("./dashboard");
//            request.getRequestDispatcher("/dashboard").forward(request, response);

		} else if (userValidate.getType()==2) {
			System.out.println("Instructor's Dashboard");

			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("userType", "Instructor");
			session.setAttribute("user_id",userValidate.getId());
//            request.setAttribute("userName", userName);
//            request.setAttribute("userType", "Instructor");

			response.sendRedirect("./dashboard");
//            request.getRequestDispatcher("/dashboard").forward(request, response);
		} else if (userValidate.getType()==3) {
			System.out.println("Student's Dashboard");

			HttpSession session = request.getSession();
//            session.setMaxInactiveInterval(10*60);
			session.setAttribute("userName", userName);
			session.setAttribute("userType", "Student");
			session.setAttribute("user_id",userValidate.getId());
//            request.setAttribute("userName", userName);
//            request.setAttribute("userType", "Student");

			response.sendRedirect("./dashboard");
//            request.getRequestDispatcher("/dashboard").forward(request, response);
		} else {
			System.out.println("Error message = " + userValidate);
//            request.setAttribute("errMessage", userValidate);
			HttpSession session = request.getSession();
			session.setAttribute("errMessage", "Invalid username or password");
			response.sendRedirect("./login.jsp");

//            request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
       }
	}


