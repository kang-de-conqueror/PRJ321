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
import java.util.Date;
import khangtl.db.MyConnection;

/**
 *
 * @author Peter
 */
public class DevicesRoomsDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public DevicesRoomsDAO() {
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

    public int getCurrentRoom(int deviceID) throws Exception {
        int result = -1;
        try {
            String sql = "SELECT dhr.CurrentRoomID FROM dbo.device_histories_room dhr WHERE dhr.StartTime = "
                    + "(SELECT MAX(StartTime) FROM dbo.device_histories_room WHERE DeviceID = ?) AND DeviceID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, deviceID);
            preStm.setInt(2, deviceID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("CurrentRoomID");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertRoom(int deviceID, int newRoom) throws Exception {
        boolean checkInsert = false;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        startTime = sf.parse(sf.format(startTime));
        try {
            String sql = "INSERT INTO dbo.device_histories_room(DeviceID, CurrentRoomID, StartTime) VALUES(?,?,?)";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, deviceID);
            preStm.setInt(2, newRoom);
            preStm.setTimestamp(3, new Timestamp(startTime.getTime()));
            checkInsert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkInsert;
    }

    public boolean changeRoom(int deviceID, int newRoom, String username, String reason) throws Exception {
        boolean checkChange = false;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTime = new Date();
        startTime = sf.parse(sf.format(startTime));
        try {
            String sql = "BEGIN UPDATE dbo.device_histories_room SET EndTime = ?"
                    + " WHERE StartTime = (SELECT MAX(StartTime) FROM dbo.device_histories_room WHERE DeviceID = ?) AND DeviceID = ?;"
                    + " INSERT INTO dbo.device_histories_room(DeviceID, CurrentRoomID, StartTime, PastRoomID, UserMoveID, MoveReason, MoveTime)"
                    + " VALUES(?,?,?, (SELECT dhr.CurrentRoomID FROM dbo.device_histories_room dhr"
                    + " WHERE StartTime = (SELECT MAX(StartTime) FROM dbo.device_histories_room WHERE DeviceID = ?) AND DeviceID = ?),"
                    + " (SELECT u.UserID FROM dbo.users u WHERE u.UserName = ?), ?, ?); END";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setTimestamp(1, new Timestamp(startTime.getTime()));
            preStm.setInt(2, deviceID);
            preStm.setInt(3, deviceID);
            preStm.setInt(4, deviceID);
            preStm.setInt(5, newRoom);
            preStm.setTimestamp(6, new Timestamp(startTime.getTime()));
            preStm.setInt(7, deviceID);
            preStm.setInt(8, deviceID);
            preStm.setString(9, username);
            preStm.setString(10, reason);
            preStm.setTimestamp(11, new Timestamp(startTime.getTime()));
            checkChange = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkChange;
    }
}
