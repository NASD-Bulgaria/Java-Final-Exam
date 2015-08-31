package servlets;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import controller.PlayerQueries;
import controller.Validation;
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
public class MainServlet extends HttpServlet {

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

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isExisting = Validation.isExisting(userName);

        if (isExisting == false) {

            HashFunction hash = Hashing.sha256();
            HashCode code = (HashCode) hash.newHasher().putString(password, Charsets.UTF_8).hash();

            String hashedPassword = code.toString();

            PlayerQueries.signupPlayer(firstName,lastName, userName, hashedPassword);

            JSONObject object = new JSONObject().put("Succsessfull signup!", "ok");

            out.println("<p>" + object.toString() + "</p>"
                    + "<a href= \"http://localhost:8080/PlayersProject/index.jsp\">"
                    + "Go back to login page </a>");
        } else {
            out.println("<p>Username already exists</p>"
                    + "<a href= \"http://localhost:8080/PlayerProject/registration.jsp\">"
                    + "Go back to registration page </a>");
        }

    }
}
