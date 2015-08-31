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
import validation.ValidationData;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isAvailable = ValidationData.isNameAvailable(username);
		if(isAvailable){
			HashFunction hash = Hashing.sha256();
			String salt = hash.newHasher().putString(username, Charsets.UTF_8).hash().toString();
			HashCode code = (HashCode) hash.newHasher().putString(password, Charsets.UTF_8).hash();
			HashCode code2 = (HashCode) hash.newHasher().putString(username, Charsets.UTF_8).hash();
			String finalpass = code.toString();
			String finalusername = code2.toString();
			HashCode code3 = (HashCode) hash.newHasher()
					.putString(finalpass, Charsets.UTF_8)
					.putString(finalusername, Charsets.UTF_8).hash();

			PlayerQueries.registerNewPlayer(username, password, salt);
			JSONObject object = new JSONObject().put("Success!", "ok");
			out.println("<p>"+object.toString()+"</p>");
			out.println("<a href=\"http://localhost:8080/PlayerGame/index.jsp\">");
			out.println("Go back to login page");
			out.println("</a>");
		}else{
			out.println("<p>User is already registered</p>");
			out.println("<a href=\"http://localhost:8080/PlayerGame/registration.jsp\">");
			out.println("Go back to register page");
			out.println("</a>");
		}
	}

}
