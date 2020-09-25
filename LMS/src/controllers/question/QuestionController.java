package controllers.question;

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
import domain.Question;

/**
 * Servlet implementation class QuestionController
 */
@WebServlet("/questions")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		List<Question> questions = new ArrayList<>();
		String stm = "select * from questions where exam_id='"+exam_id+"'";
     	 try {
     	  PreparedStatement stmt = DBConnection.prepare(stm);
     	  ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id2 = Integer.parseInt(rs.getString(1));
				int type2 = Integer.parseInt(rs.getString(2));
				String title2 = rs.getString(3);
				String content2 = rs.getString(4);
				String answer2 = rs.getString(5);
				int mark2 = Integer.parseInt(rs.getString(6));
				int examId2 = Integer.parseInt(rs.getString(7));
				questions.add(new Question(id2,type2,title2,content2,answer2,mark2,examId2));
			}
     	  } catch (SQLException e) {

     		System.out.println(e.getMessage());
     	}
	
		  request.setAttribute("questions", questions);
	      request.getRequestDispatcher("./questions.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
