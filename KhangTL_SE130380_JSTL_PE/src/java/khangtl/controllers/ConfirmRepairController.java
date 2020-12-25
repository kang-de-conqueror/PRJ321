/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khangtl.dtos.ItemsDTO;
import khangtl.dtos.UsedErrorObj;
import khangtl.models.UsedBeans;

/**
 *
 * @author Peter
 */
public class ConfirmRepairController extends HttpServlet {

    private static final String SUCCESS = "IndexController";
    private static final String ERROR = "IndexController";
    private static final String INVALID_REPAIR = "chooseItem.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("txtItemID");
            String name = request.getParameter("txtItemName");
            String description = request.getParameter("txtDescription");
            ItemsDTO dto = new ItemsDTO(Integer.parseInt(id), name, description);
            String reason = request.getParameter("txtReason");
            UsedErrorObj errorObj = new UsedErrorObj();
            boolean valid = true;
            if (reason.isEmpty()) {
                errorObj.setReasonError("Reason cannot be blank");
                valid = false;
            }
            if (valid) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date now = new Date();
                now = dateFormat.parse(dateFormat.format(now));
                UsedBeans beans = new UsedBeans();
                beans.setId(Integer.parseInt(id));
                beans.setReason(reason);
                beans.setTime(now);
                if (beans.repairItem()) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Repair successfully");
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Repair failed");
                }
            } else {
                url = INVALID_REPAIR;
                request.setAttribute("ItemINFO", dto);
                request.setAttribute("INVALID_REPAIR", errorObj);
            }
        } catch (Exception e) {
            log("Error at ConfirmRepairController: " + e.getMessage());
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
