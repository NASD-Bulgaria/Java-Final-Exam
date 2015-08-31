package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import json.JSONObject;
import queries.Queries;
import responces.Status;

;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		Status status = new Status();
		String loginName = request.getParameter("loginName");
		String loginPass = request.getParameter("loginPass");
		String rePass = request.getParameter("rePass");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		if (!loginPass.equals(rePass) || loginName.equals("")
				|| loginPass.equals("") || firstName.equals("")
				|| lastName.equals("")) {
			status.setStatusFailed();
			JSONObject resp = new JSONObject(status);
			response.getWriter().print(resp.toString());
		} else {
			try {
				int balance = Integer.parseInt(request.getParameter("balance"));
				Queries.addPlayer(loginName, loginPass, firstName, lastName, balance);
			} catch (IllegalArgumentException e) {
				status.setStatusFailed();
				JSONObject resp = new JSONObject(status);
				response.getWriter().print(resp.toString());
			}
			status.setStatusOK();
			JSONObject resp = new JSONObject(status);
			response.getWriter().print(resp.toString());
		}

	}

}
