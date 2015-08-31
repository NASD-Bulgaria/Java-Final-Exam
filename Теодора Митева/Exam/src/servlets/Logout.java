package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import request.PlayerRequest;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// public void doGet(HttpServletRequest req, HttpServletResponse res)
	// throws ServletException, IOException {
	// res.setContentType("text/html");
	// PrintWriter out = res.getWriter();
	//
	// // Get the current session object, create one if necessary
	// HttpSession session = req.getSession(true);
	//
	// // Invalidate the session if it's more than a day old or has been
	// // inactive for more than an hour.
	// if (!session.isNew()) { // skip new sessions
	// Date minuteAgo = new Date(System.currentTimeMillis() - (60 * 1000)/2);
	// Date created = new Date(session.getCreationTime());
	//
	// if (created.before(minuteAgo)) {
	// session.invalidate();
	// session.setAttribute("token", null);
	// session = req.getSession(true); // get a new session
	// }
	// }
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String loginName = request.getParameter("loginName");
		PlayerRequest.setTokenToNull(loginName);
		out.println("Have a nice day!");

		out.println("<!DOCTYPE html PUBLIC "
				+ " // W3C//DTD HTML 4.01"
				+ "	// Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\""
				+ "<html>"
						+ "<head>"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
						+ "<title>Transactions</title>" 
					+ "</head>" 
					+ "<body>"
	
						+ "<form method=\"POST\" action = \"index.jsp\">"
							+ "<input type=\"submit\" name=\"login\" value=\"Login\" />"
							+ "</form>" 
					+ "</body>" 
				+ "</html>");
	}

}
