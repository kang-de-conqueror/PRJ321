/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.models;

import java.util.List;
import khangtl.daos.RoomsDAO;
import khangtl.dtos.RoomsDTO;

/**
 *
 * @author Peter
 */
public class RoomsBean {

    private int roomID;
    private String roomName;
    private RoomsDAO dao;
    private RoomsDTO dto;

    public List<RoomsDTO> findByLikeName() throws Exception {
        return dao.findByLikeName(roomName);
    }

    public boolean delete() throws Exception {
        return dao.delete(roomID);
    }

    public RoomsDTO findByPrimaryKey() throws Exception {
        return dao.findByPrimaryKey(roomID);
    }

    public boolean update() throws Exception {
        return dao.update(dto);
    }
    
    public boolean insert() throws Exception {
        return dao.insert(dto);
    }

    public RoomsBean() {
        dao = new RoomsDAO();
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public RoomsDTO getDto() {
        return dto;
    }

    public void setDto(RoomsDTO dto) {
        this.dto = dto;
    }
}
