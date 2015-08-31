package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import queries.QueriesDB;
import model.Player;

@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Deposit() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String referer = request.getHeader("Referer");
		if(referer != null && referer.equals("http://localhost:8080/FinalExam/Login")){
		response.setContentType("text/html");
		
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Player</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#00FFCC\"> "
		
				+ "<CENTER>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/Deposit\" METHOD = \"POST\">"
				+ "Enter amount: "
				+ "<INPUT TYPE=\"TEXT\" NAME=\"Deposit\"><BR><BR><BR>"
				+ "<input type=\"submit\" value=\"Deposit\" />"
				+ "</FORM>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/Login\">"
				+ "<BR>"
				+ "<input type=\"submit\" value=\"Back\" /><BR>"
				+ "</CENTER>" + "</BODY></HTML>");
		}
		else {
			response.sendRedirect("http://localhost:8080/FinalExam/MainPage");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = response.getWriter();
		
		try{
		double ammount = Double.parseDouble(request.getParameter("Deposit"));
	
		String username = (String) request.getSession().getAttribute("username");

		Player player = QueriesDB.getPlayerByName(username);

		if (ammount > 0) {
			QueriesDB.depositeAmmount(player.getId(), ammount);

			jsonObject.put("STATUS", "OK");
			jsonObject.put("TOKEN", request.getSession().getAttribute("token"));

			out.print("<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
							+ "<form action= \"http://localhost:8080/FinalExam/Login\">"
							+ jsonObject + "<center>" + "<BR>"
							+ "Your balance now is: "
							+ (player.getBalance() + ammount)
							+ "<input type=\"submit\" value=\"Back\" />"
							+ "</center>" + "</form></body></html>");
		} 
		else {
			jsonObject.put("STATUS", "FAILED");

			out.print("<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
							+ "<form action= \"http://localhost:8080/FinalExam/Login\">"
							+ jsonObject + "<center>" + "Your entered amount is wrong!! "
							+ "<input type=\"submit\" value=\"Back\" />"
							+ "</center>" + "</form></body></html>"

					);
		}
		}
		catch(NumberFormatException e){
			jsonObject.put("STATUS", "FAILED");

			out.print("<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
							+ "<form action= \"http://localhost:8080/FinalExam/Login\">"
							+ jsonObject + "<center>" + "Your must entered number! "
							+ "<input type=\"submit\" value=\"Back\" />"
							+ "</center>" + "</form></body></html>"

					);
		}

	}

}
