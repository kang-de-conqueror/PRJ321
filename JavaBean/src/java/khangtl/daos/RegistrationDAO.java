/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Peter
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDAO() {

    }

    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connString = "jdbc:sqlserver://localhost:1433;databaseName=SinhVien";
        String userDB = "sa";
        String passDB = "";
        String sql = "SELECT Role FROM Registration WHERE Username = ? AND Password = ?";
        try (
                Connection conn = DriverManager.getConnection(connString, userDB, passDB);
                PreparedStatement preStm = conn.prepareStatement(sql);) {
            preStm.setString(1, username);
            preStm.setString(2, password);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            try (ResultSet rs = preStm.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("Role");
                    System.out.println("Role = " + role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
}
