package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import logic.GameQueries;
import model.Player;
import model.PlayerProfile;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegServ")
public class RegServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GameQueries query;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegServ() {
		super();
		query = new GameQueries();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.getWriter()
				.println(
						"<HTML>"
								+ "<TITLE>Register</TITLE>"
								+ "<BODY BGCOLOR=\"#FDF5E6\">"
								+ "<FORM ACTION=\"http://localhost:8080/OnlineGameSystemProject/RegServ\" method=POST>"
								+ "<TABLE>"
								+ "<TR><TD COLSPAN=2>"
								+ "<P ALIGN=CENTER>"
								+ "Please enter correct your Name, Username and Password."
								+ "</TD></TR>"
								+ "<TR><TD>"
								+ "<P ALIGN=RIGHT><B>First Name:</B>"
								+ "</TD>"
								+ "<TD>"
								+ "<P><INPUT TYPE=TEXT NAME=\"firstName\" VALUE=\"\" SIZE=25 required>"
								+ "</TD></TR> "
								+ "<TR><TD>"
								+ "<P ALIGN=RIGHT><B>LastName:</B>"
								+ "</TD>"
								+ "<TD>"
								+ "<P><INPUT TYPE=TEXT NAME=\"lastName\" VALUE=\"\" SIZE=25 required>"
								+ "</TD></TR> "
								+ "<TR><TD>"
								+ "<P ALIGN=RIGHT><B>User:</B>"
								+ "</TD>"
								+ "<TD>"
								+ "<P><INPUT TYPE=TEXT NAME=\"loginName\" VALUE=\"\" SIZE=25 required>"
								+ "</TD></TR>"
								+ "<TR><TD>"
								+ "<P ALIGN=RIGHT><B>Password:</B>"
								+ "</TD>"
								+ "<TD>"
								+ "<P><INPUT TYPE=PASSWORD NAME=\"loginPassword\" VALUE=\"\" SIZE=25 required>"
								+ "</TD></TR> " + "<TR><TD COLSPAN=2>    "
								+ "<INPUT TYPE=SUBMIT VALUE=\"  OK  \">    "
								+ "</TD></TR>" + "</TABLE>" + "</FORM>  "
								+ "</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("indexlogin.html");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String account = request.getParameter("loginName");
		String password = request.getParameter("loginPassword");

		if (query.isUserExist(account)) {
			JSONObject jsonObject = new JSONObject().put("status", "not ok");
			response.getWriter()
					.println(
							"<HTML>"
									+ "<TITLE>Error</TITLE>"
									+ "<BODY>"
									+ "User name exist! <br>  "
									+ jsonObject.toString()
									+ "<br><a href=\"http://localhost:8080/OnlineGameSystemProject/RegServ\">Registration</a>"
									+ "</BODY></HTML>");
		} else {
			JSONObject jsonObject = new JSONObject().put("status", "ok");
			Player player = new Player();
			player.setLoginName(account);
			player.setLoginPassword(password);
			PlayerProfile plProfile = new PlayerProfile();
			plProfile.setFirstName(firstName);
			plProfile.setLastName(lastName);
			query.addPlayer(player, plProfile);
			response.getWriter()
					.println(
							"<HTML>"
									+ "<TITLE>Error</TITLE>"
									+ "<BODY>"
									+ "Success Register!<br>  "
									+ jsonObject.toString()
									+ "<br><a href=\"http://localhost:8080/OnlineGameSystemProject\">Login</a>"
									+ "</BODY></HTML>");
		}

	}

}
