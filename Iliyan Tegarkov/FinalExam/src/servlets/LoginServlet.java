package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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

import control.PlayerRequests;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("playerName");
		String password = request.getParameter("password");

		boolean valid = PlayerRequests.loginValidate(username, password);
		HttpSession session = request.getSession();
		if (valid) {
			session.setAttribute("playerName", username);
			session.setMaxInactiveInterval(30);
			HashFunction hf = Hashing.sha1();
			HashCode hc = hf.newHasher().putString(username, Charsets.UTF_8).hash();
			PlayerRequests.addToken(username, hc.toString());
			JSONObject json = new JSONObject().put("success", "ok").put("token",hc.toString());
			out.println("<p>" + json.toString() + "</p>");
			out.println("<form method=\"post\" action=\"SuccessServlet\">");
			out.println("<input type=\"hidden\" name=\"token\" value=\"" + hc.toString() + "\"/>");
			out.println("<input type=\"submit\" value=\"Next\"/>");
			out.println("</form>");
			return;
		} else {
			out.println("Wrong input data!");
		}
	}
}
