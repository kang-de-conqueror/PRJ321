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
import khangtl.models.DevicesBean;
import khangtl.models.RoomsBean;
import khangtl.models.UsersBean;

/**
 *
 * @author Peter
 */
public class DeleteController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            if (request.getParameter("UserID") != null) {
                String id = request.getParameter("UserID");
                UsersBean beans = new UsersBean();
                beans.setUsername(id);
                if (beans.delete()) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Delete successfully");
                } else {
                    request.setAttribute("ERROR", "Delete failed");
                }
            } else if (request.getParameter("RoomID") != null) {
                int id = Integer.parseInt(request.getParameter("RoomID"));
                RoomsBean roomBeans = new RoomsBean();
                roomBeans.setRoomID(id);
                if (roomBeans.delete()) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Delete successfully");
                } else {
                    request.setAttribute("ERROR", "Delete failed");
                }
            } else if (request.getParameter("DeviceID") != null) {
                int id = Integer.parseInt(request.getParameter("DeviceID"));
                DevicesBean deviceBeans = new DevicesBean();
                deviceBeans.setDeviceID(id);
                if (deviceBeans.delete()) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Delete successfully");
                } else {
                    request.setAttribute("ERROR", "Delete failed");
                }
            }
        } catch (Exception e) {
            log("Error at DeleteController: " + e.getMessage());
            if (e.getMessage().contains("constraint") && e.getMessage().contains("RoomID")) {
                request.setAttribute("ERROR", "Cannot delete this room because users are in this room");
            }
            if (e.getMessage().contains("constraint") && e.getMessage().contains("DeviceID") && e.getMessage().contains("dbo.device_histories_room")) {
                request.setAttribute("ERROR", "Cannot delete this room because devices are in this room");
            }
            if (e.getMessage().contains("constraint") && e.getMessage().contains("DeviceID") && e.getMessage().contains("dbo.repairs")) {
                request.setAttribute("ERROR", "Cannot delete this device because device has been requesting to repair");
            }
            if (e.getMessage().contains("constraint") && (e.getMessage().contains("UserRequestID") || e.getMessage().contains("UserRepairID"))) {
                request.setAttribute("ERROR", "Cannot delete this user because user has activities");
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
