package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import json.JSONObject;
import queries.Queries;
import responces.Status;
import responces.Token;
import model.Player;
/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Status status = new Status();
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("loginPass");
		int playerId = Queries.findPlayerByLoginName(loginName);
		if(playerId == -1){
			status.setStatusFailed();
			JSONObject resp = new JSONObject(status);
			response.getWriter().print(resp);
			return;
		}
		Player player = Queries.findPlayerById(playerId);
		if(!player.getLoginPassword().equals(password)){
			status.setStatusFailed();
			JSONObject resp = new JSONObject(status);
			response.getWriter().print(resp);
		} else {
			HttpSession session = request.getSession();
			synchronized (session) {
				HashFunction hf = Hashing.sha1();
				HashCode hc = hf.newHasher().putString(new Date().toString()).hash();
				Token token = new Token(hc.toString());
				token.setUserId(playerId);
				session.setAttribute("Token", token);
				session.setMaxInactiveInterval(600);
				response.getWriter().print(token);
			}
			status.setStatusOK();
			JSONObject resp = new JSONObject(status);
			response.getWriter().print(resp);
		}
	}

}
