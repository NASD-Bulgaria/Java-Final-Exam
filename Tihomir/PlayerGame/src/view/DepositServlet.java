package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import controller.PlayerQueries;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("IndexServlet");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String balance = request.getParameter("balance");
		String token = request.getParameter("token");
		request.getSession().setMaxInactiveInterval(10*60);
		try {

			double amount = Double.valueOf(balance);

			if (amount > 0) {
				PlayerQueries.deposit(token, amount);
				JSONObject result = new JSONObject().put("status", "OK");
				out.println("<p>" + result.toString() + "</p>");
				out.println("<form method=\"POST\" action=\"ControlPanel\" >");
				out.println("<p>Make your choise</p>");
				out.println("<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>");
				out.println("<input type=\"submit\" value=\"back\"/>");
				out.println("</form>");

			} else {
				out.println("<p>Can not deposit negative amounts </p>");
				out.println("<form method=\"POST\" action=\"ControlPanel\" >");
				out.println("<p>Make your choise</p>");
				out.println("<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>");
				out.println("<input type=\"submit\" value=\"back\"/>");
				out.println("</form>");
			}

		} catch (NumberFormatException e) {
			// TODO: handle exception
			out.println("<p>You are trying to enter invalid amount </p>");
			out.println("<form method=\"POST\" action=\"ControlPanel\" >");
			out.println("<p>Add funds to your acount</p><br/>");
			out.println("<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>");
			out.println("<input type=\"submit\" value=\"back\"/>");
			out.println("</form>");

		}
	}

}
