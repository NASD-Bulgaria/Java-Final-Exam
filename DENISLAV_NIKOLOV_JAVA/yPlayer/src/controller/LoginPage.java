package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PrintWriter out = response.getWriter();
		
		String output = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">";
		
		out.println(output + "<HTML>" + "<HEAD><TITLE>Sample Form</TITLE></HEAD>" + "<BODY BGCOLOR=\"#6FFDE6\">"
				+ "<H2 ALIGN=\"CENTER\">Welcome to our login page</H2>"
				+ "<FORM ACTION=\"http://localhost:8080/yPlayer/AuthenticationPage\" Method =\"post\">"
				+ "<CENTER>" + "Username:" + "<INPUT TYPE=\"TEXT\" NAME=\"Username\"><BR>" + "Password:"
				+ "<INPUT TYPE=\"password\" NAME=\"Password\" ><P>"
				+ "<INPUT TYPE=\"SUBMIT\" VALUE = \"Login\"> <!-- Press this to submit form -->" + "</CENTER>"
				+ "</FORM>" + "<FORM ACTION=\"http://localhost:8080/yPlayer/SignUpPage\" Method =\"post\">"
				+ "<Center>" + "<INPUT TYPE=\"SUBMIT\" VALUE=\"Register\"> <!-- Press this to submit form -->"
				+ "</CENTER>" + "</FORM>" + "</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
