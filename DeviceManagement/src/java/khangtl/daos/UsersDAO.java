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
import khangtl.dtos.UsersDTO;

/**
 *
 * @author Peter
 */
public class UsersDAO implements Serializable {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public UsersDAO() {
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
    
    public UsersDTO checkLogin(String username, String password) throws Exception {
        UsersDTO dto = null;
        String sql = "SELECT u.UserFullname as Fullname, r.RoleName as Role FROM dbo.roles r JOIN dbo.users u ON r.RoleID = u.RoleID WHERE u.UserName = ? AND UserPassword = ?";
        try {
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("Fullname");
                String role = rs.getString("Role");
                dto = new UsersDTO(username, fullname, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public List<UsersDTO> findByLikeName(String search) throws Exception {
        List<UsersDTO> result = null;
        String username, fullname, role;
        UsersDTO dto = null;
        try {
            String sql = "SELECT u.UserName, u.UserFullname, r.RoleName as Role FROM dbo.roles r JOIN dbo.users u ON r.RoleID = u.RoleID WHERE u.UserFullname LIKE ? AND r.RoleID <> 1";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("UserName");
                fullname = rs.getString("UserFullname");
                role = rs.getString("Role");
                dto = new UsersDTO(username, fullname, role);
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
            String sql = "DELETE FROM dbo.users  WHERE UserName = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            checkDelete = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkDelete;
    }
    
    public UsersDTO findByPrimaryKey(String key) throws Exception {
        UsersDTO dto = null;
        try {
            String sql = "SELECT u.UserFullname, r.RoleName as Role FROM dbo.roles r JOIN dbo.users u ON r.RoleID = u.RoleID WHERE u.UserName = ? AND r.RoleID <> 1";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, key);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("UserFullname");
                String role = rs.getString("Role");
                dto = new UsersDTO(key, fullname, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public int findUserRoom(String username) throws Exception {
        int room = 0;
        try {
            String sql = "SELECT u.RoomID FROM dbo.users u WHERE u.UserName = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                room = rs.getInt("RoomID");
            }
        } finally {
            closeConnection();
        }
        return room;
    }
    
    public boolean updateUserRoom(String username, int newRoom) throws Exception {
        boolean checkUpdate = false;
        try {
            String sql = "UPDATE dbo.users SET RoomID = ? WHERE UserName = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, newRoom);
            preStm.setString(2, username);
            checkUpdate = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }
    
    public boolean update(UsersDTO dto) throws Exception {
        boolean checkUpdate = false;
        try {
            String sql = "UPDATE dbo.users SET UserFullname = ?, RoleID = (SELECT ro.RoleID FROM dbo.roles ro WHERE ro.RoleName = ?) WHERE UserName = ?";
            conn = MyConnection.getConnection();
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
    
    public boolean insert(UsersDTO dto) throws Exception {
        boolean checkInsert = false;
        try {
            String sql = "INSERT INTO dbo.users(UserName, UserPassword, UserFullname, RoleID) VALUES(?,?,?,(SELECT ro.RoleID FROM dbo.roles ro WHERE ro.RoleName = ?))";
            conn = MyConnection.getConnection();
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
