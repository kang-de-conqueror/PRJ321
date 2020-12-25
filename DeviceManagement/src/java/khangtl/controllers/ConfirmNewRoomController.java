/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ConfirmNewRoomController extends HttpServlet {
    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";
    private static final String INVALID_NEW_ROOM = "addChangeDeviceRoom.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("txtDeviceID");
            String newRoom = request.getParameter("txtNewRoom");
            ChangeRoomErrorObj errorObj = new ChangeRoomErrorObj();
            boolean valid = true;
            if (!newRoom.matches("\\d+")) {
                errorObj.setNewRoomError("Please input a digit");
                valid = false;
            }
            if (newRoom.length() > 5) {
                errorObj.setNewRoomError("Please input number < 5 digits");
                valid = false;
            }
            
            if (valid) {
                DevicesRoomsDAO dao = new DevicesRoomsDAO();
                if (dao.insertRoom(Integer.parseInt(id), Integer.parseInt(newRoom))) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Insert new room successfully");
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Insert new room failed");
                }
            } else {
                url = INVALID_NEW_ROOM;
                request.setAttribute("DeviceID", Integer.parseInt(id));
                request.setAttribute("CurrentRoom", -1);
                request.setAttribute("INVALID_NEW_ROOM", errorObj);
            }

        } catch (Exception e) {
            log("Error at ConfirmNewRoomController: " + e.getMessage());
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
