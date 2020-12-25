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
import khangtl.models.DevicesBean;

/**
 *
 * @author Peter
 */
public class InsertDeviceController extends HttpServlet {

    private static final String ERROR = "HomeController";
    private static final String SUCCESS = "HomeController";
    private static final String INVALID_DEVICE = "insertDevice.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
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

            if (valid) {
                DevicesBean beans = new DevicesBean();
                deviceImage = "assets/images/devices/".concat(deviceImage);
                DevicesDTO dto = new DevicesDTO(deviceName, deviceDescription, deviceType, deviceState, deviceImage, buyDate, guaranteeDuration);
                beans.setDto(dto);
                if (beans.insert()) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Insert successfully");
                } else {
                    request.setAttribute("ERROR", "Insert failed");
                }
            } else {
                url = INVALID_DEVICE;
                request.setAttribute("INVALID_DEVICE", errorObj);
            }
        } catch (Exception e) {
            log("Error at InsertDeviceController: " + e.getMessage());
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
