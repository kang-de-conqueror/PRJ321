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
import khangtl.dtos.DevicesDTO;

/**
 *
 * @author Peter
 */
public class DevicesDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public DevicesDAO() {
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

    public List<DevicesDTO> findByLikeName(String search) throws Exception {
        List<DevicesDTO> result = null;
        int deviceID;
        String deviceName, deviceDescription, deviceType, deviceState, deviceImage, buyDate, guaranteeDuration;
        DevicesDTO dto;
        try {
            String sql = "SELECT DeviceID, DeviceName, DeviceDescription, DeviceType, DeviceState, DeviceImage, BuyDate, GuaranteeDuration FROM dbo.devices WHERE DeviceName LIKE ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                deviceID = rs.getInt("DeviceID");
                deviceName = rs.getString("DeviceName");
                deviceDescription = rs.getString("DeviceDescription");
                deviceType = rs.getString("DeviceType");
                deviceState = rs.getString("DeviceState");
                deviceImage = rs.getString("DeviceImage");
                buyDate = rs.getString("BuyDate");
                guaranteeDuration = rs.getString("GuaranteeDuration");
                dto = new DevicesDTO(deviceID, deviceName, deviceDescription, deviceType, deviceState, deviceImage, guaranteeDuration, buyDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<DevicesDTO> findFollowRoom(String username, String search) throws Exception {
        List<DevicesDTO> result = null;
        int deviceID;
        String deviceName, deviceDescription, deviceType, deviceState, deviceImage, buyDate, guaranteeDuration, roomName;
        DevicesDTO dto;
        try {
            String sql = "SELECT d.DeviceID, d.DeviceName, d.DeviceDescription, d.DeviceType, d.DeviceState, d.DeviceImage, d.BuyDate, d.GuaranteeDuration, r.RoomName"
                    + " FROM dbo.devices d JOIN dbo.device_histories_room dhr"
                    + " ON dhr.DeviceID = d.DeviceID JOIN dbo.rooms r"
                    + " ON r.RoomID = dhr.CurrentRoomID JOIN dbo.users u"
                    + " ON u.RoomID = r.RoomID WHERE u.UserName = ? AND d.DeviceName LIKE ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                deviceID = rs.getInt("DeviceID");
                deviceName = rs.getString("DeviceName");
                deviceDescription = rs.getString("DeviceDescription");
                deviceType = rs.getString("DeviceType");
                deviceState = rs.getString("DeviceState");
                deviceImage = rs.getString("DeviceImage");
                buyDate = rs.getString("BuyDate");
                guaranteeDuration = rs.getString("GuaranteeDuration");
                roomName = rs.getString("RoomName");
                dto = new DevicesDTO(deviceID, deviceName, deviceDescription, deviceType, deviceState, deviceImage, guaranteeDuration, buyDate, roomName);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<DevicesDTO> findByDate(String from, String to) throws Exception {
        List<DevicesDTO> result = null;
        int deviceID;
        String deviceName, deviceDescription, deviceType, deviceState, deviceImage, buyDate, guaranteeDuration;
        DevicesDTO dto;
        try {
            String sql = "SELECT d.DeviceID, d.DeviceName, d.DeviceDescription, d.DeviceType, d.DeviceState, "
                    + "d.DeviceImage, d.BuyDate, d.GuaranteeDuration FROM dbo.devices d WHERE d.BuyDate > ? AND d.BuyDate < ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, from);
            preStm.setString(2, to);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                deviceID = rs.getInt("DeviceID");
                deviceName = rs.getString("DeviceName");
                deviceDescription = rs.getString("DeviceDescription");
                deviceType = rs.getString("DeviceType");
                deviceState = rs.getString("DeviceState");
                deviceImage = rs.getString("DeviceImage");
                buyDate = rs.getString("BuyDate");
                guaranteeDuration = rs.getString("GuaranteeDuration");
                dto = new DevicesDTO(deviceID, deviceName, deviceDescription, deviceType, deviceState, deviceImage, guaranteeDuration, buyDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<DevicesDTO> findByRepairQuantity(int quantity) throws Exception {
        List<DevicesDTO> result = null;
        int deviceID;
        String deviceName, deviceDescription, deviceType, deviceState, deviceImage, buyDate, guaranteeDuration;
        DevicesDTO dto;
        try {
            String sql = "SELECT DISTINCT d.DeviceID, d.DeviceName, d.DeviceDescription, d.DeviceType, d.DeviceState, "
                    + "d.DeviceImage, d.BuyDate, d.GuaranteeDuration FROM dbo.devices d INNER JOIN dbo.repairs r ON r.DeviceID = d.DeviceID "
                    + "GROUP BY d.DeviceID, d.DeviceName, d.DeviceDescription, d.DeviceType, d.DeviceState, "
                    + "d.DeviceImage, d.BuyDate, d.GuaranteeDuration HAVING COUNT(r.DeviceID) = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quantity);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                deviceID = rs.getInt("DeviceID");
                deviceName = rs.getString("DeviceName");
                deviceDescription = rs.getString("DeviceDescription");
                deviceType = rs.getString("DeviceType");
                deviceState = rs.getString("DeviceState");
                deviceImage = rs.getString("DeviceImage");
                buyDate = rs.getString("BuyDate");
                guaranteeDuration = rs.getString("GuaranteeDuration");
                dto = new DevicesDTO(deviceID, deviceName, deviceDescription, deviceType, deviceState, deviceImage, guaranteeDuration, buyDate);
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
            String sql = "DELETE FROM dbo.devices WHERE DeviceID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            checkDelete = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkDelete;
    }

    public DevicesDTO findByPrimaryKey(int key) throws Exception {
        DevicesDTO dto = null;
        try {
            String sql = "SELECT DeviceName, DeviceDescription, DeviceType, DeviceState, DeviceImage, BuyDate, GuaranteeDuration FROM dbo.devices WHERE DeviceID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, key);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String deviceName = rs.getString("DeviceName");
                String deviceDescription = rs.getString("DeviceDescription");
                String deviceType = rs.getString("DeviceType");
                String deviceState = rs.getString("DeviceState");
                String deviceImage = rs.getString("DeviceImage");
                String buyDate = rs.getString("BuyDate");
                String guaranteeDuration = rs.getString("GuaranteeDuration");
                dto = new DevicesDTO(key, deviceName, deviceDescription, deviceType, deviceState, deviceImage, guaranteeDuration, buyDate);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean update(DevicesDTO dto) throws Exception {
        boolean checkUpdate = false;
        try {
            String sql = "UPDATE dbo.devices SET DeviceName = ?, DeviceDescription = ?, DeviceType = ?,"
                    + "DeviceState = ?, DeviceImage = ?, BuyDate = ?, GuaranteeDuration = ? WHERE DeviceID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getDeviceName());
            preStm.setString(2, dto.getDeviceDescription());
            preStm.setString(3, dto.getDeviceType());
            preStm.setString(4, dto.getDeviceState());
            preStm.setString(5, dto.getDeviceImage());
            preStm.setString(6, dto.getBuyDate());
            preStm.setString(7, dto.getGuaranteeDuration());
            preStm.setInt(8, dto.getDeviceID());
            checkUpdate = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }

    public boolean insert(DevicesDTO dto) throws Exception {
        boolean checkInsert = false;
        try {
            String sql = "INSERT INTO dbo.devices(DeviceName, DeviceDescription, DeviceType, DeviceState, DeviceImage, BuyDate, GuaranteeDuration) VALUES(?,?,?,?,?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getDeviceName());
            preStm.setString(2, dto.getDeviceDescription());
            preStm.setString(3, dto.getDeviceType());
            preStm.setString(4, dto.getDeviceState());
            preStm.setString(5, dto.getDeviceImage());
            preStm.setString(6, dto.getBuyDate());
            preStm.setString(7, dto.getGuaranteeDuration());
            checkInsert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkInsert;
    }
}
