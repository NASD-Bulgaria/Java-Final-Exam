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
import model.Player_profile;


/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControlServlet() {
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
		String output = " <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n";
		String token =request.getParameter("token");
		request.getSession().setMaxInactiveInterval(60000);
		
		PrintWriter out = response.getWriter();
		Player_profile result = Requests.showPlayerProfile(token);
		JSONObject json = new JSONObject().put("sucses", "ok");
		double balance = Requests.showBalance(token);
		out.println(output + "<html>\n" + "<head><title></title></head>");
		out.println("<body>");
		out.println("<p>" + json.toString() + "</p>");
		out.println("<h1>Hi : " + result.getFirst_name()+"  "+ result.getLast_name() + "</h1>");
		out.println("<h2>Your balance is : " + balance + "<h2>");
		out.println("<form method=\"POST\" action=\"DepositServlet\" >");
		out.println("<p>input Balance</p><br/>");
		out.println("<input type=\"hidden\" name=\"token\" value=\"" + token + "\"/>");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"submit\" value=\"deposit\"/>");
		out.println("</form>");
		out.println("<form method=\"POST\" action=\"WithDrawServlet\" >");
		out.println("<input type=\"hidden\" name=\"token\" value=\"" + token + "\"/>");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"submit\" value=\"withdraw\"/>");
		out.println("</form>");
		out.println("</body>");

	}

}
