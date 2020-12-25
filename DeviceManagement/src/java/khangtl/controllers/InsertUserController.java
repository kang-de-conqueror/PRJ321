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
import khangtl.dtos.UsersDTO;
import khangtl.dtos.UsersErrorObj;
import khangtl.models.UsersBean;

/**
 *
 * @author Peter
 */
public class InsertUserController extends HttpServlet {

    private static final String ERROR = "HomeController";
    private static final String SUCCESS = "HomeController";
    private static final String INVALID_USER = "insertUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String fullname = request.getParameter("txtFullname");
            String role = request.getParameter("txtRole");

            UsersErrorObj errorObj = new UsersErrorObj();

            boolean valid = true;
            if (username.isEmpty()) {
                errorObj.setUsernameError("Username can't be blank");
                valid = false;
            }
            if (password.isEmpty()) {
                errorObj.setPasswordError("Password can't be blank");
                valid = false;
            }
            if (!confirm.equals(password)) {
                errorObj.setConfirmError("Confirm must match password");
                valid = false;
            }
            if (fullname.isEmpty()) {
                errorObj.setFullnameError("Fullname can't be blank");
                valid = false;
            }
            if (role.isEmpty()) {
                errorObj.setRoleError("Role can't be blank");
                valid = false;
            }

            if (valid) {
                UsersBean beans = new UsersBean();
                UsersDTO dto = new UsersDTO(username, fullname, role);
                dto.setPassword(password);
                beans.setDto(dto);
                if (beans.insert()) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Insert successfully");
                } else {
                    request.setAttribute("ERROR", "Insert failed");
                }
            } else {
                url = INVALID_USER;
                request.setAttribute("INVALID_USER", errorObj);
            }
        } catch (Exception e) {
            log("Error at InsertUserController: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                url = INVALID_USER;
                UsersErrorObj errorObj = new UsersErrorObj();
                errorObj.setUsernameError("Username is existed");
                request.setAttribute("INVALID_USER", errorObj);
            }
            if (e.getMessage().contains("users") && e.getMessage().contains("NULL")) {
                url = INVALID_USER;
                UsersErrorObj errorObj = new UsersErrorObj();
                errorObj.setRoleError("Your input role is invalid, please try again");
                request.setAttribute("INVALID_USER", errorObj);
            }
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
