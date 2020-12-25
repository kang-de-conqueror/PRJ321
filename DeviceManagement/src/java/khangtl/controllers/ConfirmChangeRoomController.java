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
import khangtl.daos.DevicesRoomsDAO;
import khangtl.dtos.ChangeRoomErrorObj;

/**
 *
 * @author Peter
 */
public class ConfirmChangeRoomController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";
    private static final String INVALID_CHANGE_ROOM = "addChangeDeviceRoom.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("txtDeviceID");
            String currentRoom = request.getParameter("txtCurrentRoom");
            String newRoom = request.getParameter("txtNewRoom");
            String reason = request.getParameter("txtReason");
            ChangeRoomErrorObj errorObj = new ChangeRoomErrorObj();
            boolean valid = true;
            if (!newRoom.matches("\\d+")) {
                errorObj.setNewRoomError("Please input a digit");
                valid = false;
            }
            if (newRoom.equals(currentRoom)) {
                errorObj.setNewRoomError("New room must different with current room");
                valid = false;
            }
            if (newRoom.length() > 5) {
                errorObj.setNewRoomError("Please input number < 5 digits");
                valid = false;
            }

            if (reason.isEmpty()) {
                errorObj.setReasonError("Move reason cannot be blank");
                valid = false;
            }

            if (valid) {
                DevicesRoomsDAO dao = new DevicesRoomsDAO();
                String username = (String) request.getSession().getAttribute("USER");
                boolean checkChange = dao.changeRoom(Integer.parseInt(id), Integer.parseInt(newRoom), username, reason);
                if (checkChange) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Change room successfully");
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Change room failed");
                }
            } else {
                url = INVALID_CHANGE_ROOM;
                request.setAttribute("INVALID_CHANGE_ROOM", errorObj);
                request.setAttribute("DeviceID", id);
                request.setAttribute("CurrentRoom", currentRoom);
            }
        } catch (Exception e) {
            log("Error at ConfirmChangeRoomController: " + e.getMessage());
            if (e.getMessage().contains("conflict")) {
                url = ERROR;
                request.setAttribute("ERROR", "New room haven't existed in room list, please try again");
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
