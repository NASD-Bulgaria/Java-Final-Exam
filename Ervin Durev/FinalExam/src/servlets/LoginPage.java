package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
  
    public LoginPage() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Player</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#e6fdf5\">"
				+ "<H2 ALIGN=\"CENTER\">Player</H2>"
				+ "<CENTER>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/SignIn\" METHOD=\"POST\">"
				+ "User name:"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"username\"><BR>"
				+ "Password:"
				+ "<INPUT TYPE=\"PASSWORD\" NAME=\"password\"><p>"
				+ "<input type=\"submit\" value=\"SignIn\" /><BR><BR>"
				+ "</FORM>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/SignUp\">"
				+ "<input type=\"submit\" value=\"SignUp\" /><BR><BR>"
				+ "</FORM>"
				+ "</CENTER>"  
				+ "</BODY></HTML>"
		);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
