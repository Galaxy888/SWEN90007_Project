package controllers.exam;

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
import domain.Exam;
import domain.Subject;

/**
 * Servlet implementation class ExamController
 */
//@WebServlet("/ExamController")
public class ExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//      String subject_code = request.getParameter("subject_code");
//      String stm = "select * from exams where subject_code='"+subject_code+"'";
		String pathInfo = request.getPathInfo(); // /{subject_code}/exams
		String[] pathParts = pathInfo.split("/");
		String subjectCode = ""; // {subject}
		String user_type = "";
		String route = ""; // exams
		
		System.out.println("Type: "+request.getSession(false).getAttribute("userType"));

		System.out.println(
				"ExamController_0: " + pathInfo + " lenght: " + pathParts.length + ", pathParts[0]:" + pathParts[0]);
		
		
		String userType = (String) request.getSession(false).getAttribute("userType");
		int user_id = (int)request.getSession(false).getAttribute("user_id");
		if(userType.equals("Instructor")) {
			if(pathParts.length == 3) {
				subjectCode = pathParts[1];
				String operation = pathParts[2]; // add/update/delete
				System.out.print(operation);
				request.setAttribute("subject_code",subjectCode );
				if (operation.equals("exams")) {	
					String stm = "select * from exams where subject_code='" + subjectCode + "'";
					List<Exam> exams = new ArrayList<>();
					try {
						PreparedStatement stmt = DBConnection.prepare(stm);
						ResultSet rs = stmt.executeQuery();
						while (rs.next()) {
							int id = Integer.parseInt(rs.getString(1));
							String title = rs.getString(2);
							int status = Integer.parseInt(rs.getString(3));
							String code = rs.getString(4);
							Exam exam = new Exam(id, title, status, code);
							exams.add(exam);
						}
					} catch (SQLException e) {

						System.out.println(e.getMessage());
					}
					request.setAttribute("exams", exams);
					request.getRequestDispatcher("/exams.jsp").forward(request, response);
					
//					else if (user_type.equals("Student")) {
//						String stm = "select * from exams where subject_code='" + subjectCode + "'";
//						List<Exam> exams = new ArrayList<>();
//						try {
//							PreparedStatement stmt = DBConnection.prepare(stm);
//							ResultSet rs = stmt.executeQuery();
//							while (rs.next()) {
//								int id = Integer.parseInt(rs.getString(1));
//								String title = rs.getString(2);
//								int status = Integer.parseInt(rs.getString(3));
//								String code = rs.getString(4);
//								Exam exam = new Exam(id, title, status, code);
//								exams.add(exam);
//							}
//						} catch (SQLException e) {
//
//							System.out.println(e.getMessage());
//						}
//						request.setAttribute("exams", exams);
//						request.getRequestDispatcher("/exams_student.jsp").forward(request, response);
//					}

				}

				else if (operation.equals("addExam")) {
					request.getRequestDispatcher("/addExam").forward(request, response);
				} else if (operation.equals("updateExam")) {
					request.getRequestDispatcher("/updateExam").forward(request, response);
				} else if (operation.equals("deleteExam")) {
					request.getRequestDispatcher("/deleteExam").forward(request, response);
				} 
			}
			else if (pathParts.length == 5) {
				subjectCode = pathParts[1];
				if (pathParts[2].equals("exams")) {
					String exam_id = pathParts[3];
					String operation = pathParts[4];
					request.setAttribute("exam_id", exam_id);
					request.setAttribute("operation", operation);
					request.getRequestDispatcher("/question").forward(request, response);
				} else if(pathParts[4].equals("ViewAnswer")) {
					String exam_id = pathParts[3];
					String operation = pathParts[4];
					request.setAttribute("exam_id", exam_id);
					request.setAttribute("operation", operation);
					request.getRequestDispatcher("/ViewAnswer").forward(request, response);
				}
				else {
					response.sendRedirect("/LMS/dashboard");
				}
			}
		}
		else if (userType.equals("Student")) {
			if(pathParts.length == 3) {
				subjectCode = pathParts[1];
				String operation = pathParts[2]; // add/update/delete
				System.out.print(operation);
				request.setAttribute("subject_code",subjectCode );
				if (operation.equals("exams")) {	
					String stm = "select * from exams where subject_code='" + subjectCode + "'";
					List<Exam> exams = new ArrayList<>();
					try {
						PreparedStatement stmt = DBConnection.prepare(stm);
						ResultSet rs = stmt.executeQuery();
						while (rs.next()) {
							int id = Integer.parseInt(rs.getString(1));
							int mark = 0;
							String sql = "select * from users_exams where user_id ='"+user_id+"' and exam_id='"+id+"' limit 1";
							PreparedStatement search = DBConnection.prepare(sql);
							ResultSet rs1 = search.executeQuery();
							while(rs1.next()) {
								mark = Integer.parseInt(rs1.getString(3));
							}
							System.out.print(mark);
							request.setAttribute("mark"+id, mark);
							
							String title = rs.getString(2);
							int status = Integer.parseInt(rs.getString(3));
							String code = rs.getString(4);
							Exam exam = new Exam(id, title, status, code);
							exams.add(exam);
						}
					} catch (SQLException e) {

						System.out.println(e.getMessage());
					}
					request.setAttribute("exams", exams);
					request.getRequestDispatcher("/exams_student.jsp").forward(request, response);
				}
//					else if (user_type.equals("Student")) {
//						String stm = "select * from exams where subject_code='" + subjectCode + "'";
//						List<Exam> exams = new ArrayList<>();
//						try {
//							PreparedStatement stmt = DBConnection.prepare(stm);
//							ResultSet rs = stmt.executeQuery();
//							while (rs.next()) {
//								int id = Integer.parseInt(rs.getString(1));
//								String title = rs.getString(2);
//								int status = Integer.parseInt(rs.getString(3));
//								String code = rs.getString(4);
//								Exam exam = new Exam(id, title, status, code);
//								exams.add(exam);
//							}
//						} catch (SQLException e) {
//
//							System.out.println(e.getMessage());
//						}
//						request.setAttribute("exams", exams);
//						request.getRequestDispatcher("/exams_student.jsp").forward(request, response);
//					}

			

				else if (operation.equals("takeQuestion")) {
					request.getRequestDispatcher("/question").forward(request, response);
				} 
				}
			
			else if (pathParts.length == 5) {
				subjectCode = pathParts[1];
				if (pathParts[2].equals("exams")) {
					String exam_id = pathParts[3];
					String operation = pathParts[4];
					request.setAttribute("exam_id", exam_id);
					request.setAttribute("operation", operation);
					request.getRequestDispatcher("/question").forward(request, response);
				} 
				else {
					response.sendRedirect("/LMS/dashboard");
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}else if (userType.equals("Admin")) {
			if(pathParts.length == 3) {
				subjectCode = pathParts[1];
				String operation = pathParts[2]; // add/update/delete
				System.out.print(operation);
				request.setAttribute("subject_code",subjectCode );
				if (operation.equals("instructor")) {
					request.getRequestDispatcher("/addInstructor").forward(request, response);
				}else if (operation.equals("student")) {
					request.setAttribute("subject_code",subjectCode );
					request.getRequestDispatcher("/Student").forward(request, response);
				}else if (operation.equals("addSubject")) {
					request.getRequestDispatcher("/addSubject").forward(request, response);
				}
				
			}
			
			
			
			
			
		}
		
		
		
		else {
			response.sendRedirect("/LMS/login.jsp");
//		}

		if (pathParts.length <= 1) {
			// TODO need update
//        	response.sendRedirect("/LMS/dashboard");

		} else if (pathParts.length == 2) {
			subjectCode = pathParts[1];
			request.setAttribute("subject_code",subjectCode);
//        	if(pathParts.length==3) {
//        		route = pathParts[2]; // exams
//        	}

		} else if (pathParts.length == 4) {
			subjectCode = pathParts[1];
			user_type = pathParts[3];
			String operation = pathParts[2]; // add/update/delete
			System.out.print(operation);
			request.setAttribute("subject_code",subjectCode );
			
			if (operation.equals("exams")) {
				if (user_type.equals("Instructor")) {
				String stm = "select * from exams where subject_code='" + subjectCode + "'";
				List<Exam> exams = new ArrayList<>();
				try {
					PreparedStatement stmt = DBConnection.prepare(stm);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						int id = Integer.parseInt(rs.getString(1));
						String title = rs.getString(2);
						int status = Integer.parseInt(rs.getString(3));
						String code = rs.getString(4);
						Exam exam = new Exam(id, title, status, code);
						exams.add(exam);
					}
				} catch (SQLException e) {

					System.out.println(e.getMessage());
				}
				request.setAttribute("exams", exams);
				request.getRequestDispatcher("/exams.jsp").forward(request, response);
				}
				else if (user_type.equals("Student")) {
					String stm = "select * from exams where subject_code='" + subjectCode + "'";
					List<Exam> exams = new ArrayList<>();
					try {
						PreparedStatement stmt = DBConnection.prepare(stm);
						ResultSet rs = stmt.executeQuery();
						while (rs.next()) {
							int id = Integer.parseInt(rs.getString(1));
							String title = rs.getString(2);
							int status = Integer.parseInt(rs.getString(3));
							String code = rs.getString(4);
							Exam exam = new Exam(id, title, status, code);
							exams.add(exam);
						}
					} catch (SQLException e) {

						System.out.println(e.getMessage());
					}
					request.setAttribute("exams", exams);
					request.getRequestDispatcher("/exams_student.jsp").forward(request, response);
				}

			}

			else if (operation.equals("addExam")) {
				request.getRequestDispatcher("/addExam").forward(request, response);
			} else if (operation.equals("updateExam")) {
				request.getRequestDispatcher("/updateExam").forward(request, response);
			} else if (operation.equals("deleteExam")) {
				request.getRequestDispatcher("/deleteExam").forward(request, response);
			}

		}
		else if (pathParts.length == 5) {
			subjectCode = pathParts[1];

			if (pathParts[2].equals("deleteExam")) {
				String oprertion = pathParts[2];
				String exam_id = pathParts[3];
				String status = pathParts[4];
				request.setAttribute("exam_id", exam_id);
				request.setAttribute("status", status);
				request.getRequestDispatcher("/deleteExam").forward(request, response);

			} else if (pathParts[2].equals("exams")) {
				String exam_id = pathParts[3];
				String operation = pathParts[4];
				request.setAttribute("exam_id", exam_id);
				request.setAttribute("operation", operation);
				request.getRequestDispatcher("/question").forward(request, response);
			} else {
				response.sendRedirect("/LMS/dashboard");
			}
		}
			
		else if (pathParts.length == 6) {
			subjectCode = pathParts[1];
			request.setAttribute("subject_code",subjectCode );
			if (pathParts[2].equals("deleteExam")) {
				String oprertion = pathParts[2];
				String exam_id = pathParts[3];
				String status = pathParts[4];
				request.setAttribute("exam_id", exam_id);
				request.setAttribute("status", status);
				request.getRequestDispatcher("/deleteExam").forward(request, response);

			} else if (pathParts[2].equals("exams")) {
				String exam_id = pathParts[3];
				String operation = pathParts[4];
				request.setAttribute("exam_id", exam_id);
				request.setAttribute("operation", operation);
				request.getRequestDispatcher("/question").forward(request, response);
			} else {
				response.sendRedirect("/LMS/dashboard");
			}

//        	System.out.println("ExamController_question: "+pathInfo+" examID: "+examID);

		}
//        else if(pathParts.length==4) {
//        	subjectCode = pathParts[1]; 
//        	route = pathParts[2]; // exams
//        	String oprertion = pathParts[3]; // add/update/delete
//        	System.out.println("pathParts==4");
//        	if (oprertion.equals("addExam")) {
//        		request.getRequestDispatcher("/addExam").forward(request, response);
//        	}
//        	
//        	
//        }
		else {
			// TODO need update
			System.out.println("ExamController_else: " + pathInfo + " length: " + pathParts.length);
			response.sendRedirect("/LMS/dashboard");
		}

//        String stm = "select * from exams where subject_code='"+subjectCode+"'";
//        List<Exam> exams = new ArrayList<>();
//        try {
//        PreparedStatement stmt = DBConnection.prepare(stm);
//        ResultSet rs = stmt.executeQuery();
//        while (rs.next()) { 
//                    int id = Integer.parseInt(rs.getString(1));
//                    String title = rs.getString(2);
//                    int status = Integer.parseInt(rs.getString(3));
//                    String code = rs.getString(4);
//                    Exam exam = new Exam(id,title,status,code);
//                    exams.add(exam);
//         }
//        }catch (SQLException e) {
//
//            System.out.println(e.getMessage());
//        }
//        request.setAttribute("exams", exams);
//        request.getRequestDispatcher("/exams.jsp").forward(request, response);
		// response.sendRedirect("exams.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
		
		
	}

}
