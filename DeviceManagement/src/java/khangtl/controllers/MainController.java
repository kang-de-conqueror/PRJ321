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

/**
 *
 * @author Peter
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String HOME = "HomeController";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String SEARCH = "SearchController";
    private static final String DELETE = "DeleteController";
    private static final String EDIT = "EditController";
    private static final String UPDATE = "UpdateController";
    private static final String INSERT_USER = "InsertUserController";
    private static final String INSERT_ROOM = "InsertRoomController";
    private static final String INSERT_DEVICE = "InsertDeviceController";
    private static final String SEND_REQUEST_REPAIR = "SendRequestRepairController";
    private static final String CONFIRM_REPAIR = "ConfirmRepairController";
    private static final String HANDLE_REPAIR = "HandleRepairController";
    private static final String START_REPAIR = "StartRepairController";
    private static final String FINISH_REPAIR = "FinishRepairController";
    private static final String CANCEL_REQUEST = "CancelRequestController";
    private static final String CONFIRM_FINISH_REPAIR = "ConfirmFinishRepairController";
    private static final String HISTORY_DEVICE = "HistoryDeviceController";
    private static final String CHANGE_ROOM = "ChangeRoomController";
    private static final String CONFIRM_NEW_ROOM = "ConfirmNewRoomController";
    private static final String CONFIRM_CHANGE_ROOM = "ConfirmChangeRoomController";
    private static final String ADD_CHANGE_USER_ROOM = "AddChangeUserRoom";
    private static final String CONFIRM_USER_ROOM = "ConfirmUserRoomController";
    private static final String ADD_CHANGE_DEVICE_ROOM = "AddChangeDeviceRoom";
    private static final String CONFIRM_DEVICE_ROOM = "ConfirmDeviceRoomController";
    private static final String HISTORY_REPAIR = "HistoryRepairController";
    private static final String REPAIR_ANNOUNCE = "RepairAnnounceController";
    private static final String DATE = "DateController";
    private static final String REPAIR_QUANTITY = "RepairQuantityController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "Home":
                    url = HOME;
                    break;
                case "Login":
                    url = LOGIN;
                    break;
                case "Logout":
                    url = LOGOUT;
                    break;
                case "Search":
                    url = SEARCH;
                    break;
                case "Delete":
                    url = DELETE;
                    break;
                case "Edit":
                    url = EDIT;
                    break;
                case "Update":
                    url = UPDATE;
                    break;
                case "Insert User":
                    url = INSERT_USER;
                    break;
                case "Insert Room":
                    url = INSERT_ROOM;
                    break;
                case "Insert Device":
                    url = INSERT_DEVICE;
                    break;
                case "Send Request Repair":
                    url = SEND_REQUEST_REPAIR;
                    break;
                case "Confirm Repair":
                    url = CONFIRM_REPAIR;
                    break;
                case "Handle Repair":
                    url = HANDLE_REPAIR;
                    break;
                case "Start Repair":
                    url = START_REPAIR;
                    break;
                case "Finish Repair":
                    url = FINISH_REPAIR;
                    break;
                case "Cancel Request":
                    url = CANCEL_REQUEST;
                    break;
                case "Confirm Finish Repair":
                    url = CONFIRM_FINISH_REPAIR;
                    break;
                case "History Device":
                    url = HISTORY_DEVICE;
                    break;
                case "Change Room":
                    url = CHANGE_ROOM;
                    break;
                case "Confirm New Room":
                    url = CONFIRM_NEW_ROOM;
                    break;
                case "Confirm Change Room":
                    url = CONFIRM_CHANGE_ROOM;
                    break;
                case "Add/Change User Room":
                    url = ADD_CHANGE_USER_ROOM;
                    break;
                case "Confirm User Room":
                    url = CONFIRM_USER_ROOM;
                    break;
                case "Add/Change Device Room":
                    url = ADD_CHANGE_DEVICE_ROOM;
                    break;
                case "Confirm Device Room":
                    url = CONFIRM_DEVICE_ROOM;
                    break;
                case "View History Repair":
                    url = HISTORY_REPAIR;
                    break;
                case "Repair Announce":
                    url = REPAIR_ANNOUNCE;
                    break;
                case "Search by date":
                    url = DATE;
                    break;
                case "Search by repair's quantity":
                    url = REPAIR_QUANTITY;
                    break;
                default:
                    request.setAttribute("ERROR", "Your action is invalid");
                    break;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.getMessage());
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
