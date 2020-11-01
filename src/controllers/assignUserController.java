package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.SubjectService;
import service.UserSubjectService;
/**
 * Servlet implementation class assignUser
 */
//@WebServlet("/assignUser")
public class assignUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserSubjectService userSubjectService;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public assignUserController() {
        super();
        userSubjectService = new UserSubjectService();
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
		System.out.println("addUserSubject doPost");
		String code = request.getParameter("code");
		int id = Integer.parseInt(request.getParameter("id"));
	
		Boolean success = userSubjectService.createNewUserSubject(id, code);
		System.out.println("add user doPost success: "+success);
		if (success) {
			response.sendRedirect("./dashboard");
		}else {
			System.out.println("11111111");
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageExam", "something went wrong.");
			response.sendRedirect("./dashboard");

		}
		
	}

}
