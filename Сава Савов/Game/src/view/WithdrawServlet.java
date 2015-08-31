package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import control.PlayerQueries;
import validation.DataValidation;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("user");
		try {
			double amount = Double.valueOf(request.getParameter("balance"));
			boolean temp = DataValidation.isEnough(userName, amount);
			boolean correctInput = DataValidation.negativeInput(amount);
			if(correctInput){
			if (temp) {
				PlayerQueries.withdrawBalance(userName, amount);
				JSONObject obj = new JSONObject().put("status", "ok");
				out.println("<p>" + obj.toString() + "</p>");
				out.println("<form method=\"post\" action=\"UserPanel\" >");
				out.println("<input type=\"hidden\" name=\"user\" value=\"" + userName + "\"/>");
				out.println("<input type=\"Submit\" value=\"UserPanel\"/>");
				out.println("</form>");
			} else {
				out.println("<p>The sum that you've entered is more than you have in your balance</p>");
				out.println("<form method=\"post\" action=\"UserPanel\" >");
				out.println("<input type=\"hidden\" name=\"user\" value=\"" + userName + "\"/>");
				out.println("<input type=\"Submit\" value=\"UserPanel\"/>");
				out.println("</form>");
			}
		}else{
			out.println("<p>You can't enter negative amount</p>");
			out.println("<form method=\"post\" action=\"UserPanel\" >");
			out.println("<input type=\"hidden\" name=\"user\" value=\""+userName+"\"/>");
			out.println("<input type=\"Submit\" value=\"UserPanel\"/>");
			out.println("</form>");
		}
		} catch (Exception e) {
			out.println("<p>Invalid input</p>");
			out.println("<form method=\"post\" action=\"UserPanel\" >");
			out.println("<input type=\"hidden\" name=\"user\" value=\"" + userName + "\"/>");
			out.println("<input type=\"Submit\" value=\"UserPanel\"/>");
			out.println("</form>");
		}
	}

}
