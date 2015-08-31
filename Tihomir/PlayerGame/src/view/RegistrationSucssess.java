package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;


import controller.PlayerQueries;
import model.Player;
import validation.ValidationData;

/**
 * Servlet implementation class RegistrationSucssess
 */
@WebServlet("/RegistrationSucssess")
public class RegistrationSucssess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationSucssess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("IndexServlet");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("login_name");
		String password = request.getParameter("login_password");
		String first_name = request.getParameter("firstName");
		String last_name = request.getParameter("lastName");

		boolean isValid = ValidationData.isNameAvailable(username);
		if (isValid && username != null && username != "" && !username.isEmpty() && !password.isEmpty()
				&& password != null && password != "" && !first_name.isEmpty() && !last_name.isEmpty()
				&& first_name != null && first_name != "" && last_name != null && last_name != "") {

			HashFunction hash = Hashing.sha1();
			String salt = hash.newHasher().putString(username, Charsets.UTF_8).hash().toString();
			String pass = hash.newHasher().putString(password, Charsets.UTF_8).hash().toString();
			HashCode hs = hash.newHasher().putString(pass, Charsets.UTF_8).putString(salt, Charsets.UTF_8).hash();
			String result = hs.toString();
			Player newPlayer = PlayerQueries.registerNewPlayer(username, result, salt);
			PlayerQueries.addPlayerProfile(first_name, last_name, newPlayer);
			JSONObject json = new JSONObject().put("status", "OK");
			out.println("<p>" + json.toString() + "</p>");
			out.println("<a href=\"IndexServlet\">Back");
			out.println("</a>");
		} else {
			JSONObject json = new JSONObject().put("status", "Not OK");
			out.println("<p>" + json.toString() + "</p>");

			out.println("<a href=\"RegServlet\">Back");
			out.println("</a>");
		}

	}

}
