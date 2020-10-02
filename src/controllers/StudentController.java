package controllers;

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
import domain.User;

/**
 * Servlet implementation class addStudentController
 */
//@WebServlet("/addStudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentController() {
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
		String code =(String)request.getAttribute("subject_code");
		System.out.print(code);
		String sql = "select * from users_subjects where subject_code ='"+code+"'";
		List<User> users = new ArrayList<>();
		try {
		PreparedStatement stmt = DBConnection.prepare(sql);
    	ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int id = Integer.parseInt(rs.getString(1));
			System.out.print(id);
			User user=new User().getUser(id);
			System.out.print(user);
			users.add(user);
		}
		
		}catch (SQLException e) {
			
		}
		
		request.setAttribute("users",users);
		//request.setAttribute("questions", questions);
		request.getRequestDispatcher("./student_list.jsp").forward(request, response);
		
		
		
	}

}
