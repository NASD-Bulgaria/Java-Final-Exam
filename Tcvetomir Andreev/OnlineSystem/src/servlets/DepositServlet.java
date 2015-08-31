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

import control.Requests;
import control.ValidationData;

/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepositServlet() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("HomeServlet");
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
		String balance = request.getParameter("balance");
		String token = request.getParameter("token");
		request.getSession().setMaxInactiveInterval(60000);
		try {

			double bal = Double.valueOf(balance);

			if (bal > 0) {
				Requests.depositBalance(token, bal);
				JSONObject result = new JSONObject().put("status", "ok");
				out.println("<p>" + result.toString() + "</p>");
				out.println("<form method=\"POST\" action=\"ControlServlet\" >");
				out.println("<p>input Balance</p>");
				out.println("<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>");
				out.println("<input type=\"submit\" value=\"go back to control panel\"/>");
				out.println("</form>");

			} else {
				out.println("<p>can't deposit </p>");
				out.println("<form method=\"POST\" action=\"ControlServlet\" >");
				out.println("<p>input Balance</p>");
				out.println("<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>");
				out.println("<input type=\"submit\" value=\"go back to control panel\"/>");
				out.println("</form>");
			}

		} catch (NumberFormatException e) {
			// TODO: handle exception
			out.println("<p>can't deposit invalid balance </p>");
			out.println("<form method=\"POST\" action=\"ControlServlet\" >");
			out.println("<p>input Balance</p><br/>");
			out.println("<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>");
			out.println("<input type=\"submit\" value=\"go back to control panel\"/>");
			out.println("</form>");

		}
	}

}
