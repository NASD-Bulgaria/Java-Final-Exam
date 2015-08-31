package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import model.ValidationsUsernames;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String loginName = request.getParameter("loginName");
		String loginPassword = request.getParameter("loginPassword");
		boolean isValid = ValidationsUsernames.validForLogin(loginName, loginPassword);
		if (isValid) {
			HttpSession session = request.getSession();
			session.setAttribute("user", loginName);
			session.setMaxInactiveInterval(500);
			
			JSONObject json = new JSONObject().put("status", "ok");
			out.println("<p>"+json.toString()+" </p>");
			out.println(
					"<form method=\"post\" action=\"PlayerServlet\">" + 
			"<input type=\"hidden\" name=\"user\" value=\""
			+ loginName + "\"/>" + 
			"<input type=\"submit\"  value=\"Player info\"/>" + 
			"</form>");

		} else {
			out.println("<p>wrong pass and username</p>");
			out.println("<a href=\"http://localhost:8080/PlayerSystem/index.jsp\">Go to login panel");
			out.println("</a>");
		}

	}

}
