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
import khangtl.dtos.RoomsDTO;
import khangtl.dtos.RoomsErrorObj;
import khangtl.models.RoomsBean;

/**
 *
 * @author Peter
 */
public class InsertRoomController extends HttpServlet {

    private static final String ERROR = "HomeController";
    private static final String SUCCESS = "HomeController";
    private static final String INVALID_ROOM = "insertRoom.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String roomID = request.getParameter("txtRoomID");
            String roomName = request.getParameter("txtRoomName");

            RoomsErrorObj errorObj = new RoomsErrorObj();

            boolean valid = true;
            if (roomID.isEmpty()) {
                errorObj.setRoomIDError("Room id cannot be blank");
                valid = false;
            }
            if (!roomID.matches("^(0*[1-9][0-9]*(\\.[0-9]+)?|0+\\.[0-9]*[1-9][0-9]*)$")) {
                errorObj.setRoomIDError("Please input a number greater than 0");
                valid = false;
            }
            if (roomName.isEmpty()) {
                errorObj.setRoomNameError("Room name cannot be blank");
                valid = false;
            }

            if (valid) {
                RoomsBean beans = new RoomsBean();
                RoomsDTO dto = new RoomsDTO(Integer.parseInt(roomID), roomName);
                beans.setDto(dto);
                if (beans.insert()) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Insert successfully");
                } else {
                    request.setAttribute("ERROR", "Insert failed");
                }
            } else {
                url = INVALID_ROOM;
                request.setAttribute("INVALID_ROOM", errorObj);
            }
        } catch (Exception e) {
            log("Error at InsertRoomController: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                url = INVALID_ROOM;
                RoomsErrorObj errorObj = new RoomsErrorObj();
                errorObj.setRoomIDError("Room id is existed");
                request.setAttribute("INVALID_ROOM", errorObj);
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
