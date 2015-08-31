package servlets;

import controller.PlayerQueries;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author zahra
 */
public class DepositServlet extends HttpServlet {

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
        
        PrintWriter out = response.getWriter();
        String username = request.getParameter("token");

        try {

            double amount = Double.valueOf(request.getParameter("balance"));
            PlayerQueries.deposit(username, amount);
            JSONObject obj = new JSONObject().put("Successfull operation!", "ok");
            
            out.println("<p>" + obj.toString() + "</p>"
                    + "<form method=\"post\" action=\"MenuServlet\" >"
                    + "<input type=\"hidden\" name=\"token\" value=\"" + username + "\"/>"
                    + "<input type=\"Submit\" value=\"MenuServlet\"/> </form>");

        } catch (Exception e) {

            out.println("<p>Invalid input</p>"
                    + "<form method=\"post\" action=\"MenuServlet\" >"
                    + "<input type=\"hidden\" name=\"token\" value=\"" + username + "\"/>"
                    + "<input type=\"Submit\" value=\"MenuServlet\"/> </form>");
        }
    }

}
