package servlets;

import controller.PlayerQueries;
import entityClasses.Player;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zahra
 */
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("token");
        PrintWriter out = response.getWriter();
       
        Player player = PlayerQueries.getPlayerByName(username);

        out.println("<h3> The current balance is : " + player.getBalance() + "</h3>"
                + "<h3> Please enter the amount you want to deposit or withdraw: </h3>"
                + "<input type=\"text\" name=\"balance\"/>");

        out.println("<form method=\"post\" action=\"DepositServlet\" >"
                + "<input type=\"hidden\" name=\"token\" value=\"" + username + "\"/>"
                + "<input type=\"Submit\" value=\"Deposit\"/> </form>");

        out.println("<form method=\"post\" action=\"WithdrawServlet\" >"
                + "<input type=\"hidden\" name=\"token\" value=\"" + username + "\"/>"
                + "<input type=\"Submit\" value=\"Withdraw\"/> </form>");

    }
}
