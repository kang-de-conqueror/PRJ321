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
import khangtl.dtos.DevicesErrorObj;
import khangtl.dtos.RoomsDTO;
import khangtl.dtos.RoomsErrorObj;
import khangtl.dtos.UsersDTO;
import khangtl.dtos.UsersErrorObj;
import khangtl.models.DevicesBean;
import khangtl.models.RoomsBean;
import khangtl.models.UsersBean;

/**
 *
 * @author Peter
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";
    private static final String INVALID_USER = "updateUser.jsp";
    private static final String INVALID_ROOM = "updateRoom.jsp";
    private static final String INVALID_DEVICE = "updateDevice.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            if (request.getParameter("txtUsername") != null) {
                String username = request.getParameter("txtUsername");
                String fullname = request.getParameter("txtFullname");
                String role = request.getParameter("txtRole");

                UsersDTO dto = new UsersDTO(username, fullname, role);
                UsersErrorObj errorObj = new UsersErrorObj();

                boolean valid = true;

                if (fullname.isEmpty()) {
                    errorObj.setFullnameError("Fullname cannot be blank");
                    valid = false;
                }
                if (role.isEmpty()) {
                    errorObj.setRoleError("Role cannot be blank");
                    valid = false;
                }

                if (valid) {
                    UsersBean beans = new UsersBean();
                    beans.setDto(dto);
                    if (beans.update()) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Update failed");
                        request.setAttribute("UserDTO", dto);
                    }
                } else {
                    url = INVALID_USER;
                    request.setAttribute("INVALID_USER", errorObj);
                    request.setAttribute("UserDTO", dto);
                }
            } else if (request.getParameter("txtRoomID") != null) {
                int roomID = Integer.parseInt(request.getParameter("txtRoomID"));
                String roomName = request.getParameter("txtRoomName");
                RoomsDTO dto = new RoomsDTO(roomID, roomName);
                RoomsErrorObj errorObj = new RoomsErrorObj();
                boolean valid = true;

                if (roomName.isEmpty()) {
                    errorObj.setRoomNameError("Room name cannot be blank");
                    valid = false;
                }
                if (valid) {
                    RoomsBean roomBeans = new RoomsBean();
                    roomBeans.setDto(dto);
                    if (roomBeans.update()) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Update failed");
                        request.setAttribute("RoomDTO", dto);
                    }
                } else {
                    url = INVALID_ROOM;
                    request.setAttribute("INVALID_ROOM", errorObj);
                    request.setAttribute("RoomDTO", dto);
                }
            } else if (request.getParameter("txtDeviceID") != null) {
                int deviceID = Integer.parseInt(request.getParameter("txtDeviceID"));
                String deviceName = request.getParameter("txtDeviceName");
                String deviceDescription = request.getParameter("txtDeviceDescription");
                String deviceType = request.getParameter("txtDeviceType");
                String deviceState = request.getParameter("txtDeviceState");
                String deviceImage = request.getParameter("txtDeviceImage");
                String buyDate = request.getParameter("txtBuyDate");
                String guaranteeDuration = request.getParameter("txtGuaranteeDuration");

                DevicesErrorObj errorObj = new DevicesErrorObj();

                boolean valid = true;

                if (deviceName.isEmpty()) {
                    errorObj.setDeviceNameError("Device name cannot be blank");
                    valid = false;
                }
                if (deviceDescription.isEmpty()) {
                    errorObj.setDeviceDescriptionError("Device description cannot be blank");
                    valid = false;
                }
                if (deviceType.isEmpty()) {
                    errorObj.setDeviceTypeError("Device type cannot be blank");
                    valid = false;
                }
                if (deviceState.isEmpty()) {
                    errorObj.setDeviceStateError("Device state cannot be blank");
                    valid = false;
                }
                if (deviceImage.isEmpty()) {
                    errorObj.setDeviceImageError("Device image cannot be blank");
                    valid = false;
                }

                if (!buyDate.matches("^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")) {
                    errorObj.setBuyDateError("Please follow this format: YYYY-MM-DD");
                    valid = false;
                }

                if (!guaranteeDuration.matches("^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")) {
                    errorObj.setGuaranteeDurationError("Please follow this format: YYYY-MM-DD");
                    valid = false;
                }
                deviceImage = "assets/images/devices/".concat(deviceImage);
                DevicesDTO dto = new DevicesDTO(deviceID, deviceName, deviceDescription, deviceType, deviceState, deviceImage, buyDate, guaranteeDuration);
                if (valid) {
                    DevicesBean deviceBeans = new DevicesBean();
                    deviceBeans.setDto(dto);
                    if (deviceBeans.update()) {
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Update failed");
                        request.setAttribute("DeviceDTO", dto);
                    }
                } else {
                    url = INVALID_DEVICE;
                    request.setAttribute("INVALID_DEVICE", errorObj);
                    request.setAttribute("DeviceDTO", dto);
                }
            }
        } catch (Exception e) {
            log("Error at UpdateController: " + e.getMessage());
            if (e.getMessage().contains("users") && e.getMessage().contains("NULL")) {
                url = INVALID_USER;
                String username = request.getParameter("txtUsername");
                String fullname = request.getParameter("txtFullname");
                String role = request.getParameter("txtRole");
                UsersDTO dto = new UsersDTO(username, fullname, role);
                UsersErrorObj errorObj = new UsersErrorObj();
                errorObj.setRoleError("Your input role is invalid, please try again");
                request.setAttribute("INVALID_USER", errorObj);
                request.setAttribute("UserDTO", dto);
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
