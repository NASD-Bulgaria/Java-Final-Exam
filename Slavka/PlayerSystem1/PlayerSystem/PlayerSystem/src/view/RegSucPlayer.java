package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import controller.Queries;
import model.Player;
import model.ValidationsUsernames;


/**
 * Servlet implementation class RegSucPlayer
 */
@WebServlet("/RegSucPlayer")
public class RegSucPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegSucPlayer() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter();
		String loginName= request.getParameter("loginName");
		String loginPassword= request.getParameter("loginPassword");
		boolean isValid= ValidationsUsernames.validPlayerName(loginName);
		if (isValid) {
			HashFunction hash = Hashing.sha1();
			String salt = hash.newHasher().putString(loginName, Charsets.UTF_8).hash().toString();
			String pass = hash.newHasher().putString(loginPassword, Charsets.UTF_8).hash().toString();
			HashCode hs = hash.newHasher()
					.putString(pass, Charsets.UTF_8)
					.putString(salt, Charsets.UTF_8)
					.hash();
			String result =hs.toString();
			
			Player temp =Queries.addNewPlayer(loginName, result, salt, "0");	
			JSONObject json = new JSONObject().put("status", "ok");
			out.println("<p>"+json.toString()+" </p>");
			out.println("<a href=\"http://localhost:8080/PlayerSystem/index.jsp\">Go back");
			out.println("</a>");
		}else {
			out.println("<p>Player witht this name exist");
			out.println("<a href=\"http://localhost:8080/PlayerSystem/RegisterServlet\">Go bank");
			out.println("</a>");
		}
		
	}

}
