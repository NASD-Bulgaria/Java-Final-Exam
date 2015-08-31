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

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import controller.PlayerQueries;
import validation.ValidationData;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("IndexServlet");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("login_name");
		String password = request.getParameter("login_password");
		boolean isValid = ValidationData.isValidUserNameAndPass(username, password);
		if (isValid && username != null && username != "" && password != null && password != "") {

			HttpSession sesison = request.getSession();
			sesison.setAttribute("user", username);
			HashFunction hash = Hashing.sha1();
			HashCode hs = hash.newHasher().putString(username, Charsets.UTF_8).putString(password, Charsets.UTF_8)
					.hash();

			String token = hs.toString();
			PlayerQueries.addToken(username, token);
			JSONObject json = new JSONObject().put("token", token).put("status", "OK");
			out.println("<p>" + json.toString() + "</p>");
			out.println("<form method=\"POST\" action=\"ControlPanel\" >");
			out.println("<input type=\"hidden\" name=\"token\" value=\"" + token + "\"/>");
			out.println("<input type=\"submit\" value=\"Control Panel\"/>");
			out.println("</form>");

		} else {
			JSONObject json = new JSONObject().put("status", "Not OK");
			out.println("<p>" + json.toString() + "</p>");
			out.println("<a href=\"IndexServlet\">Index page");
		}
	}
}
