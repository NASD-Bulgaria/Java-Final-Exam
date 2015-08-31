package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controler.Queries;

/**
 * Servlet implementation class ConfirmRegister
 */
@WebServlet("/ConfirmRegister")
public class ConfirmRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmRegister() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/PlayersSystem/MainServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String usName = request.getParameter("userName");
		String pass = request.getParameter("passw");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		int result;
		synchronized (this) {
			result = Queries.addUser(firstName, lastName, usName, pass);
		}
		
		String output = " <!doctype html public \"-//w3c//dtd html 5.0 transitional//en\">";

		switch (result) {
		default:
			String token = Queries.getUniqueToken();
			
			synchronized (this) {
				Queries.setToken(result, token);
			}			
			
			JSONObject jOb = new JSONObject().put("loginName", "test").put("loginPassword", "pass");
			out.println(output
					+ "<html><head><title>Succes Registration!</title></head>"
					+ "<body bgcolor=\"#fafde6\"><BR><BR>"
					+ "<form action=\"/PlayersSystem/PlayerServlet\" method=\"POST\">"
					+ "<CENTER><BR><BR>"
					+ "<p>" + jOb.toString() + "</p>"
					+ "Your account was Created<BR><BR>"
					+ "initial account balance is 0.00<BR><BR>"
					+ "<input type=\"hidden\" name=\"compID\" value=\""	+ result + "\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\"" + token + "\" />"
					+ "<input type=\"submit\" value=\"Submit\" /><BR><BR>"
					+ "</CENTER>" + "</form></body></html>");
			break;
		case -1:
			out.println(output
					+ "<html><head><title></title></head>"
					+ "<body bgcolor=\"#F8ECEE\">\n"
					+ "<form action=\"/PlayersSystem/RegistrationForm\" method=\"GET\">"
					+ "<CENTER>" + "This username is in use!<BR><BR>" + usName
					+ "<BR><BR>" + "<input type=\"submit\" value=\"Back\" />"
					+ "</CENTER>" + "</form></body></html>");
			break;
		case -2:
			out.println(output
					+ "<html><head><title></title></head>"
					+ "<body bgcolor=\"#F8ECEE\">\n"
					+ "<form action=\"/PlayersSystem/RegistrationForm\" method=\"GET\">"
					+ "<CENTER>" + "Sorry DataBase error! Try later.\n"
					+ "<BR><BR>"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</CENTER>" + "</form></body></html>");
			break;
		}
	}

}
