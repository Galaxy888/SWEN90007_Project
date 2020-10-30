package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ExamService;
import service.UserService;

/**
 * Servlet implementation class addUserController
 */
//@WebServlet("/addUserController")
public class addUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addUserController() {
        super();
        userService = new UserService();
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
		System.out.println("addUser doPost");
		//int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		int type = Integer.parseInt(request.getParameter("type"));
		
		Boolean success = userService.createNewUser(name, password,type);
		System.out.println("add user doPost success: "+success);
		if (success) {
			response.sendRedirect("./dashboard");
		}else {
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageExam", "something went wrong. The exam is already exist");
			response.sendRedirect("./dashboard");

		}
	}

}
