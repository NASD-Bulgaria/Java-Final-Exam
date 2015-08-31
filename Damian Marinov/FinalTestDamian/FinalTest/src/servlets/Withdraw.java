package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import json.JSONObject;
import model.Player;
import static queries.Queries.withdraw;
import queries.Queries;
import responces.Status;
import responces.Token;

/**
 * Servlet implementation class Withdraw
 */
@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
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
		HttpSession session = request.getSession();
		Token tok =null;
		Status status = new Status();
		synchronized (session) {
			tok = (Token) session.getAttribute("Token");
		}
		if(tok == null){
			response.sendRedirect("/FinalTest/LogInTest");
		} else {
			session.setMaxInactiveInterval(600);
			int value = 0;
			try{
				 value = Integer.parseInt(request.getParameter("amount"));
			} catch(NumberFormatException e){
				status.setStatusFailed();
				JSONObject resp = new JSONObject(status);
				response.getWriter().print(resp.toString());
				return;
			}
			int id = tok.getUserId(tok.getHash());
			Player p = Queries.findPlayerById(id);
			if(p.getBalance() - value<0){
				status.setStatusFailed();
				JSONObject resp = new JSONObject(status);
				response.getWriter().print(resp.toString());
			}else{
				withdraw(id, value);
				status.setStatusOK();
				JSONObject resp = new JSONObject(status);
				response.getWriter().print(resp.toString());
			}	
		}
	}
	
}

