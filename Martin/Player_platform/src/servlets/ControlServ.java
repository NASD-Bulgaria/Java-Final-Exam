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



/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/ControlServ")
public class ControlServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlServ() {
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
		
		String token =request.getParameter("token");

		
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject().put("sucses", "ok");
		double balance = Requests.showBalance(token);
		out.println("<p>" + json.toString() + "</p>");
		out.println("<h2>Your balance is : " + balance + "<h2>");
		out.println("<form method=\"POST\" action=\"DepositServ\" >");
		out.println("<p>input Balance</p><br/>");
		out.println("<input type=\"hidden\" name=\"token\" value=\"" + token + "\"/>");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"submit\" value=\"deposit\"/>");
		out.println("</form>");
		out.println("<form method=\"POST\" action=\"WithDrawServ\" >");
		out.println("<input type=\"hidden\" name=\"token\" value=\"" + token + "\"/>");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"submit\" value=\"withdraw\"/>");
		out.println("</form>");
		

	}

}
