package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainPage
 */
@WebServlet("/MainPage")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  
    public MainPage() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String referer = request.getHeader("Referer");
		PrintWriter out = response.getWriter();
		if(referer!= null && referer.equals("http://localhost:8080/FinalExam/Login")){
		request.getSession().removeAttribute("token");
		request.getSession().removeAttribute("username");
		}
		
		
		out.println(
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Login</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#00FFCC\"> "
				+ "<H2 ALIGN=\"CENTER\">PLAYER LOGIN</H2>"
				+ "<CENTER>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/Login\" METHOD=\"POST\">"
				+ "User name:"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"username\"><BR><BR>"
				+ "Password:"
				+ "<INPUT TYPE=\"PASSWORD\" NAME=\"password\"><p><BR>"
				+ "<input type=\"submit\" value=\"Login\" /><BR><BR>"
				+ "</FORM>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/Registration\">"
				+ "<input type=\"submit\" value=\"Registration\" /><BR><BR>"
				+ "</FORM>"
				+ "</CENTER>"  
				+ "</BODY></HTML>"
		);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
