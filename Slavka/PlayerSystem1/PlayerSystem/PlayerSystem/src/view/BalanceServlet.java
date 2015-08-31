package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import controller.Queries;


/**
 * Servlet implementation class BalanceServlet
 */
@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BalanceServlet() {
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
		PrintWriter out= response.getWriter();
		String balance = request.getParameter("balance");
		try {		
			double bal = Double.valueOf(balance);
			Queries.addDeposit(request.getParameter("temp"), bal);
			JSONObject result = new JSONObject().put("status", "ok");
			out.println("<p>"+result.toString()+"</p>");
			out.println("<form method=\"POST\" action=\"PlayerServlet\" >");
			out.println("<input type=\"hidden\" name=\"user\" value=\""+request.getParameter("temp")+"\"/>");
			out.println("<input type=\"submit\" value=\"Player panel\"/>");
			out.println("</form>");		
			
		} catch (NullPointerException e) {
			

		}
	}

}
