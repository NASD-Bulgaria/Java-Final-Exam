package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegisterServ")
public class RegisterServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<form method=\"POST\" action=\"Status\" >");
		out.println("<p>UserName:</p></br>");
		out.println("<input type=\"text\" name=\"login_name\"/>");
		out.println("<p>Password:</p></br>");
		out.println("<input type=\"password\" name=\"login_password\"/>");
		out.println("</br>");
		out.println("<p>First name:</p></br>");
		out.println("<input type=\"text\" name=\"firstName\"/>");
		out.println("<p>Last name</p></br>");
		out.println("<input type=\"text\" name=\"LastName\"/>");
		out.println("</br>");
		out.println("<input type=\"submit\" value=\"register\"/>");
		out.println("</form>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
