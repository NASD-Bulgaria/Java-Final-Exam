package servlets;

import controller.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author zahra
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isRegistered = Validation.isRegistered(userName, password);

        if (isRegistered) {

            HttpSession session = request.getSession(true);
            synchronized (session) {
                session.setAttribute("token", userName);
                session.setMaxInactiveInterval(600);
            }

            JSONObject object = new JSONObject();
            object.put("Successful login", "ok");

            out.println("<p>" + object.toString() + "</p>"
                    + "<form method=\"post\" action=\"MenuServlet\">"
                    + "<input type=\"hidden\" name=\"token\" value=\"" + userName + "\"/>"
                    + "Click the button to go to Menu <input type=\"Submit\" value=\"MenuServlet\"/> </form>");

        } else {
            out.println("<p>Invalid username or password!</p>"
                    + " <h4> Click <a href=\"http://localhost:8080/PlayersProject/index.jsp\">here </a> to try again <h4/>");
        }
    }

}
