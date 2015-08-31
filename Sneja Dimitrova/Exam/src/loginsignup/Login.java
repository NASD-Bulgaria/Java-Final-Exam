package loginsignup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Log In</title>"
				+ "</head><body><form method=\"post\" action=\"http://localhost:8080/Exam13.08/LogInHandler\" >"
				+ "<p>username: </p><input name=\"username\" type=\"text\" /><p>password: </p>"
				+ "<input name=\"password\" type=\"password\" /><p>"
				+ "<p><a href = \"http://localhost:8080/Exam11.08/SignUp\">Sign up</a></p>"
				+ "<input type = \"submit\" value = \"Log in\" />"
				+ "</p></form></body></html>");
	}//end method doPost
}//end class
