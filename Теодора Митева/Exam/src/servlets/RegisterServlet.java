package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import request.PlayerRequest;
import entity.Player;
import entity.PlayerProfile;

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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		response.setContentType("text/html");
		try {
			String loginName = request.getParameter("loginName");
			String loginPassword = request.getParameter("loginPassword");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String balance = request.getParameter("balance");

			if (loginName != null && loginPassword != null
					&& Double.valueOf(balance) != null
					&& Double.valueOf(balance) >= 0 && firstName != null
					&& firstName != "" && lastName != null && lastName != ""
					&& !loginName.trim().isEmpty()
					&& !loginPassword.trim().isEmpty() && !loginName.isEmpty()
					&& !loginPassword.isEmpty() && !balance.isEmpty()
					&& firstName.trim() != "" && !firstName.isEmpty()
					&& lastName.trim() != "" && !lastName.isEmpty()) {

				if (!PlayerRequest.existingLoginName(loginName)) {
					String hashingPassword = PlayerRequest.hashing(
							loginPassword, loginName);

					Player player = new Player();
					player.setLoginName(loginName);
					player.setLoginPassword(hashingPassword);
					player.setBalance(Double.valueOf(balance));

					PlayerProfile playerProfile = new PlayerProfile(player,
							firstName, lastName);
					PlayerRequest.registerNewPlayer(player, playerProfile);
					JSONObject json = new JSONObject();
					json.put("loginName", loginName);
					json.put("loginPassword", loginPassword);
					out.println(json);

					out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
							+ "<html>"
							+ "<head>"
							+ "	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
							+ "<title>Registration</title>"
							+ "</head>"
							+ "<body>"
							+ "<form action=\"LoginServlet\" method=\"post\">"
							+ "		<br>Login name: <input type=\"text\" name=\"loginName\" /> "
							+ "		<br>Password: <input type=\"password\" name=\"loginPassword\" />"
							+ "		<br><input type=\"submit\" />"

							+ "		<a href=\"http://localhost:8080/Exam29.08.2015/register.jsp\">Register here</a>\""
							+ "	</form>" + "	</body>" + "</html>");
				} else {
					out.println("Login name is taken!");
				}
			} else {
				out.print("Wrong information!");
			}
		} catch (NumberFormatException e) {
			out.println("Enter a valid balance number!");
		}
	}

}
