package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import entities.Player;
import entities.PlayerProfile;
import queries.JPAQueries;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JPAQueries query;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		query = new JPAQueries();
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
								+ "<BODY>"
								+ "<FORM ACTION=\"http://localhost:8080/PlayersProject/RegisterServlet\" METHOD=post>"
								+ "<TABLE>"
								+ "<TR><TD COLSPAN=2>"
								+ "<P ALIGN=CENTER>"
								+ "Please enter your Name, User,<br>"
								+ " Password to register."
								+ "</TD></TR>"
								+ "<TR><TD>"
								+ "<P ALIGN=RIGHT><B>First Name:</B>"
								+ "</TD>"
								+ "<TD>"
								+ "<P><INPUT TYPE=TEXT NAME=\"firstName\" VALUE=\"\" SIZE=15 required>"
								+ "</TD></TR> "
								+ "<TR><TD>"
								+ "<P ALIGN=RIGHT><B>LastName:</B>"
								+ "</TD>"
								+ "<TD>"
								+ "<P><INPUT TYPE=TEXT NAME=\"lastName\" VALUE=\"\" SIZE=15 required>"
								+ "</TD></TR> "
								+ "<TR><TD>"
								+ "<P ALIGN=RIGHT><B>User:</B>"
								+ "</TD>"
								+ "<TD>"
								+ "<P><INPUT TYPE=TEXT NAME=\"loginName\" VALUE=\"\" SIZE=15 required>"
								+ "</TD></TR>"
								+ "<TR><TD>"
								+ "<P ALIGN=RIGHT><B>Password:</B>"
								+ "</TD>"
								+ "<TD>"
								+ "<P><INPUT TYPE=PASSWORD NAME=\"loginPassword\" VALUE=\"\" SIZE=15 required>"
								+ "</TD></TR> " + "<TR><TD COLSPAN=2>    "
								+ "<INPUT TYPE=SUBMIT VALUE=\"  OK  \">    "
								+ "</TD></TR>" + "</TABLE>" + "</FORM>  "
								+ "</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String account = request.getParameter("loginName");
		String password = request.getParameter("loginPassword");

		if (query.isLoginNameExist(account)) {
			JSONObject jsonObject = new JSONObject().put("status", "notok");
			response.getWriter()
					.println(
							"<HTML>"
									+ "<TITLE>Error</TITLE>"
									+ "<BODY>"
									+ "User name exist! <br>  "
									+ jsonObject.toString()
									+ "<br><a href=\"http://localhost:8080/PlayersProject/RegisterServlet\">Registration</a>"
									+ "</BODY></HTML>");
		} else {
			JSONObject jsonObject = new JSONObject().put("status", "ok");
			Player company = new Player();
			company.setLoginName(account);
			company.setLoginPassword(password);
			PlayerProfile compProfile = new PlayerProfile();
			compProfile.setFirstName(firstName);
			compProfile.setLastName(lastName);
			query.addCompany(company, compProfile);
			response.getWriter()
					.println(
							"<HTML>"
									+ "<TITLE>Error</TITLE>"
									+ "<BODY>"
									+ "Success Register!<br>  "
									+ jsonObject.toString()
									+ "<br><a href=\"http://localhost:8080/PlayersProject\">LogIN</a>"
									+ "</BODY></HTML>");
		}

	}

}
