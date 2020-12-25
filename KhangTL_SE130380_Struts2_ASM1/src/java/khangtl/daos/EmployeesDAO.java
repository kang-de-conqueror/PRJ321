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
import khangtl.dtos.EmployeesDTO;

/**
 *
 * @author Peter
 */
public class EmployeesDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public EmployeesDAO() {
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

    public boolean checkLogin(String username, String password) throws Exception {
        boolean check = false;
        try {
            String sql = "SELECT e.EmpId, e.EmpName, e.Address FROM TBL_emp e WHERE EmpId = ? AND PassWord = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<EmployeesDTO> getListEmployee() throws Exception {
        List<EmployeesDTO> result = null;
        EmployeesDTO dto = null;
        String id, name, address;
        try {
            String sql = "SELECT e.EmpId, e.EmpName, e.Address FROM TBL_emp e";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("EmpId");
                name = rs.getString("EmpName");
                address = rs.getString("Address");
                dto = new EmployeesDTO(id, name, address);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public EmployeesDTO getEmployeeByID(String id) throws Exception {
        String name, address;
        EmployeesDTO dto = null;
        try {
            String sql = "SELECT e.EmpId, e.EmpName, e.Address FROM TBL_emp e WHERE e.EmpId = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getString("EmpId");
                name = rs.getString("EmpName");
                address = rs.getString("Address");
                dto = new EmployeesDTO(id, name, address);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public boolean updateEmployeeByID(EmployeesDTO dto) throws Exception {
        boolean checkUpdate;
        try {
            String sql = "UPDATE TBL_emp SET PassWord = ?, EmpName = ?, Address = ? WHERE EmpId = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getPassword());
            preStm.setString(2, dto.getName());
            preStm.setString(3, dto.getAddress());
            preStm.setString(4, dto.getId());
            checkUpdate = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }
}
