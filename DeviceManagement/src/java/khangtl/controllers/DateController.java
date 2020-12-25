/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khangtl.daos.DevicesDAO;
import khangtl.dtos.DevicesDTO;

/**
 *
 * @author Peter
 */
public class DateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String from = request.getParameter("txtFrom");
            String to = request.getParameter("txtTo");
            boolean valid = true;
            if (from.isEmpty() || !from.matches("^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")) {
                request.setAttribute("INVALID_FROM", "Please follow format: yyyy-MM-dd");
                valid = false;
            }
            if (to.isEmpty() || !to.matches("^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")) {
                request.setAttribute("INVALID_TO", "Please follow format: yyyy-MM-dd");
                valid = false;
            }
            Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(to);
            if (!fromDate.before(toDate)) {
                request.setAttribute("INVALID_DATE", "From date must before to date");
                valid = false;
            }
            if (valid) {
                DevicesDAO dao = new DevicesDAO();
                List<DevicesDTO> result = dao.findByDate(from, to);
                request.setAttribute("DeviceDateINFO", result);
            }
        } catch (Exception e) {
            log("Error at DateController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("statistical.jsp").forward(request, response);
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
