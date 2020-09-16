package auth;

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

import datasource.DBConnection;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
        response.setContentType("text/html");
		System.out.println("Hello from GET method in LoginServlet");
		String user = request.getParameter("userName");
		String pass = request.getParameter("passWord");
		
		
		PrintWriter writer = response.getWriter();
		writer.println("<h3> Hello from Get "+user+  "   " +pass+ "</h3>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("text/html");
		
		
		String user = request.getParameter("userName");
		String pass = request.getParameter("passWord");
		
		String correctUser = null;
		PrintWriter writer = response.getWriter();
		String stm = ("Select * FROM users WHERE name = '" + user + "' AND password = '" + pass + "';" );
//		writer.println("<h3> Hello from Post: Your user name is: "+user+", Your password is: " +pass+ "</h3>");
		try {
        	PreparedStatement stmt = DBConnection.prepare(stm);

        	ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				correctUser = rs.getString(2);
				
				
			}
	
		} catch (SQLException e) {
	
		}
		
		if(correctUser != null) {
			System.out.println("test");
			response.sendRedirect("success.jsp");
		}
		else {
			System.out.println("test2");
			writer.println("<h3> Error </h3>");
		}
	}

}
