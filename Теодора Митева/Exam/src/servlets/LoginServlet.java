package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

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

import request.PlayerRequest;

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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String loginPassword = request.getParameter("loginPassword");

		boolean valid = validation.Validation
				.validate(loginName, loginPassword);
		HttpSession session = request.getSession();

		if (valid) {
			session.setAttribute("loginName", loginName);
			request.getSession().setMaxInactiveInterval(600);

			JSONObject json = new JSONObject().put("status", "ok");

			HashFunction hf = Hashing.sha1();
			// unique token !?
			Random rand = new Random();
			int randomNum = (rand.nextInt(9999999 - 1) + 1) + 1;
			HashCode hc = hf.newHasher().putString(loginName, Charsets.UTF_8)
					.putInt(randomNum).hash();
			PlayerRequest.setTokenToPlayer(loginName, hc.toString());

			json.put("token", hc.toString());

			PrintWriter out = response.getWriter();
			out.println(json);
			out.println("<!DOCTYPE html PUBLIC "
					+ " // W3C//DTD HTML 4.01"
					+ "	// Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\""
					+ "<html>"
					+ "	<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
					+ "<title>Transactions</title>"
					+ "	</head>"
					+ "	<body>"
					
					+ "<form method=\"POST\" action=\"Deposit\">"
					+ "	<br>Deposit: <input type=\"text\" name=\"deposit\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\"" + hc.toString() + "\"/> "
					+ "<input type=\"hidden\" name=\"loginName\" value=\""+ loginName	+ "\"/> "

					+ "<input type=\"submit\" name=\"depositB\" value=\"Deposit\" />"

					+ "	</form>"
					
					
					+ "	<form method=\"POST\" action=\"Withdraw\">"
					+ "	<br> Withdraw: <input type=\"text\" name=\"withdraw\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\"" + hc.toString() + "\"/>"
					+ "<input type=\"hidden\" name=\"loginName\" value=\"" + loginName + "\"/> "

					+ " <input type=\"submit\" name=\"withdrawB\" value=\"Withdraw\" />"

					+ "</form>"

					+ "<form method=\"POST\" action = \"Logout\">"
					
					+ "<br><input type=\"hidden\" name=\"token\" value=\""+ hc.toString()+"\"/>"
					+ "<br><input type=\"hidden\" name=\"loginName\" value=\"" + loginName + "\"/>"
					+ "<input type=\"submit\" name=\"logout\" value=\"Logout\" />"
					+ "</form>"

					+ "	</body>" + "</html>");

		} else {
			JSONObject json = new JSONObject().put("status", "invalid");

			PrintWriter out = response.getWriter();
			out.println(json);
		}
	}

}
