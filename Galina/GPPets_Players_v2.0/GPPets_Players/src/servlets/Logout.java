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

import model.Player;

import org.json.JSONObject;

import com.sun.media.jfxmedia.events.PlayerTimeListener;

import control.PlayerEJB;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PlayerEJB playerEJB;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		
		String token = req.getParameter("token");
		Player thePlayer = playerEJB.getPlayerByToken(token);
		int userID = thePlayer.getId();
		playerEJB.setTokenInDB(userID, "");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
		dispatcher.forward(req, res);
	}
}
