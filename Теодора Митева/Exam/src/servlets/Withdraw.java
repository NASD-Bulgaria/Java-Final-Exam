package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import validation.Validation;

/**
 * Servlet implementation class Withdraw
 */
@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		try {
			String loginName = request.getParameter("loginName");
			String token = request.getParameter("token");
			String amount = request.getParameter("withdraw");

			if (Validation.validateWithdraw(loginName, Double.valueOf(amount))) {

				JSONObject json = new JSONObject();
				json.put("token", token);
				json.put("withdraw", amount);
				out.println(json);
				out.print("<br>");
				out.println("You withdraw money!");
			} else {
				out.println("You don't have enough money or the input data is invalid!");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.println("Invalid  input");
		}
	}

}
