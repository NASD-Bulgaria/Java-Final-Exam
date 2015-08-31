package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DepositWithdrawTest
 */
@WebServlet("/DepositWithdrawTest")
public class DepositWithdrawTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepositWithdrawTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pr = response.getWriter();
		pr.print("<html><head><title>Test</title></head>"
				+ "<body><form action=\"http://localhost:8080/FinalTest/Deposit\" method=\"post\">"
				+ "<br/><p>Deposit</p><input type=\"text\" name=\"amount\"/>"
				+ "<br><input type=\"submit\" value=\"Deposit\"/>"
				+ "<form action=\"http://localhost:8080/FinalTest/Withdraw\" method=\"post\">"
				+ "<br/><p>Withdraw</p><input type=\"text\" name=\"amount\"/>"
				+ "<br><input type=\"submit\" value=\"Withdraw\"/>"
				+ "</form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
