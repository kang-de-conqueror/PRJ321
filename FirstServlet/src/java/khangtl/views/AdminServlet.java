/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.views;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khangtl.dtos.RegistrationDTO;

/**
 *
 * @author Peter
 */
public class AdminServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome Admin</h1>");
            out.println("<h2>Search</h2>");
            out.println("<form action='SearchController' method='POST'>");
            out.println("Fullname: <input type='text' name='txtSearch' />");
            out.println("<input type='submit' value='Search' />");
            out.println("</form>");
            List<RegistrationDTO> listAccount = (List<RegistrationDTO>) request.getAttribute("INFO");
            if (listAccount != null) {
                if (listAccount.size() > 0) {
                    String search = request.getParameter("txtSearch");
                    out.println("<table border='1'>");
                    out.println("<thead>");
                    out.println("<tr>");
                    out.println("<th>No</th>");
                    out.println("<th>Username</th>");
                    out.println("<th>Fullname</th>");
                    out.println("<th>Role</th>");
                    out.println("<th>Link</th>");
                    out.println("<th>Button</th>");
                    out.println("</tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    for (int i = 0; i < listAccount.size(); i++) {
                        out.println("<tr>");
                        out.println("<td>" + (i + 1) + "</td>");
                        out.println("<td>" + listAccount.get(i).getUsername() + "</td>");
                        out.println("<td>" + listAccount.get(i).getFullname() + "</td>");
                        out.println("<td>" + listAccount.get(i).getRole() + "</td>");
                        out.println("<td><a href='DeleteController?username=" + listAccount.get(i).getUsername() + "&txtSearch=" + search + "'>Delete</a></td>");
                        out.println("<td>");
                        out.println("<form action='DeleteController' method='POST'>");
                        out.println("<input type='hidden' name='username' value='" + listAccount.get(i).getUsername() + "' />");
                        out.println("<input type='hidden' name='txtSearch' value='" + search + "' />");
                        out.println("<input type='submit' value='Delete' />");
                        out.println("</td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody");
                    out.println("</table>");
                } else {
                    out.println("No records found");
                }
            }

            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
