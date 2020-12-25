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
import java.util.Date;
import java.util.List;
import khangtl.db.MyConnection;
import khangtl.dtos.ItemsDTO;

/**
 *
 * @author Peter
 */
public class ItemsDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public ItemsDAO() {
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

    public List<ItemsDTO> getAllItemDisable() throws Exception {
        List<ItemsDTO> result = null;
        int id, quantity;
        String name, description;
        ItemsDTO dto = null;
        try {
            String sql = "SELECT i.ItemId, i.ItemName, i.Description, i.Quantity FROM TBL_Items i WHERE i.Status = 0";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getInt("ItemId");
                name = rs.getString("ItemName");
                description = rs.getString("Description");
                quantity = rs.getInt("Quantity");
                dto = new ItemsDTO(id, quantity, name, description);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ItemsDTO getItemById(int id) throws Exception {
        int quantity;
        String name, description;
        ItemsDTO dto = null;
        try {
            String sql = "SELECT i.ItemId, i.ItemName, i.Description, i.Quantity FROM TBL_Items i WHERE i.ItemId = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ItemId");
                name = rs.getString("ItemName");
                description = rs.getString("Description");
                quantity = rs.getInt("Quantity");
                dto = new ItemsDTO(id, quantity, name, description);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean repairItem(int id, String reason, Date time) throws Exception {
        boolean checkRepair;
        try {
            String sql = "INSERT INTO TBL_Used(Id, Reason, TimeOfRepair) VALUES(?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            preStm.setString(2, reason);
            preStm.setTimestamp(3, new java.sql.Timestamp(time.getTime()));
            checkRepair = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkRepair;
    }
}
