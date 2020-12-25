/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import khangtl.db.MyConnection;
import khangtl.dtos.RegistrationDTO;

/**
 *
 * @author Peter
 */
public class RegistrationDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public RegistrationDAO() {

    }

    private void closeConnection() throws ClassNotFoundException, SQLException, Exception {
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

    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";
        try {
            String sql = "SELECT Role FROM Registration Where Username = ? AND Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public List<RegistrationDTO> findByLikeName(String search) throws Exception {
        List<RegistrationDTO> result = null;
        String username, fullname, role;
        RegistrationDTO dto = null;
        try {
            String sql = "SELECT Username, Fullname, Role FROM Registration WHERE Fullname LIKE ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("Username");
                fullname = rs.getString("Fullname");
                role = rs.getString("Role");
                dto = new RegistrationDTO(username, fullname, role);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(String id) throws Exception {
        boolean checkDelete = false;
        try {
            String sql = "DELETE FROM Registration WHERE Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            checkDelete = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkDelete;
    }

    public RegistrationDTO findByPrimaryKey(String username) throws Exception {
        RegistrationDTO dto = null;
        try {
            String sql = "SELECT Fullname, Role FROM Registration WHERE Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("Fullname");
                String role = rs.getString("Role");
                dto = new RegistrationDTO(username, fullname, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean update(RegistrationDTO dto) throws Exception {
        boolean checkUpdate = false;
        try {
            String sql = "UPDATE Registration Fullname = ?, Role = ? WHERE Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getFullname());
            preStm.setString(2, dto.getRole());
            preStm.setString(3, dto.getUsername());
            checkUpdate = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }

    public boolean insert(RegistrationDTO dto) throws Exception {
        boolean checkInsert = false;
        try {
            String sql = "INSERT INTO Registration(USername, Password, Fullname, Role) VALUES(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getFullname());
            preStm.setString(4, dto.getRole());
            checkInsert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkInsert;
    }
}
