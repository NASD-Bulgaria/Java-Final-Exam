package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.PlayerQueries;
import entity.Player;

/**
 * Servlet implementation class UserPanel
 */
@WebServlet("/UserPanel")
public class UserPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPanel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user");
		PrintWriter out = response.getWriter();
		
		Date date = new Date();
		Player player = PlayerQueries.findUserByUserName(userName);
		out.println("<h1 align=\"center\">Welcome to our User Panel</h1");
		out.println("<h2 align=\"center\">" + date.toString() + "</h2>");
		out.println("<h3 align=\"center\">Your current balance is:</h3>");
		out.println("<h4 align=\"center\">" + player.getBalance() + "</h4>");
		out.println("<form method=\"post\" action=\"DepositServlet\" >");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"hidden\" name=\"user\" value=\"" + userName + "\"/>");
		out.println("<input type=\"Submit\" value=\"deposit\"/>");
		out.println("</form>");

		out.println("<form method=\"post\" action=\"WithdrawServlet\" >");
		out.println("<input type=\"text\" name=\"balance\"/>");
		out.println("<input type=\"hidden\" name=\"user\" value=\"" + userName + "\"/>");
		out.println("<input type=\"Submit\" value=\"withdraw\"/>");
		out.println("</form>");
	}

}
