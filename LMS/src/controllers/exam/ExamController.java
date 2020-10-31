package controllers.exam;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.DBConnection;
import domain.Exam;
import domain.Subject;
import service.SubjectService;

/**
 * Servlet implementation class ExamController
 */
//@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectService subjectService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExamController() {
		super();
		subjectService = new SubjectService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String pathInfo = request.getPathInfo(); // /{subject_code}/exams
		String[] pathParts = pathInfo.split("/");
		String subjectCode = ""; // {subject}
		String user_type = "";
		String route = ""; // exams
		
		if(request.getSession(false)==null) {
			response.sendRedirect("/login.jsp");
		}
		
		else {

		System.out.println("Type: " + request.getSession(false).getAttribute("userType"));

		System.out.println(
				"ExamController_0: " + pathInfo + " lenght: " + pathParts.length + ", pathParts[0]:" + pathParts[0]);

		String userType = (String) request.getSession(false).getAttribute("userType");
		int user_id = (int) request.getSession(false).getAttribute("user_id");
		if (userType.equals("Instructor")) {
			if (pathParts.length == 3) {
				subjectCode = pathParts[1];
				String operation = pathParts[2]; // add/update/delete
				System.out.print(operation);
				request.setAttribute("subject_code", subjectCode);
				if (operation.equals("exams")) {

					List<Exam> exams = subjectService.getAllExams(subjectCode);
					System.out.println("ExamController : getAllExams" + exams);
					if (exams != null) {
						request.setAttribute("exams", exams);
						request.getRequestDispatcher("/exams.jsp").forward(request, response);
					} else {
						HttpSession session = request.getSession();
						session.setAttribute("errMessageExam", "Something went wrong.");
						response.sendRedirect("./exams");
					}
				}

				else if (operation.equals("addExam")) {
					request.getRequestDispatcher("/addExam").forward(request, response);
				} else if (operation.equals("updateEditExam")) {
					request.getRequestDispatcher("/updateEditExam").forward(request, response);
				}else if (operation.equals("updateExam")) {
					System.out.println("VVVVVVVVVVVV");
					System.out.println(request.getParameter("title"));
					System.out.println(request.getParameter("version"));
					request.getRequestDispatcher("/updateExam").forward(request, response);
				} else if (operation.equals("deleteExam")) {
					request.getRequestDispatcher("/deleteExam").forward(request, response);
				} else if (operation.equals("closeExam")) {
					request.getRequestDispatcher("/closeExam").forward(request, response);
				} 
			} else if (pathParts.length == 5) {
				subjectCode = pathParts[1];
				if (pathParts[2].equals("exams") &&(!pathParts[4].equals("markExamDone"))) {
					String subject_code = pathParts[1];
					String exam_id = pathParts[3];
					String operation = pathParts[4];
					request.setAttribute("exam_id", exam_id);
					request.setAttribute("operation", operation);
					request.setAttribute("subject_code", subject_code);
					request.getRequestDispatcher("/question").forward(request, response);
				} else if (pathParts[4].equals("ViewAnswer")) {
					String exam_id = pathParts[3];
					String operation = pathParts[4];
					request.setAttribute("exam_id", exam_id);
					request.setAttribute("operation", operation);
					request.getRequestDispatcher("/ViewAnswer").forward(request, response);
				} else if (pathParts[4].equals("markExamDone")){
					//TODO
//					HttpSession session = request.getSession(); 
//					session.setAttribute("errMessageStudentTakeExam", "You have already submit the exam");
					response.sendRedirect("/courses/"+subjectCode+"/exams");
					
				}else {
					response.sendRedirect("/dashboard");
				}
			}
		}

		else if (userType.equals("Student")) {
			if (pathParts.length == 3) {
				subjectCode = pathParts[1];
				String operation = pathParts[2]; // add/update/delete
				System.out.print(operation);
				request.setAttribute("subject_code", subjectCode);

				if (operation.equals("exams")) {
               
					String stm = "select * from exams where subject_code='" + subjectCode + "' and status=1";
					List<Exam> exams = new ArrayList<>();
					try {
						PreparedStatement stmt = DBConnection.prepare(stm);
						ResultSet rs = stmt.executeQuery();
						while (rs.next()) {
							int id = Integer.parseInt(rs.getString(1));
							int mark = 0;
							int flag = 1;
							int close_flag = 0;
							String sql = "select * from users_exams where status=1 and user_id ='" + user_id + "' and exam_id='" + id
									+ "' limit 1";
							PreparedStatement search = DBConnection.prepare(sql);
							ResultSet rs1 = search.executeQuery();
							while (rs1.next()) {
								flag = 0;
								mark = Integer.parseInt(rs1.getString(3));
							}
							System.out.print(mark);
							request.setAttribute("mark" + id, mark);
							request.setAttribute("flag" + id, flag);
							System.out.print("flag is:"+flag);
							String title = rs.getString(2);
							int status = Integer.parseInt(rs.getString(3));
							if (status==2) {
								close_flag = 1;
							}
							request.setAttribute("close_flag" + id, close_flag);
							String code = rs.getString(4);
							//int version = Integer.parseInt(rs.getString(5));
							
							// TODO
//							Exam exam = new Exam(id, title, status, code, version);
							Exam exam = new Exam();
							exam.setId(id);
							exam.setTitle(title);
							exam.setStatus(status);
							exam.setSubject(code);
							exams.add(exam);
						}
					} catch (SQLException e) {

						System.out.println(e.getMessage());
					}
					
					Collections.sort(exams);
					request.setAttribute("exams", exams);
					request.setAttribute("subjectCode", subjectCode);
					request.getRequestDispatcher("/exams_student.jsp").forward(request, response);

//					List<Exam> exams = subjectService.getAllStudentExams(subjectCode);
//					Map<Integer, List<Exam> > result = subjectService.getAllStudentExams(subjectCode);
//					System.out.println("ExamController : getAllStudentExams"+ exams);
//					if (exams!=null) {
//						request.setAttribute("exams", exams);
//						request.getRequestDispatcher("/exams_student.jsp").forward(request, response);
//					}else {
//						HttpSession session = request.getSession(); 
//						session.setAttribute("errMessageExam", "something went wrong. The exam is already exist");
//						response.sendRedirect("./exams");
//					}
				} else if (operation.equals("takeQuestion")) {
					request.getRequestDispatcher("/question").forward(request, response);
				}
			} else if (pathParts.length == 5) {
				subjectCode = pathParts[1];
				if (pathParts[2].equals("exams")) {
					String exam_id = pathParts[3];
					String operation = pathParts[4];
					request.setAttribute("exam_id", exam_id);
					request.setAttribute("operation", operation);
					request.setAttribute("subject_code", subjectCode);
					request.getRequestDispatcher("/question").forward(request, response);
				} else {
					response.sendRedirect("/dashboard");
				}
			}else if(pathParts.length == 6) {
				if(pathParts[5].equals("done")) {
					HttpSession session = request.getSession(); 
					session.setAttribute("errMessageStudentTakeExam", "You have already submitted the exam");
					response.sendRedirect("/courses/"+pathParts[1]+"/exams");
				}else {
					response.sendRedirect("/dashboard");
				}
			}

		}

		else if (userType.equals("Admin")) {
			if (pathParts.length == 3) {
				subjectCode = pathParts[1];
				String operation = pathParts[2]; // add/update/delete
				System.out.print(operation);
				request.setAttribute("subject_code", subjectCode);
				if (operation.equals("instructor")) {
					request.getRequestDispatcher("/addInstructor").forward(request, response);
				} else if (operation.equals("student")) {
					request.setAttribute("subject_code", subjectCode);
					request.getRequestDispatcher("/Student").forward(request, response);
				} else if (operation.equals("addSubject")) {
					request.getRequestDispatcher("/addSubject").forward(request, response);
				}

			}

		}

		else {
			response.sendRedirect("/login.jsp");
		}
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
