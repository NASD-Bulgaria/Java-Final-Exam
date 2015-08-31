package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.json.JSONObject;

import control.PlayerRequests;

/**
 * Servlet implementation class WithrawServlet
 */
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet implements HttpSessionListener{
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			String temp = request.getParameter("token");
			String withdrawAmount = request.getParameter("withdraw");
			int isDone = PlayerRequests.withdraw(temp, Double.parseDouble(withdrawAmount));
			JSONObject json = new JSONObject().put("status", "ok");
			if (isDone == 1) {
				out.println("<p>" + json.toString() + "</p>");
				out.println("You Succesfully withdrawed " + withdrawAmount + " from your bank account!");
			} else if (isDone == 0) {
				out.println("You dont have enought in your bank account!");
			} else {
				out.println("Please enter a correct amount!");
			}
		} catch (NumberFormatException e) {
			out.println("Please enter a valid number for balance!");
		}
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {

	}

}
