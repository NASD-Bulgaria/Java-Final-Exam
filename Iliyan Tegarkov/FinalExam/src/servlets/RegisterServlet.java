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
import model.Player;
import model.Player_profile;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String loginName = request.getParameter("loginName");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		try {
			String balance = request.getParameter("balance");

			boolean valid = PlayerRequests.existingLoginName(loginName);
			if (!valid) {
				out.println("Username allready exists!");
			}
			if (valid && firstName.trim() != null && firstName.trim() != "" && lastName.trim() != null && lastName.trim() != ""
					&& !password.trim().isEmpty() && !firstName.trim().isEmpty() && !loginName.trim().isEmpty() && !lastName.trim().isEmpty()
					&& loginName.trim() != null && password.trim() != null && loginName.trim() != "" && password.trim() != "" && !balance.trim().isEmpty()
					&& Double.parseDouble(balance) >= 0) {
				String hashedPassword = PlayerRequests.passwordHashin(loginName, password);
				Player player = new Player();
				player.setBalance(Double.parseDouble(balance));
				player.setLogin_name(loginName);
				player.setLogin_password(hashedPassword);
				Player_profile profile = new Player_profile(player, firstName, lastName);
				PlayerRequests.createNewPlayer(player, profile);
				JSONObject json = new JSONObject().put("success", "ok");

				out.println("<p>" + json.toString() + "</p>");
				out.println("<form method=\"post\" action=\"login.jsp\">");
				out.println("<input type=\"submit\" value=\"Next\"/>");
				out.println("</form>");
			} else {
				out.println("The fileds cannot be empty or the input data is wrong! ");
			}
		} catch (NumberFormatException e) {
			out.println("Please enter a valid number for balance!");
		}
	}

}
