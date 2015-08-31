package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.Queries;

/**
 * Servlet implementation class BankServlet
 */
@WebServlet("/PlayerServlet")
public class PlayerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/PlayersSystem/MainServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getSession().setMaxInactiveInterval(10);
		
		PrintWriter out = response.getWriter();
		
		String tokenRequest = request.getParameter("token");
		int id = Integer.valueOf(request.getParameter("compID"));
		String tokenDB;
		
		request.getSession().setAttribute("tokenSesion", tokenRequest);
		synchronized (this) {
			tokenDB = Queries.getTokenByID(id);
		}

		String output = " <!doctype html public \"-//w3c//dtd html 5.0 transitional//en\">\n";
		
		if(tokenRequest.equals(tokenDB) && (request.getSession().getAttribute("tokenSesion"))!=null){
			out.println(output + "<html><head><title></title></head>"
					+ "<body bgcolor=\"#f0f0f0\">\n"
					+ "<form action=\"/PlayersSystem/CalculateDeposit\" method=\"POST\">"
					+ "<CENTER>"
					+ "Please enter the sum:<BR>"
					+ "<INPUT TYPE=\"TEXT\" NAME=\"sum\"><BR>"
					+ "<BR><input type=\"submit\" value=\"Deposit\" />"
					+ "<input type=\"hidden\" name=\"compID\" value=\"" + id + "\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\"" + tokenRequest + "\" />"
					+ "</CENTER>" + "</form>"
					+ "<BR><form action=\"/PlayersSystem/CalculateWithdraw\" method=\"POST\">"
					+ "<CENTER>"
					+ "Please enter the sum:<BR>"
					+ "<INPUT TYPE=\"TEXT\" NAME=\"sum2\"><BR>"
					+ "<input type=\"submit\" value=\"Withdraw\" />"
					+ "<input type=\"hidden\" name=\"compID\" value=\""+ id + "\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\""+ tokenRequest + "\" />"
					+ "</CENTER>" + "</form>" 
					+ "<BR><form action=\"/PlayersSystem/GetBalance\" method=\"POST\">"
					+ "<CENTER>"
					+ "<input type=\"submit\" value=\"See Balance\" />"
					+ "<input type=\"hidden\" name=\"compID\" value=\""+ id +"\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\""+ tokenRequest +"\" />"
					+ "</CENTER>" + "</form>" 
					+ "</form></body></html>");
		}else{
			Queries.setToken(id, "");
			response.sendRedirect("/PlayersSystem/MainServlet");
		}
		
	}

}
