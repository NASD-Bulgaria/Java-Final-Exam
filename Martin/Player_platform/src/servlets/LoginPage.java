package servlets;

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

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import control.Requests;
import control.ValidationData;

/**
 * Servlet implementation class LoginServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("HomeServ");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		String username = request.getParameter("login_name");
		String password = request.getParameter("login_password");
		boolean isValid = ValidationData.userNamePasswordValid(username, password);
		if (isValid && username!=null && username!="" && password!=null && password!="") {
			HttpSession sesison = request.getSession();
			synchronized (sesison) {
				sesison.setAttribute("user", username);
				request.getSession().setMaxInactiveInterval(600);
			} 
			
			HashFunction hash = Hashing.sha1();
			HashCode hs = hash.newHasher().putString(username, Charsets.UTF_8).putString(password, Charsets.UTF_8)
					.hash();

			String token = hs.toString();
			Requests.addToken(username, token);
			JSONObject json = new JSONObject().put("token", token).put("status", "ok");
			out.println("<p>" + json.toString() + "</p>");
			out.println("<form method=\"POST\" action=\"ControlServ\" >");
			out.println("<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>");
			out.println("<input type=\"submit\" value=\"go to controlPanel\"/>");
			out.println("</form>");

		} else {
			JSONObject json = new JSONObject().put("status", "failed");
			out.println("<p>" + json.toString() + "</p>");
			out.println("<a href=\"HomeServ\">Go to login page");
		}
	}

}
