/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khangtl.daos.DevicesDAO;
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
public class SearchController extends HttpServlet {

    private static final String ADMIN_SEARCH_USER = "searchUserAdmin.jsp";
    private static final String ADMIN_SEARCH_ROOM = "searchRoomAdmin.jsp";
    private static final String ADMIN_SEARCH_DEVICE = "searchDeviceAdmin.jsp";
    private static final String STAFF_SEARCH_DEVICE = "searchDeviceStaff.jsp";
    private static final String USER_SEARCH_DEVICE = "searchDeviceUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            String role = (String) request.getSession().getAttribute("UserRole");
            switch (role) {
                case "admin":
                    if (request.getParameter("txtUserSearch") != null) {
                        url = ADMIN_SEARCH_USER;
                        String search = request.getParameter("txtUserSearch");
                        UsersBean beans = new UsersBean();
                        beans.setFullname(search);
                        List<UsersDTO> result = beans.findByLikeName();
                        request.setAttribute("UserINFO", result);
                    } else if (request.getParameter("txtRoomSearch") != null) {
                        url = ADMIN_SEARCH_ROOM;
                        String search = request.getParameter("txtRoomSearch");
                        RoomsBean roomBeans = new RoomsBean();
                        roomBeans.setRoomName(search);
                        List<RoomsDTO> result = roomBeans.findByLikeName();
                        request.setAttribute("RoomINFO", result);
                    } else if (request.getParameter("txtDeviceSearch") != null) {
                        url = ADMIN_SEARCH_DEVICE;
                        String search = request.getParameter("txtDeviceSearch");
                        DevicesBean deviceBeans = new DevicesBean();
                        deviceBeans.setDeviceName(search);
                        List<DevicesDTO> result = deviceBeans.findByLikeName();
                        request.setAttribute("DeviceINFO", result);
                    }
                    break;
                case "staff":
                    url = STAFF_SEARCH_DEVICE;
                    if (request.getParameter("txtDeviceSearch") != null) {
                        String search = request.getParameter("txtDeviceSearch");
                        DevicesBean deviceBeans = new DevicesBean();
                        deviceBeans.setDeviceName(search);
                        List<DevicesDTO> result = deviceBeans.findByLikeName();
                        request.setAttribute("DeviceINFO", result);
                    }
                    break;
                case "user":
                    url = USER_SEARCH_DEVICE;
                    if (request.getParameter("txtDeviceSearch") != null) {
                        String search = request.getParameter("txtDeviceSearch");
                        DevicesDAO dao = new DevicesDAO();
                        List<DevicesDTO> result = dao.findFollowRoom((String) request.getSession().getAttribute("USER"), search);
                        request.setAttribute("DeviceINFO", result);
                    }
                    break;
            }
        } catch (Exception e) {
            log("Error at SearchController: " + e.getMessage());
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
