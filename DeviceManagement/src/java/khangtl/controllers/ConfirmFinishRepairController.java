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
import khangtl.daos.RepairsDAO;

/**
 *
 * @author Peter
 */
public class ConfirmFinishRepairController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";
    private static final String INVALID_FINISH = "finishRepair.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("RepairID");
            String name = request.getParameter("DeviceName");
            String description = request.getParameter("txtDescription");
            String result = request.getParameter("txtResult");
            boolean valid = true;
            if (description.isEmpty()) {
                request.setAttribute("INVALID_DESCRIPTION", "Description cannot be blank");
                valid = false;
            }
            if (result.isEmpty()) {
                request.setAttribute("INVALID_RESULT", "Result cannot be blank");
                valid = false;
            }

            if (valid) {
                RepairsDAO dao = new RepairsDAO();
                if (dao.finishRepair(Integer.parseInt(id), description, result)) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Finish repair successfully");
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Finish repair failed");
                }
            } else {
                url = INVALID_FINISH;
                request.setAttribute("RepairID", id);
                request.setAttribute("DeviceName", name);
            }
        } catch (Exception e) {
            log("Error at ConfirmFinishRepairController: " + e.getMessage());
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
