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
 * Servlet implementation class Deposit
 */
@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String output = " <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n";
		JSONObject jobject = new JSONObject().put("Status", "Waiting to enter sum");
		out.println(output + jobject +"<html>\n" + "<head><title>Deposit </title></head>\n" + "<H2 ALIGN=\"CENTER\">Deposit</H2>"
				+ "<body bgcolor=\"#6FFDE6\">\n"
				+ "<FORM  action =\"http://localhost:8080/yPlayer/Deposit\"  METHOD=\"POST\"> " + "<CENTER>"
				+ "Amount to deposit : " + "<INPUT TYPE = \"number\" name = \"sum\"><p>"
				+ "<INPUT TYPE = \"Submit\" value = \"Deposit\"> <Submit>" + "</FORM>" + "</BODY></HTML> "
				+"<CENTER>" + "<form action=\"http://localhost:8080/yPlayer/SuccessfullLogin\">"
				+ "<body bgcolor=\"#6FFDE6\">\n" + "<br><input type=\"submit\" value=\"Back to home page\" /><br/>"
				+ "</form>");
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Player play =   (Player) request.getSession().getAttribute("tokenUni");
		try {
			double sum = Double.parseDouble(request.getParameter("sum"));
			Querries.playDeposit(play.getId(), sum);
			if (sum<0) {
				JSONObject jobjectADD = new JSONObject().put("Status", "You can't add negative sum of money!");
				out.println(jobjectADD);
				out.println("<CENTER>" + "<form action=\"http://localhost:8080/yPlayer/Deposit\">"
						+ "<body bgcolor=\"#6FFDE6\">\n"
						+ "<br><input type=\"submit\" value=\"Back to deposit page\" /><br/>" + "</form>");
			}else{
				
			
			JSONObject jobjectResult = new JSONObject().put("Status", "Successfull deposit");
			out.println( "<CENTER>" + jobjectResult);
			out.println("<CENTER>" + "<form action=\"http://localhost:8080/yPlayer/SuccessfullLogin\">"
					+ "<body bgcolor=\"#6FFDE6\">\n"
					+ "<br><input type=\"submit\" value=\"Back to home page\" /><br/>" + "</form>");
		}
		} catch (Exception e) {
			response.sendRedirect("http://localhost:8080/yPlayer/EmptyDepositValue");
		}
		
	}
}
