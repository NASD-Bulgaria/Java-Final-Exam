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
 * Servlet implementation class Deposit
 */
@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
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
			String token =  request.getParameter("token");
			String amount = request.getParameter("deposit");

			if (Validation.validateDeposit(loginName, Double.valueOf(amount))) {

				JSONObject json = new JSONObject();
				json.put("token", token);
				json.put("amount", amount);
				out.println(json);
				out.print("<br>");
				out.println("You added " + amount + " to your account!");
			} else {
				out.println("Invalid input");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.println("Invalid  input!");
		}
	}

}
