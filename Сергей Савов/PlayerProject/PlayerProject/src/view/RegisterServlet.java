package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.ServiceMethods;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String loginName = "";
	private String loginPass = "";
	private String repeatPass = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML ><HTML><HEAD><TITLE>Login Post</TITLE></HEAD><BODY>"
				+ "<form method = \"Post\" action=\"http://localhost:8080/PlayerProject/RegisterServlet\""
				+ "<H1>Registration</H1>" 
				+ "<p>Username</p>"
				+ "<input type=\"text\" name = \"loginname\"></input>" 
				+ "<p>Password</p>"
				+ "<input type=\"password\" name = \"loginpass\"></input>"
				+ "<p>Repeat Password</p>"
				+ "<input type=\"password\" name = \"loginpass2\"></input>"
				+ "<input type=\"submit\" value=\"registration\">"
				+ "</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML ><HTML><HEAD><TITLE>Login Post</TITLE></HEAD><BODY>"
//				+ "<form method = \"Post\" action=\"http://localhost:8080/PlayerProject/RegisterServlet\""
//				+ "<H1>Registration1</H1>" 
//				+ "<p>Username</p>"
//				+ "<input type=\"text\" name = \"loginname\"></input>" 
//				+ "<p>Password</p>"
//				+ "<input type=\"password\" name = \"loginpass\"></input>"
//				+ "<p>Repeat Password</p>"
//				+ "<input type=\"password\" name = \"loginpass2\"></input>"
//				+ "<input type=\"submit\" value=\"register\">"
//				+ "</form>");
		
		loginName = request.getParameter("loginname");
		loginPass = request.getParameter("loginpass");
		repeatPass = request.getParameter("loginpass2");
		
		if(loginPass.equals(repeatPass)) {

			ServiceMethods.createPlayer(loginName, loginPass);
			
				
			response.sendRedirect("Login.html");
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
			out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
				
		}
		
	}

}
