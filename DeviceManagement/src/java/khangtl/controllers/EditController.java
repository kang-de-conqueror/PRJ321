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
import khangtl.dtos.DevicesDTO;
import khangtl.dtos.RoomsDTO;
import khangtl.dtos.UsersDTO;
import khangtl.models.DevicesBean;
import khangtl.models.RoomsBean;
import khangtl.models.UsersBean;

/**
 *
 * @author Peter
 */
public class EditController extends HttpServlet {

    private static final String USER = "updateUser.jsp";
    private static final String ROOM = "updateRoom.jsp";
    private static final String DEVICE = "updateDevice.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            if (request.getParameter("UserID") != null) {
                url = USER;
                String id = request.getParameter("UserID");
                UsersBean beans = new UsersBean();
                beans.setUsername(id);
                UsersDTO dto = beans.findByPrimaryKey();
                request.setAttribute("UserDTO", dto);
            } else if (request.getParameter("RoomID") != null) {
                url = ROOM;
                int id = Integer.parseInt(request.getParameter("RoomID"));
                RoomsBean roomBeans = new RoomsBean();
                roomBeans.setRoomID(id);
                RoomsDTO dto = roomBeans.findByPrimaryKey();
                request.setAttribute("RoomDTO", dto);
            } else if (request.getParameter("DeviceID") != null) {
                url = DEVICE;
                int id = Integer.parseInt(request.getParameter("DeviceID"));
                DevicesBean deviceBeans = new DevicesBean();
                deviceBeans.setDeviceID(id);
                DevicesDTO dto = deviceBeans.findByPrimaryKey();
                request.setAttribute("DeviceDTO", dto);
            }
        } catch (Exception e) {
            log("Error at EditController: " + e.getMessage());
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
