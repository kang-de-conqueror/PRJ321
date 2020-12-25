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
import khangtl.daos.UsersDAO;
import khangtl.dtos.UsersDTO;

/**
 *
 * @author Peter
 */
public class ConfirmUserRoomController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";
    private static final String INVALID_USER_ROOM = "addChangeUserRoom.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String oldRoom = request.getParameter("txtOldRoom");
        String newRoom = request.getParameter("txtNewRoom");
        String username = request.getParameter("UserID");
        try {

            boolean valid = true;

            if (newRoom.isEmpty() || !newRoom.matches("\\d+")) {
                valid = false;
                request.setAttribute("INVALID_USER_ROOM", "Please input a digit");
            }
            if (valid) {
                UsersDAO dao = new UsersDAO();
                if (dao.updateUserRoom(username, Integer.parseInt(newRoom))) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "Update room successfully");
                } else {
                    url = ERROR;
                    request.setAttribute("ERROR", "Update room failed");
                }
            } else {
                url = INVALID_USER_ROOM;
                UsersDTO dto = new UsersDTO(username, Integer.parseInt(oldRoom));
                request.setAttribute("UserINFO", dto);
            }
        } catch (Exception e) {
            log("Error at ConfirmUserRoomController: " + e.getMessage());
            if (e.getMessage().contains("constraint")) {
                url = ERROR;
                request.setAttribute("ERROR", "Your input room is not existed");
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
