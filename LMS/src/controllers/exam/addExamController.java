package controllers.exam;

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

import dataMapper.ExamMapper;
import datasource.DBConnection;
import domain.Exam;
import domain.Subject;
import service.ExamService;

/**
 * Servlet implementation class addExamController
 */
@WebServlet("/addExamController")
public class addExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamService examService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addExamController() {
		super();
		// TODO Auto-generated constructor stub
		examService = new ExamService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 String view = "/exams.jsp";
//	     ServletContext servletContext = getServletContext();
//	     RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
//	     requestDispatcher.forward(request, response);
//		doPost(request, response);
//		HttpSession session = request.getSession(); 
//		session.setAttribute("errMessageExam", null);
		response.sendRedirect("./exams");
		
		
//		System.out.println("add exam error: "+ request.getAttribute("errMessage"));
//		request.getRequestDispatcher(request.getPathInfo()).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("add exam doPost");
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		int status = Integer.parseInt(request.getParameter("status"));
		String subject_code = request.getParameter("subject_code");

//		Exam exam = new Exam(id, title, status, subject_code);
		
		Boolean success = examService.createNewExam(id, title, status, subject_code);
		System.out.println("add exam doPost success: "+success);
		if (success) {
			response.sendRedirect("./exams");
		}else {
			//TODO
//			request.setAttribute("errMessageExam", "something went wrong. The exam is already exist");
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageExam", "something went wrong. The exam is already exist");
			response.sendRedirect("./exams");
//			doGet(request,response);
//			response.sendRedirect("./exams");
		}
		
		
//		ExamMapper examMapper = new ExamMapper();
//		examMapper.insert(exam);
//		exam.insert();

//		String stm = "select * from exams where subject_code='" + subject_code + "'";
//		System.out.println("title:" + title);
//		List<Exam> exams = new ArrayList<>();
//		try {
//			PreparedStatement stmt = DBConnection.prepare(stm);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				int id2 = Integer.parseInt(rs.getString(1));
//				String title2 = rs.getString(2);
//				int status2 = Integer.parseInt(rs.getString(3));
//				String code = rs.getString(4);
//				Exam exam2 = new Exam(id2, title2, status2, code);
//				exams.add(exam2);
//			}
//		} catch (SQLException e) {
//
//			System.out.println(e.getMessage());
//		}

//		  request.setAttribute("exams", exams);
//	      request.getRequestDispatcher("./exams").forward(request, response);

//		  request.setAttribute("exams", exams);
//		  request.getRequestDispatcher("/exams.jsp").forward(request, response);
		
		
		
		
		
//		response.sendRedirect("./exams");
		
		
		
		
		
		
		
		
		
//		  response.sendRedirect(request.getHeader("refer"));

//		  response.sendRedirect("/LMS/dashboard");
		// String view = "./exams.jsp";
		// ServletContext servletContext = getServletContext();
		// RequestDispatcher requestDispatcher =
		// servletContext.getRequestDispatcher(view);
		// requestDispatcher.forward(request, response);
	}
}
