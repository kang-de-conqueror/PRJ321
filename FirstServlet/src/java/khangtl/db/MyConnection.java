/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Peter
 */
public class MyConnection implements Serializable {
    public static Connection getMyConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Sinhvien", "sa", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
