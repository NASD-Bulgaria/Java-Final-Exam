package handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
 * Servlet implementation class SignUpHandler
 */
@WebServlet("/SignUpHandler")
public class SignUpHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpHandler() {
		super();
		// TODO Auto-generated constructor stub
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
		Querys quer = null;
		Player player = null;
		try {
			quer = new Querys();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//get the information from the previews page
		String username = request.getParameter("username");
		String tempPass = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String strBalance = request.getParameter("balance");
		double balance = 0;
		try {
			balance = Double.valueOf(strBalance);//check if the input is of type double
		} catch (Exception e) {
			response.sendRedirect("http://localhost:8080/Exam13.08/SignUp");
		}
		//convert the input password in hash
		String password = "";
		HashFunction hashFunc = Hashing.sha256();
		HashCode hashCode = hashFunc.newHasher()
				.putString(tempPass, Charsets.UTF_8).hash();
		password = hashCode.toString();
		
		//make the token in hash
		String token = "";
		Random rand = new Random();
		int random = rand.nextInt();
		String tempToken = String.valueOf(random);
		HashFunction hash = Hashing.sha1();
		HashCode hashCod = hash.newHasher()
				.putString(tempToken, Charsets.UTF_8).hash();
		token = hashCod.toString();
		int resultPlayerInfo = 0; 
		int resultUserInfo = 0;
		//check if the input username is available
		List<String> list = new ArrayList<String>();
		boolean flag = true; 
		list = quer.allUserNames();
		for (String string : list) {
			if (username.equals(string)) {
				flag = false;
			}
		}
		//if the username is available add the info to the database
		if (flag) {
			resultUserInfo = quer.createPlayer(username, password, token,
					balance);
			player = quer.findBalID(token);
			resultPlayerInfo = quer.creatPlayerProfile(firstName, lastName,
					player.getId());
			request.getSession().setAttribute("token", token);
			request.getSession().setAttribute("username", username);
			player = quer.findBalID(token);
			JSONObject json = new JSONObject(player);
			request.getSession().setAttribute("json", json);
			quer.close();
			if (resultPlayerInfo == 1 && resultUserInfo == 1) {
				//if the adding is successful redirect to the next page
				response.sendRedirect("http://localhost:8080/Exam13.08/DepositeWithdraw");
			} else {
				//if the adding was not successful redirect to the previews page
				response.sendRedirect("http://localhost:8080/Exam13.08/SignUp");
			}
		} else {
			//if username is not available redirect to the previews page
			response.sendRedirect("http://localhost:8080/Exam13.08/SignUp");
		}
	}//end method doPost
}//end class
