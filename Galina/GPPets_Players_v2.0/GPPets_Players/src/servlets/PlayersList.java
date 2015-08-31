package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Player;
import control.PlayerEJB;

/**
 * Servlet implementation class PlayersList
 */
@WebServlet("/players")
public class PlayersList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PlayerEJB player;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Player> playersList = player.playersList();
		req.setAttribute("players", playersList);
		RequestDispatcher dispatcher = getServletContext().
		getRequestDispatcher("/jsp/playersList.jsp");
		dispatcher.forward(req, res);	 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
