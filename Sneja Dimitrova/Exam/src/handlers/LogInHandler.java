package handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Player;

import org.json.JSONObject;

import query.Querys;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * Servlet implementation class LogInHandler
 */
@WebServlet("/LogInHandler")
public class LogInHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setMaxInactiveInterval(600);
		Querys quer = null;
		Player player = new Player();
		List<String> usernames = new ArrayList<String>();
		try {
			quer = new Querys();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//get the info from the previews page
		String username = request.getParameter("username");
		String tempPass = request.getParameter("password");
		//convert the password to hash
		String password = "";
		HashFunction hashFunc = Hashing.sha256();
		HashCode hashCode = hashFunc.newHasher()
				.putString(tempPass, Charsets.UTF_8).hash();
		password = hashCode.toString();
		//check is the username exist
		usernames = quer.allUserNames();
		boolean flag = false;
		for (String string : usernames) {
			if (username.equals(string)) {
				//if exist check the passwords for match
				player = quer.findPassToken(username);
				request.getSession().setAttribute("token", player.getToken());
				request.getSession().setAttribute("username", username);
//				player = quer.findBalID(token);
//				JSONObject json = new JSONObject(player);
//				request.getSession().setAttribute("json", json);
				quer.close();
				if (password.equals(player.getPassword())) {
					//redirect to the next page
					response.sendRedirect("http://localhost:8080/Exam13.08/DepositeWithdraw");
				} else {
					//redirect to the previews page if the password not match
					response.sendRedirect("http://localhost:8080/Exam13.08/Login");
				}
				flag = true;//the flag for exist of the user name turns true
			}
		}
		if (flag!=true) {
			//redirect to the previews page when the username doesn`t exist
			response.sendRedirect("http://localhost:8080/Exam13.08/Login");
		}
	}//end method doPost
}//end class 
