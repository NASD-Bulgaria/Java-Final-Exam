package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import logic.GameQueries;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/WithdrawServ")
public class WithdrawServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GameQueries query;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WithdrawServ() {
		super();
		query = new GameQueries();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String token = request.getParameter("token");
		String amount = request.getParameter("amount");

		HttpSession session = request.getSession();
		String tokenAttr = (String) session.getAttribute("token");

		if (tokenAttr == null || !query.isTokenExist(tokenAttr)) {

			query.deleteToken(token);

			JSONObject jsonObject = new JSONObject().put("status", "offline");
			response.getWriter()
					.println(
							"<HTML>"
									+ "<TITLE>Error</TITLE>"
									+ "<BODY>"
									+ "You are offline!<br>  "
									+ jsonObject.toString()
									+ "<br><a href=\"http://localhost:8080/OnlineGameSystemProject\">LogIn</a>"
									+ "</BODY></HTML>");

		} else {
			try {
				double amoutToDouble = Double.valueOf(amount);
				if (token != null && amount != null && amoutToDouble > 0
						&& query.withdrawBalance(amoutToDouble, token)) {
					JSONObject jsonObject = new JSONObject()
							.put("status", "ok");
					response.getWriter().println(jsonObject.toString());
				} else {
					JSONObject jsonObject = new JSONObject().put("status",
							"not ok");
					response.getWriter().println(jsonObject.toString());
				}
			} catch (Exception e) {
				JSONObject jsonObject = new JSONObject().put("status", "not ok");
				response.getWriter().println(jsonObject.toString());
			}
		}

	}

}
