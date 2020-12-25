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
import khangtl.dtos.UpdatesDTO;

/**
 *
 * @author Peter
 */
public class UpdatesDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public UpdatesDAO() {
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

    public boolean newUpdate(UpdatesDTO dto) throws Exception {
        boolean checkUpdate = false;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        now = sf.parse(sf.format(now));
        try {
            String sql = "UPDATE dbo.updates SET UserID = (SELECT u.UserID FROM dbo.users u WHERE u.UserName = ?), UpdateContent = ?, UpdateTime = ?";
            conn = MyConnection.getConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getUpdateContent());
            preStm.setTimestamp(3, new Timestamp(now.getTime()));
            checkUpdate = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }

}
