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
import khangtl.dtos.RegistrationDTO;
import khangtl.dtos.RegistrationErrorObj;
import khangtl.models.KhangTLBean;

/**
 *
 * @author Peter
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchController";
    private static final String INVALID = "update.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String fullname = request.getParameter("txtFullname");
            String role = request.getParameter("txtRole");
            RegistrationDTO dto = new RegistrationDTO(username, fullname, role);

            RegistrationErrorObj errorObj = new RegistrationErrorObj();
            boolean valid = true;
            if (fullname.length() == 0) {
                errorObj.setFullnameError(("Fullname can't be blank"));
                valid = false;
            }
            if (role.length() == 0) {
                errorObj.setRoleError("Role can't be blank");
                valid = false;
            }
            if (valid) {
                KhangTLBean beans = new KhangTLBean();
                beans.setDto(dto);
                if (beans.update()) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Update failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
                request.setAttribute("DTO", dto);
            }
        } catch (Exception e) {
            log("ERROR at UpdateController: " + e.getMessage());
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
