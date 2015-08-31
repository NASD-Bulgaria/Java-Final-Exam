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
import model.PlayerProfile;

/**
 * Servlet implementation class ControlPanel
 */
@WebServlet("/ControlPanel")
public class ControlPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlPanel() {
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
		String token =request.getParameter("token");
		request.getSession().setMaxInactiveInterval(10*60);
		PrintWriter out = response.getWriter();
		PlayerProfile playerProfile = PlayerQueries.showPlayersProfiles(token);
		JSONObject json = new JSONObject().put("status", "OK");
		double balance = PlayerQueries.showBalance(token);
		out.println(" <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n"
				+ "<html>\n" + "<head><title></title></head>");
		out.println("<body>");
		out.println("<p>" + json.toString() + "</p>");
		out.println("<h1>Hi : " + playerProfile.getFirst_name()+"  "+ playerProfile.getLast_name() + "</h1>");
		out.println("<h2>Your balance is : " + balance + "<h2>");
		out.println("<form method=\"POST\" action=\"DepositServlet\" >");
		out.println("<p>Make your choise</p><br/>");
		out.println("<input type=\"hidden\" name=\"token\" value=\"" + token + "\"/>");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"submit\" value=\"add cash :(\"/>");
		out.println("</form>");
		out.println("<form method=\"POST\" action=\"WithdrawServlet\" >");
		out.println("<input type=\"hidden\" name=\"token\" value=\"" + token + "\"/>");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"submit\" value=\"get cash :)\"/>");
		out.println("</form>");
		out.println("</body>");
	}

}
