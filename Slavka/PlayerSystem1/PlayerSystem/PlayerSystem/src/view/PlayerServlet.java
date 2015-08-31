package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.Queries;
import model.Player;

/**
 * Servlet implementation class CompanyServlet
 */
@WebServlet("/PlayerServlet")
public class PlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlayerServlet() {
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
	//	String getToken = (String) request.getSession().getAttribute("token");
		String output = " <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n";
		PrintWriter out = response.getWriter();
		Player result = Queries.showPlayerByLogin_name(request.getParameter("user"));
		JSONObject json = new JSONObject().put("status", "ok");
		out.println(output + "<html>\n" + "<head><title></title></head>");
		out.println("<body>");
		out.println("<p>" + json.toString() + "</p>");
		out.println("<h1>Your balance is : " + result.getBalance() + "<h1>");
		out.println("<form method=\"POST\" action=\"Deposit\" >");		
		out.println("<input type=\"hidden\" name=\"temp\" value=\"" + request.getParameter("user") + "\"/>");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"submit\" value=\"deposit\"/>");
		out.println("</form>");
		out.println("<form method=\"POST\" action=\"WithDrawServlet\" >");
		out.println("<input type=\"hidden\" name=\"temp\" value=\"" + request.getParameter("user") + "\"/>");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"submit\" value=\"withdraw\"/>");
		out.println("</form>");
		out.println("</body>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
