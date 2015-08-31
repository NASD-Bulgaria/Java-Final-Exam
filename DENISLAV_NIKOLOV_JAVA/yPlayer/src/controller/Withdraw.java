package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import yPlayer.Player;

/**
 * Servlet implementation class Withdraw
 */
@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Withdraw() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			PrintWriter out = response.getWriter();
			String output = " <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n";
			JSONObject jobject = new JSONObject().put("Status", "Waiting to enter sum");
			out.println(output + jobject + "<html>\n" + "<head><title> Withdraw </title></head>\n"
					+ "<H2 ALIGN=\"CENTER\">Withdraw</H2>" + "<body bgcolor=\"#6FFDE6\">\n"
					+ "<FORM  action =\"http://localhost:8080/yPlayer/Withdraw\"  METHOD=\"POST\"> " + "<CENTER>"
					+ "Amount to withdraw : " + "<INPUT TYPE = \"number\" name = \"sum\"><p>"
					+ "<INPUT TYPE = \"Submit\" value = \"Withdraw\"> <Submit>" + "</FORM>" + "</BODY></HTML> "
					+ "<CENTER>" + "<form action=\"http://localhost:8080/yPlayer/SuccessfullLogin\">"
					+ "<body bgcolor=\"#6FFDE6\">\n" + "<br><input type=\"submit\" value=\"Back to home page\" /><br/>"
					+ "</form>");
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Player play = (Player) request.getSession().getAttribute("tokenUni");
		try {
			double sum = Double.parseDouble(request.getParameter("sum"));
			
			Player plBalance = Querries.findplayer(play.getLoginName());
			Querries.playWithdraw(play.getId(), sum);
			if (plBalance.getBalance() < sum) {
				JSONObject jobjectWithdr = new JSONObject().put("Status", "Unsufficient funds!");
				out.println(jobjectWithdr);
				out.println("<CENTER>" + "<form action=\"http://localhost:8080/yPlayer/Withdraw\">"
						+ "<body bgcolor=\"#6FFDE6\">\n"
						+ "<br><input type=\"submit\" value=\"Back to withdraw page\" /><br/>" + "</form>");
			} else {
				JSONObject jobjectResult = new JSONObject().put("Status", "Successfull withdraw");
				out.println("<CENTER>" + jobjectResult);
				out.println("<CENTER>" + "<form action=\"http://localhost:8080/yPlayer/SuccessfullLogin\">"
						+ "<body bgcolor=\"#6FFDE6\">\n" + "<br><input type=\"submit\" value=\"Back to home page\" /><br/>"
						+ "</form>");
			}
		} catch (Exception e) {
			response.sendRedirect("http://localhost:8080/yPlayer/EmptyValue");
		}
		

	}

}
