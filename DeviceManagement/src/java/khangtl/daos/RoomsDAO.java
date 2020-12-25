/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import khangtl.db.MyConnection;
import khangtl.dtos.RoomsDTO;

/**
 *
 * @author Peter
 */
public class RoomsDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public RoomsDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<RoomsDTO> findByLikeName(String search) throws Exception {
        List<RoomsDTO> result = null;
        int roomID;
        String roomName;
        RoomsDTO dto = null;
        try {
            String sql = "SELECT RoomID, RoomName FROM dbo.rooms WHERE RoomName LIKE ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                roomID = rs.getInt("roomID");
                roomName = rs.getString("RoomName");
                dto = new RoomsDTO(roomID, roomName);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(int id) throws Exception {
        boolean checkDelete = false;
        try {
            String sql = "DELETE FROM dbo.rooms WHERE RoomID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            checkDelete = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkDelete;
    }

    public RoomsDTO findByPrimaryKey(int key) throws Exception {
        RoomsDTO dto = null;
        try {
            String sql = "SELECT RoomName FROM dbo.rooms WHERE RoomID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, key);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String roomName = rs.getString("roomName");
                dto = new RoomsDTO(key, roomName);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean update(RoomsDTO dto) throws Exception {
        boolean checkUpdate = false;
        try {
            String sql = "UPDATE dbo.rooms SET RoomName = ? WHERE roomID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getRoomName());
            preStm.setInt(2, dto.getRoomID());
            checkUpdate = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }

    public boolean insert(RoomsDTO dto) throws Exception {
        boolean checkInsert;
        try {
            String sql = "INSERT INTO dbo.rooms(RoomID, RoomName) VALUES(?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getRoomID());
            preStm.setString(2, dto.getRoomName());
            checkInsert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkInsert;
    }
}
