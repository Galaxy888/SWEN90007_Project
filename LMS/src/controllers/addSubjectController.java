package controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
 * Servlet implementation class addSubjectController
 */
@WebServlet("/subject")
public class addSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addSubjectController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String code = request.getParameter("code");
//		String name = request.getParameter("name");
//		Subject subject = new Subject(code, name);
//		subject.insert();
//		
//		response.sendRedirect("/dashboard");
		
		String code = request.getParameter("code");
		  String name = request.getParameter("name");
		  boolean success = false;
		  boolean flag = true;
		  String sql = "select * from subjects";
		  try {
		   PreparedStatement stmt = DBConnection.prepare(sql);
		   ResultSet rs = stmt.executeQuery();
		   while (rs.next()) {
		    String subject_code = rs.getString(1);
		    if(code.equals(subject_code)) {
		     flag = false;
		    }
		   }
		   
		  }catch (SQLException e) {
		   System.out.println(e);
		  }
		  
		  if (flag) {
		  Subject subject = new Subject(code, name);
		  subject.insert();
		  response.sendRedirect("./dashboard");	
		  }else {
			   HttpSession session = request.getSession(); 
			   session.setAttribute("errMessageExam", "The subject code is already exist");
			   response.sendRedirect("./dashboard");
		   
		  }
		  
		 

	}

}
