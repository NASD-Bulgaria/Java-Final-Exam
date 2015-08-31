package handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import models.Player;
import query.Querys;

/**
 * Servlet implementation class DepositeHandler
 */
@WebServlet("/DepositeHandler")
public class DepositeHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DepositeHandler() {
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
		Querys quer = null;
		Player player = null;
		try {
			quer = new Querys();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//get the info from the previews page
		String token = (String) request.getSession().getAttribute("token");
		String strChoose = request.getParameter("choose");
		int choose = Integer.valueOf(strChoose);
		String strAmount = request.getParameter("amount");
		Object json = request.getSession().getAttribute("json");
		request.getSession().setAttribute("json", json);
		double amount = 0;
		try {
			amount = Double.valueOf(strAmount);//checks if the input is of type double
		} catch (Exception e) {
			response.sendRedirect("http://localhost:8080/Exam13.08/DepositeWithdraw");
		}
		player = quer.findBalID(token);
		
		if (choose == 1) {
			if (amount > 0) {
				double result = player.getBalance() + amount;
				int res = quer.updateBal(player.getId(), result);
				response.sendRedirect("http://localhost:8080/Exam13.08/DepositeWithdraw");
			} //end if
		} else if (choose == 2) {
			if (amount < player.getBalance()) {
				double result = player.getBalance() - amount;
				int res = quer.updateBal(player.getId(), result);
				quer.close();
				response.sendRedirect("http://localhost:8080/Exam13.08/DepositeWithdraw");
			}//end if
		}//end else if
	}//end method doPost
}//end of the class
