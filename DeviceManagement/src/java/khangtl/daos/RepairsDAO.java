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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import khangtl.db.MyConnection;
import khangtl.dtos.RepairsDTO;

/**
 *
 * @author Peter
 */
public class RepairsDAO implements Serializable {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public RepairsDAO() {
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
    
    public boolean sendRepair(int id, String username, String description, Date now) throws Exception {
        boolean checkSend = false;
        try {
            String sql = "INSERT INTO dbo.repairs(DeviceID, UserRequestID, RequestDescription, RequestTime)"
                    + " VALUES(?,(SELECT u.UserID FROM dbo.users u WHERE u.UserName = ?),?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            preStm.setString(2, username);
            preStm.setString(3, description);
            preStm.setTimestamp(4, new Timestamp(now.getTime()));
            checkSend = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkSend;
    }
    
    public boolean startRepair(String userRepair, int repairID) throws Exception {
        boolean checkStart = false;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        now = sf.parse(sf.format(now));
        try {
            String sql = "UPDATE dbo.repairs SET UserRepairID = (SELECT UserID FROM "
                    + "dbo.users WHERE UserName = ?), RepairStartTime = ? WHERE RepairID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userRepair);
            preStm.setTimestamp(2, new Timestamp(now.getTime()));
            preStm.setInt(3, repairID);
            checkStart = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkStart;
    }
    
    public boolean finishRepair(int repairID, String description, String result) throws Exception {
        boolean checkFinish = false;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        now = sf.parse(sf.format(now));
        try {
            String sql = "UPDATE dbo.repairs SET RepairEndTime = ?, RepairDescription = ?, RepairResult = ? WHERE RepairID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setTimestamp(1, new Timestamp(now.getTime()));
            preStm.setString(2, description);
            preStm.setString(3, result);
            preStm.setInt(4, repairID);
            checkFinish = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkFinish;
    }
    
    public boolean cancelRequest(int repairID) throws Exception {
        boolean checkCancel = false;
        try {
            String sql = "UPDATE dbo.repairs SET Canceled = 1 WHERE RepairID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, repairID);
            checkCancel = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkCancel;
    }
    
    public List<RepairsDTO> getHistoryRepair(int deviceID) throws Exception {
        List<RepairsDTO> result = null;
        RepairsDTO dto = null;
        int repairID;
        boolean canceled;
        String deviceName, userRequest, userRepair, requestTime, requestDescription, repairStartTime, repairEndTime, repairDescription, repairResult;
        try {
            String sql = "SELECT r.RepairID, (SELECT d.DeviceName FROM dbo.devices d WHERE d.DeviceID = r.DeviceID) DeviceName, "
                    + "(SELECT u.UserFullname FROM dbo.users u WHERE u.UserID = r.UserRequestID) UserRequest, r.RequestTime, "
                    + "r.RequestDescription, (SELECT u.UserFullname FROM dbo.users u WHERE u.UserID = r.UserRepairID) UserRepair, r.RepairStartTime, "
                    + "r.RepairEndTime, r.RepairDescription, r.RepairResult, r.Canceled FROM dbo.repairs r WHERE r.DeviceID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, deviceID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                repairID = rs.getInt("RepairID");
                deviceName = rs.getString("DeviceName");
                userRequest = rs.getString("UserRequest");
                userRepair = rs.getString("UserRepair");
                requestDescription = rs.getString("RequestDescription");
                requestTime = rs.getString("RequestTime");
                repairStartTime = rs.getString("RepairStartTime");
                repairEndTime = rs.getString("RepairEndTime");
                repairDescription = rs.getString("RepairDescription");
                repairResult = rs.getString("RepairResult");
                canceled = rs.getBoolean("Canceled");
                dto = new RepairsDTO(repairID, canceled, deviceName, requestDescription, userRequest, userRepair, requestTime, repairStartTime, repairEndTime, repairDescription, repairResult);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<RepairsDTO> viewAllRepairRequest() throws Exception {
        List<RepairsDTO> result = null;
        int repairID;
        String deviceName, userRequest, requestTime, requestDescription, startTime, endTime;
        boolean canceled;
        RepairsDTO dto = null;
        try {
            String sql = "SELECT r.RepairID, (SELECT d.DeviceName FROM dbo.devices d WHERE d.DeviceID = r.DeviceID) "
                    + "DeviceName, (SELECT u.UserFullname FROM dbo.users u WHERE u.UserID = r.UserRequestID) UserRequest, r.RequestTime, "
                    + "r.RequestDescription, r.RepairStartTime, r.RepairEndTime, r.Canceled FROM dbo.repairs r";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                repairID = rs.getInt("RepairID");
                deviceName = rs.getString("DeviceName");
                userRequest = rs.getString("UserRequest");
                requestTime = rs.getString("RequestTime");
                requestDescription = rs.getString("RequestDescription");
                startTime = rs.getString("RepairStartTime");
                endTime = rs.getString("RepairEndTime");
                canceled = rs.getBoolean("Canceled");
                dto = new RepairsDTO(repairID, canceled, deviceName, requestDescription, userRequest, requestTime, startTime, endTime);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<RepairsDTO> viewRequestByUser(String username) throws Exception {
        List<RepairsDTO> result = null;
        int repairID;
        String deviceName, userRequest, userRepair, requestTime, requestDescription, startTime, endTime, repairDescription, repairResult;
        boolean canceled;
        RepairsDTO dto = null;
        try {
            String sql = "SELECT r.RepairID, (SELECT d.DeviceName FROM dbo.devices d  WHERE d.DeviceID = r.DeviceID) DeviceName, "
                    + "(SELECT u.UserFullname FROM dbo.users u WHERE u.UserID = (SELECT u.UserID FROM dbo.users u WHERE u.UserName = ?)) "
                    + "UserRequest, r.RequestTime, r.RequestDescription, (SELECT u.UserFullname FROM dbo.users u WHERE u.UserID = r.UserRepairID) "
                    + "UserRepair, r.RepairStartTime, r.RepairEndTime, r.Canceled, r.RepairResult, r.RepairDescription FROM dbo.repairs r";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                repairID = rs.getInt("RepairID");
                deviceName = rs.getString("DeviceName");
                userRequest = rs.getString("UserRequest");
                userRepair = rs.getString("UserRepair");
                requestTime = rs.getString("RequestTime");
                requestDescription = rs.getString("RequestDescription");
                startTime = rs.getString("RepairStartTime");
                endTime = rs.getString("RepairEndTime");
                canceled = rs.getBoolean("Canceled");
                repairDescription = rs.getString("RepairDescription");
                repairResult = rs.getString("RepairResult");
                dto = new RepairsDTO(repairID, canceled, deviceName, requestDescription, userRequest, userRepair, requestTime, startTime, endTime, repairDescription, repairResult);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
