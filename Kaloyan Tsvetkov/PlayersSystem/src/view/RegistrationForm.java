package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationForm
 */
@WebServlet("/RegistrationForm")
public class RegistrationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter()
		.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>A Sample Form</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#e6fdf5\">"
				+ "<H2 ALIGN=\"CENTER\">Registration Form</H2>"
				+ "<FORM action=\"/PlayersSystem/ConfirmRegister\" method=\"POST\">"
				+ "<CENTER>"
				+ "User name:<BR>"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"userName\"><BR>"
				+ "Password:<BR>"
				+ "<INPUT TYPE=\"password\" NAME=\"passw\"><BR>"
				+ "First Name:<BR>"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"firstName\"><BR><BR>"
				+ "Last Name:<BR>"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"lastName\"><BR><BR>"
				+ "<INPUT type=\"submit\" value=\"Sign Up\" /><BR><BR>"
				+ "</CENTER>" + "</FORM>"
				+ "<FORM action=\"/PlayersSystem/MainServlet\" method=\"GET\">"
				+ "<CENTER>"
				+ "<INPUT type=\"submit\" value=\"Back\" /><BR><BR>"
				+ "</CENTER>" + "</FORM>" + "</BODY></HTML>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
