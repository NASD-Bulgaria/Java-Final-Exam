package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import logic.GameQueries;
import model.Player;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServ")
public class HomeServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GameQueries query;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServ() {
		super();
		query = new GameQueries();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String account = request.getParameter("loginName");
		String password = request.getParameter("loginPassword");
		Player player = query.isLoginSuccess(account, password);

		if (account != null && password != null && player != null) {
			Random r = new Random();
			int rnd = r.nextInt(100000);

			HashFunction hf = Hashing.sha1();
			HashCode hc = hf.newHasher().putInt(rnd)
					.putString(new Date().toString(), Charsets.UTF_8).hash();

			HttpSession session = request.getSession();
			synchronized (session) {
				session.setAttribute("token", hc.toString());
				session.setMaxInactiveInterval(600);
				}
			

			query.addToken(player.getId(), hc.toString());

			JSONObject jsonObject = new JSONObject().put("status", "Profile Exists");
			jsonObject.put("token", hc.toString());
			response.getWriter().println(jsonObject.toString());

			response.getWriter()
					.println(
							"<HTML>"
									+ "<TITLE>Home</TITLE>"
									+ "<BODY>"
									+ "<FORM ACTION=\"http://localhost:8080/OnlineGameSystemProject/DepositServ\" METHOD=post>"
									+ "Deposit Balance <br>"
									+ "<INPUT TYPE=\"hidden\" NAME=\"token\" VALUE=\""
									+ hc.toString()
									+ "\">"
									+ "<INPUT TYPE=TEXT NAME=\"amount\" VALUE=\"\" SIZE=25 required>"
									+ "<INPUT TYPE=SUBMIT VALUE=\"deposit\">    "
									+ "</FORM>  "
									+ "<FORM ACTION=\"http://localhost:8080/OnlineGameSystemProject/WithdrawServ\" METHOD=post>"
									+ " Withdraw Balance <br>"
									+ "<INPUT TYPE=\"hidden\" NAME=\"token\" VALUE=\""
									+ hc.toString()
									+ "\">"
									+ "<INPUT TYPE=TEXT NAME=\"amount\" VALUE=\"\" SIZE=25 required>"
									+ "<INPUT TYPE=SUBMIT VALUE=\"withdraw\">    "
									+ "</FORM>  " + "</BODY></HTML>");
		} else {
			JSONObject jsonObject = new JSONObject().put("status", "Fail");
			response.getWriter()
					.println(
							"<HTML>"
									+ "<TITLE>Error</TITLE>"
									+ "<BODY>"
									+ "Login failed! <br>  "
									+ jsonObject.toString()
									+ "<br><a href=\"http://localhost:8080/OnlineGameSystemProject/RegServ\">Registration</a>"
									+ "<br><a href=\"http://localhost:8080/OnlineGameSystemProject\">Login</a>"
									+ "</BODY></HTML>");
		}

	}

}
