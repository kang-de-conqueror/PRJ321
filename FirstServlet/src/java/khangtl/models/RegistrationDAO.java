/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import khangtl.db.MyConnection;
import khangtl.dtos.RegistrationDTO;

/**
 *
 * @author Peter
 */
public class RegistrationDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegistrationDAO() {
    }

    public String checkLogin(String username, String password) {
        String role = "failed";
        try {
            String sql = "SELECT Role FROM Registration WHERE Username = ? AND Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return role;
    }

    public List<RegistrationDTO> findByLikeName(String search) {
        List<RegistrationDTO> result = null;
        String username = null;
        String fullname = null;
        String role = null;
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(String username) {
        boolean checkDelete = false;
        try {
            String sql = "DELETE FROM Registration WHERE Username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            checkDelete = preStm.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return checkDelete;
    }
}
