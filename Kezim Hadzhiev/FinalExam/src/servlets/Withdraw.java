package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Player;

import org.json.JSONObject;

import queries.QueriesDB;

@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Withdraw() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String referer = request.getHeader("Referer");
		if(referer != null && referer.equals("http://localhost:8080/FinalExam/Login")){
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Player</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#00FFCC\"> "
			
				+ "<CENTER>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/Withdraw\" METHOD = \"POST\">"
				+ "How much: "
				+ "<INPUT TYPE=\"TEXT\" NAME=\"Amount\"><BR><BR>"
				+ "<input type=\"submit\" value=\"Withdraw\" /><BR>"
				+ "</FORM>" 
				+ "<FORM action=\"http://localhost:8080/FinalExam/Login\">"
				+ "<BR>"
				+ "<input type=\"submit\" value=\"Back\" /><BR>"
				+ "</CENTER>" 
				+ "</BODY></HTML>");
		}
		else {
			response.sendRedirect("http://localhost:8080/FinalExam/MainPage");
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			double withdraw = Double.parseDouble(request.getParameter("Amount"));

			String name = (String) request.getSession().getAttribute("username");

			Player player = QueriesDB.getPlayerByName(name);

			if (withdraw > 0 && withdraw <= player.getBalance()) {
				QueriesDB.withdrawAmount(player.getId(), withdraw);

				jsonObject.put("STATUS:", "OK");
				jsonObject.put("TOKEN:", request.getSession().getAttribute("token"));

				out.print("<html><head><title></title></head>"
								+ "<BODY BGCOLOR=\"#00FFCC\"> "
								+ "<form action= \"http://localhost:8080/FinalExam/Login\">"
								+ jsonObject + "<center>" + "<BR>"
								+ "Your balance now is: "
								+ (player.getBalance() - withdraw)
								+ "<input type=\"submit\" value=\"Back\" />"
								+ "</center>" + "</form></body></html>");
			} else {
				jsonObject.put("STATUS", "FAILED");

				out.print("<html><head><title></title></head>"
								+ "<BODY BGCOLOR=\"#00FFCC\"> "
								+ "<form action= \"http://localhost:8080/FinalExam/Login\">"
								+ jsonObject + "<center>"
								+ "Your balance is not enought SORRY!!"
								+ "<input type=\"submit\" value=\"Back\" />"
								+ "</center>" + "</form></body></html>"

						);
			}

		} catch (NumberFormatException e) {
			jsonObject.put("STATUS", "FAILED");

			out.print("<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
							+ "<form action= \"http://localhost:8080/FinalExam/Login\">"
							+ jsonObject + "<center>" + "Your must entered ammount! "
							+ "<input type=\"submit\" value=\"Back\" />"
							+ "</center>" + "</form></body></html>"

					);
		}
	}
}
