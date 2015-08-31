package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import control.PlayerEJB;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PlayerEJB playerEJB;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
		dispatcher.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		res.setContentType("text/thml");
		RequestDispatcher dispatcher;
		
		String loginName = req.getParameter("loginName");
		String password = req.getParameter("password");
		
		JSONObject myJSON = new JSONObject();
		HttpSession session = null;
		
		if( playerEJB.isLoginInfoCorrect(loginName, password) ){
			
			session = req.getSession();
			Hash newHash = new Hash(loginName, password);
			String token = newHash.hash();
			
			playerEJB.setTokenInDB(playerEJB.getPlayer(loginName).getId(), token);
			double balance = playerEJB.getPlayer(loginName).getBalance();
			
			synchronized (session) {
				session.setAttribute("token", token);
				session.setMaxInactiveInterval(600);
			}
			
			myJSON.put("status", "ok");
			myJSON.put("token", token);
			myJSON.put("balance", balance);
			
			req.setAttribute("myJSON", myJSON);
						
			dispatcher = getServletContext().getRequestDispatcher("/home");
			dispatcher.forward(req, res);
			
		} else {
			myJSON.put("status", "login failed");
			req.setAttribute("myJSON", myJSON);
			
			dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(req, res);
		}

	}
}
