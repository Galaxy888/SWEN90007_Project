package controllers.exam;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Mark;
import service.MarkService;

/**
 * Servlet implementation class updateResultController
 */
//@WebServlet("/updateResultController")
public class updateResultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MarkService markService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateResultController() {
        super();
        markService = new MarkService();
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
		int id = Integer.parseInt(request.getParameter("user_id"));
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		int mark = Integer.parseInt(request.getParameter("mark"));
		int status = Integer.parseInt(request.getParameter("status"));
		int version = Integer.parseInt(request.getParameter("version"));
		System.out.println("VVVVVVV: "+version);
		
		Boolean success = markService.updateResult(id, exam_id, mark, status,version);
		System.out.println("update Result doPost success: "+success);
		if (success) {
			response.sendRedirect("./ViewMark");
		}else {
			HttpSession session = request.getSession(); 
			session.setAttribute("errMessageResult", "Someone has already updated the result. Please view the latsest version");
			response.sendRedirect("./ViewMark");
		}
		
//		Mark result = new Mark();
//		try {
//			result.updateResult(id, exam_id, mark, status);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		response.sendRedirect("./questions");
	}
	

}
