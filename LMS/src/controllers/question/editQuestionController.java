package controllers.question;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pessimistic.LockManager;

/**
 * Servlet implementation class editQuestionController
 */
@WebServlet("/editQuestionController")
public class editQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editQuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("editQuestionController");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String option = request.getParameter("option");
//		System.out.println(request.getParameter("id"));
		HttpSession httpSession = request.getSession(true);
		System.out.println("httpSession.getId() 0: "+httpSession.getId());
		LockManager lockManager=new LockManager();
//		response.sendRedirect("/login.jsp");
		
		if(option.equals("cancel")) {
			lockManager.releaseLock(id,httpSession.getId());
		}
		else {

		if(lockManager.hasLock(id, httpSession.getId())) {
			if(lockManager.lockOwner(id,httpSession.getId())){
				response.setStatus(200);
				lockManager.acquireLock(id, httpSession.getId());
				
			}else {
				System.out.println("Not lock owner");
//				HttpSession session = request.getSession();
				httpSession.setAttribute("errMessageExam", "Someone is updating Exam. Please try again later ");
				response.setStatus(422);
//				response.sendRedirect("/dashboard");
				
//				response.sendRedirect("./exams");
			}
			
		}else {
			response.setStatus(200);
			lockManager.acquireLock(id, httpSession.getId());
		}
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
