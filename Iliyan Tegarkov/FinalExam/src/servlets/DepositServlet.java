package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import control.PlayerRequests;

/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
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
			String depositAmount = request.getParameter("deposit");
			boolean valid = PlayerRequests.deposit(temp, Double.parseDouble(depositAmount));
			JSONObject json = new JSONObject().put("status", "ok");
			if (valid) {
				out.println("<p>" + json.toString() + "</p>");
				out.println("You Succesfully added " + depositAmount + " to your bank account!");
			} else {
				out.println("Please, enter correct amount!");
			}
		} catch (NumberFormatException e) {
			out.println("Please enter a correct number for balance!");
		}
	}

}
