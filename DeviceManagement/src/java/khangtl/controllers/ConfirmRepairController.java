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
import khangtl.daos.RepairsDAO;
import khangtl.dtos.DevicesDTO;

/**
 *
 * @author Peter
 */
public class ConfirmRepairController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";
    private static final String INVALID_REQUEST_REPAIR = "requestRepair.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String deviceID = request.getParameter("txtDeviceID");
            String deviceName = request.getParameter("txtDeviceName");
            String deviceDescription = request.getParameter("txtRequestDescription");
            String username = (String) request.getSession().getAttribute("USER");
            DevicesDTO dto = new DevicesDTO(Integer.parseInt(deviceID), deviceName);
            boolean valid = true;
            if (deviceDescription.isEmpty()) {
                valid = false;
            }

            if (valid) {
                RepairsDAO dao = new RepairsDAO();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date now = new Date();
                now = dateFormat.parse(dateFormat.format(now));
                if (dao.sendRepair(Integer.parseInt(deviceID), username, deviceDescription, now)) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Send request successfully!");
                } else {
                    request.setAttribute("ERROR", "Send request failed");
                }
            } else {
                url = INVALID_REQUEST_REPAIR;
                request.setAttribute("INVALID_REQUEST_REPAIR", "Description cannot be blank");
                request.setAttribute("DeviceDTO", dto);
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
