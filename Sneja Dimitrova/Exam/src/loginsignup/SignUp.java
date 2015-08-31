package loginsignup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Sign up</title>"
				+ "</head><body><form method=\"post\" action=\"http://localhost:8080/Exam13.08/SignUpHandler\" >"
				+ "<p>username: </p><input name=\"username\" type=\"text\" /><p>password: </p>"
				+ "<input name=\"password\" type=\"password\" />"
				+"<p>First name</p>"
				+"<input name=\"firstName\" type=\"text\" />"
				+"<p>Last name</p>"
				+"<input name=\"lastName\" type=\"text\" />"
				+"<p>Balance</p>"
				+"<input name=\"balance\" type=\"text\" />"
				+"<p><input type = \"submit\" value = \"Sign up\" />"
				+ "</p></form></body></html>");
	}//end method doPost
}//end class
