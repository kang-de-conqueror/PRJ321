/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khangtl.dtos.UsersDTO;
import khangtl.dtos.UsersErrorObj;
import khangtl.models.UsersBean;

/**
 *
 * @author Peter
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String HOME = "HomeController";
    private static final String INVALID = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            UsersErrorObj errorObj = new UsersErrorObj();
            boolean valid = true;
            if (username.isEmpty()) {
                errorObj.setUsernameError("Username cannot be blank");
                valid = false;
            }
            if (password.isEmpty()) {
                errorObj.setPasswordError("Password cannot be blank");
                valid = false;
            }
            if (valid) {
                UsersBean beans = new UsersBean();
                beans.setUsername(username);
                beans.setPassword(password);

                UsersDTO dto = beans.checkLogin();
                HttpSession session = request.getSession();
                if (dto == null) {
                    request.setAttribute("ERROR", "Invalid Username or Password");
                    session.setAttribute("pageBeforeError", "index.jsp");
                } else {
                    session.setAttribute("USER", username);
                    session.setAttribute("FULLNAME", dto.getFullname());
                    session.setAttribute("UserRole", dto.getRole());
                    url = HOME;
                }

            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (Exception e) {
            log("Error at LoginController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
