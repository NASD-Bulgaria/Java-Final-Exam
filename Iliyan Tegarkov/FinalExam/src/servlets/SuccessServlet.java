package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SuccessServlet
 */
@WebServlet("/SuccessServlet")
public class SuccessServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Withdraw or Deposit?");
		String token = request.getParameter("token");
		out.println("<form action=\"DepositServlet\" method=\"post\">"+
				"<br>Deposit:<br> <input type=\"text\" name=\"deposit\" />"+ 
				"<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>"+
				"<input type=\"submit\" value=\"Deposit\" />"+
				"</form>"+
				"<form action=\"WithdrawServlet\" method=\"post\">"+
				"<br>Withdraw:<br> <input type=\"text\" name=\"withdraw\" /> "+
				"<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>"+
				"<input type=\"submit\" value=\"Withdraw\" />"+
				"</form> "+
				
				"<form action=\"LogOutServlet\" method=\"post\">"+
				"<input type=\"hidden\" name=\"token\" value=\""+token+"\"/>"+
				"<input type=\"submit\" value=\"Logout\" />"+
				"</form>");
		
		
	}

}
