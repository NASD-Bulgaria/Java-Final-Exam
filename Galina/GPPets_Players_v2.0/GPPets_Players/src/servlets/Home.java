package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.Player;

import org.json.JSONException;
import org.json.JSONObject;

import control.PlayerEJB;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet implements HttpSessionListener {
	private static final long serialVersionUID = 1L;
	@EJB
	private PlayerEJB playerEJB;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		
		String token = "";
		try {
			JSONObject myJSON = (JSONObject) req.getAttribute("myJSON");
			if(myJSON!=null){
				token = (String) myJSON.get("token");
			}
		} catch (JSONException e) {
			System.out.println("No myJSON");
		} 
		
		if(token!="" && token!=null){
			dispatcher = getServletContext().getRequestDispatcher("/jsp/home.jsp");
			dispatcher.forward(req, res);
		} else {
			dispatcher = getServletContext().getRequestDispatcher("/login");
			dispatcher.forward(req, res);
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		this.doGet(req, res);
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		String token = (String) session.getAttribute("token");
		Player thePlayer = playerEJB.getPlayerByToken(token);
		int userID = thePlayer.getId();
		playerEJB.setTokenInDB(userID, "");
		
	}
}
