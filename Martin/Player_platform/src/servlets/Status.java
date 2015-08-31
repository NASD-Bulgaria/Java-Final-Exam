package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import control.Requests;
import control.ValidationData;

import model.Player;

/**
 * Servlet implementation class RegSucsses
 */
@WebServlet("/Status")
public class Status extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Status() {
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
		String username = request.getParameter("login_name").trim();
		String password = request.getParameter("login_password").trim();
		String first_name = request.getParameter("firstName").trim();
		String last_name = request.getParameter("LastName").trim();

		boolean isValid = ValidationData.userNameExist(username);
		if (isValid) {
			HashFunction hash = Hashing.sha256();
			String salt = hash.newHasher().putString(username, Charsets.UTF_8).hash().toString();
			String pass = hash.newHasher().putString(password, Charsets.UTF_8).hash().toString();
			HashCode hs = hash.newHasher().putString(pass, Charsets.UTF_8).putString(salt, Charsets.UTF_8).hash();
			String result = hs.toString();
			Player player = Requests.regPlayer(username, result, salt);
			Requests.regPlayer_Profile(first_name, last_name, player);
			JSONObject json = new JSONObject().put("status", "ok");
			out.println("<p>" + json.toString() + "</p>");
			out.println("<a href=\"HomeServ\">Go to login page");
			out.println("</a>");
		} else {
			out.println("Username taken");
			out.println("<a href=\"RegisterServ\">Try Again");
			out.println("</a>");
		}
	}

}
