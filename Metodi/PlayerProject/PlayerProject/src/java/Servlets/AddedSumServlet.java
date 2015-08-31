/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import EntityAndQueries.Queries;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author metodi
 */
@WebServlet(name = "AddedSumServlet", urlPatterns = {"/AddedSumServlet"})
public class AddedSumServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
              dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          HttpSession session=request.getSession(true);
            synchronized(session){
            request.getSession().setMaxInactiveInterval(600);
            }
            
            
        PrintWriter out=response.getWriter();
        JSONObject object1=new JSONObject().put("Warning:","put sum");
        String userName=(String) request.getSession().getAttribute("user");
        String password=(String) request.getSession().getAttribute("pass");
        try {
              double sum=Double.parseDouble(request.getParameter("balance"));
              if (sum>0) {
                    String balance=Queries.InsertSum(userName, password, sum);
                JSONObject object=new JSONObject().put("Result:", balance);
                out.println("<form method=\"post\" action=\"SumServlet\">");
                out.println("<p>"+object.toString()+"</p>");
                out.println("<input type=\"Submit\" action=\"BACK\"/>");
                out.println("</form>");
            }else{
                      String balance=Queries.InsertSum(userName, password, sum);
                JSONObject object=new JSONObject().put("Result:", balance);
                out.println("<form method=\"post\" action=\"SumServlet\">");
                out.println("<p>"+object.toString()+"</p>");
                out.println("<input type=\"Submit\" action=\"BACK\"/>");
                out.println("</form>");
              }
            
        } catch (NumberFormatException e) {
                out.println("<form method=\"post\" action=\"SumServlet\">");
                out.println("<p>"+object1.toString()+"</p>");
                out.println("<input type=\"Submit\" action=\"BACK\"/>");
                out.println("</form>");
        }
     
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
