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
import khangtl.dtos.HistoriesDTO;

/**
 *
 * @author Peter
 */
public class HistoriesDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public HistoriesDAO() {
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

    public List<HistoriesDTO> viewDeviceHistoryByID(int id) throws Exception {
        List<HistoriesDTO> result = null;
        try {
            String sql = "SELECT dhr.CurrentRoomID, dhr.StartTime, dhr.PastRoomID, dhr.EndTime,"
                    + " u.UserFullname, dhr.MoveTime, dhr.MoveReason FROM dbo.device_histories_room dhr"
                    + " JOIN dbo.users u ON u.UserID = dhr.UserMoveID WHERE dhr.DeviceID = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int currentRoomID = rs.getInt("CurrentRoomID");
                int pastRoomID = rs.getInt("PastRoomID");
                String startTime = rs.getString("StartTime");
                String endTime = rs.getString("EndTime");
                String fullname = rs.getString("UserFullname");
                String moveTime = rs.getString("MoveTime");
                String moveReason = rs.getString("MoveReason");
                HistoriesDTO dto = new HistoriesDTO(currentRoomID, pastRoomID, startTime, endTime, fullname, moveTime, moveReason);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
