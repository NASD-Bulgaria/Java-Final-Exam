package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String output = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Players System</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#e6fdf5\">"
				+ "<H2 ALIGN=\"CENTER\">Wellcome!</H2>"
				+ "<FORM action=\"/PlayersSystem/ConfirmLogIn\" method=\"POST\">"
				+ "<CENTER>"
				+ "User name:<BR>"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"userName\"><BR>"
				+ "Password:<BR>"
				+"<INPUT TYPE=\"PASSWORD\" NAME=\"pass\"><p>"
				+"<input type=\"submit\" value=\"log in\" /><BR><BR>"
				+ "<a href=\"/PlayersSystem/RegistrationForm\">Register</a>"
				+ "</CENTER>" + "</FORM>" + "</BODY></HTML>";
		out.println(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
