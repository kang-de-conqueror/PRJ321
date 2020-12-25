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
import khangtl.db.MyConnection;
import khangtl.dtos.BlogsDTO;

/**
 *
 * @author Peter
 */
public class BlogsDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public BlogsDAO() {
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

    public boolean insert(BlogsDTO dto) throws Exception {
        boolean checkInsert;
        try {
            String sql = "INSERT INTO BlogEntry(BlogId, Title, Body, DatePublish) VALUES(?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getId());
            preStm.setString(2, dto.getTitle());
            preStm.setString(3, dto.getBody());
            preStm.setString(4, dto.getDatePublish());
            checkInsert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkInsert;
    }

    public boolean delete(int id) throws Exception {
        boolean checkDelete;
        try {
            String sql = "DELETE FROM BlogEntry WHERE BlogId = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            checkDelete = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkDelete;
    }
}
