package depositewithdraw;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Player;
import query.Querys;

/**
 * Servlet implementation class DepositeWithdraw
 */
@WebServlet("/DepositeWithdraw")
public class DepositeWithdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepositeWithdraw() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setMaxInactiveInterval(60);
		PrintWriter out = response.getWriter();
		String token = (String) request.getSession().getAttribute("token");
		Object json = request.getSession().getAttribute("json");
		Querys quer = null;
		Player player = null;
		try {
			quer = new Querys();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		player = quer.findBalID(token);//gets the balance and the token of the current user
		request.getSession().setAttribute("token", token);
		request.getSession().setAttribute("json", json);
		quer.close();//close the connection to the database
		out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Deposite or Withdraw</title>"
				+ "</head><body><form method=\"post\" action=\"http://localhost:8080/Exam13.08/DepositeHandler\" >"
				+ "<p>Your balance is: </p>"
				+ player.getBalance()
				+ "<p>amount: </p><input name=\"amount\" type=\"text\" />"
				+ "<p><input type = \"radio\" name = \"choose\" value = \"1\">Deposite</input>"
				+ "<input type = \"radio\" name = \"choose\" value = \"2\">Withdraw</input></p>"
				+ "<p><input type = \"submit\" value = \"Update\" /></p>"
				+ "</p></form></body></html>");
	}//end method doPost
}//end class
