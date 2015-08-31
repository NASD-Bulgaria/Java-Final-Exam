package servlets;

import static queries.Queries.deposit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import json.JSONObject;
import responces.Status;
import responces.Token;

/**
 * Servlet implementation class deposit
 */
@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Deposit() {
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
		HttpSession session = request.getSession();
		Token tok = null;
		synchronized (session) {
			tok = (Token) session.getAttribute("Token");
		}
		if (tok == null) {
			response.sendRedirect("/FinalTest/LogInTest");
		} else {
			session.setMaxInactiveInterval(600);
			int value = 0;
			try {
				value = Integer.parseInt(request.getParameter("amount"));
			} catch (NumberFormatException e) {
				Status status = new Status();
				status.setStatusFailed();
				JSONObject resp = new JSONObject(status);
				response.getWriter().print(resp.toString());
				return;
			}
			int id = tok.getUserId(tok.getHash());
			deposit(id, value);
			Status status = new Status();
			status.setStatusOK();
			JSONObject resp = new JSONObject(status);
			response.getWriter().print(resp.toString());
		}
	}
}
