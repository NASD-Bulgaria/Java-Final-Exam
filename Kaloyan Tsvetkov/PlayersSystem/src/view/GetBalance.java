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
 * Servlet implementation class GetBalance
 */
@WebServlet("/GetBalance")
public class GetBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBalance() {
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
		PrintWriter out = response.getWriter();
		String output = " <!doctype html public \"-//w3c//dtd html 5.0 transitional//en\">\n";

		String tokenRequest = request.getParameter("token");
		int id = Integer.valueOf(request.getParameter("compID"));
		String tokenDB = "";
				
		synchronized (tokenDB) {
			tokenDB = Queries.getTokenByID(id);
		}

		if (tokenRequest.equals(tokenDB) && (request.getSession().getAttribute("tokenSesion"))!=null) {
			
			double result;
			synchronized (this) {
				result = Queries.getBalanceByID(id);
			}			
			out.println(output
					+ "<html><head><title>Succes Registration!</title></head>"
					+ "<body bgcolor=\"#f0f0f0\"><BR><BR>"
					+ "<form action=\"/PlayersSystem/PlayerServlet\" method=\"POST\">"
					+ "<CENTER>"
					+ "Your account balance is: <BR>" + result + "<BR>"
					+ "<input type=\"hidden\" name=\"compID\" value=\""+ id +"\" />"
					+ "<input type=\"hidden\" name=\"token\" value=\""+ tokenRequest +"\" />"
					+ "<input type=\"submit\" value=\"Back\" /><BR><BR>"
					+ "</CENTER>" + "</form></body></html>");
		}else{
			Queries.setToken(id, "");
			response.sendRedirect("/PlayersSystem/MainServlet");
		}
	}

}
