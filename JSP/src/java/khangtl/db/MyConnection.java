/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Peter
 */
public class MyConnection {

    public static Connection getMyConnection() throws ClassNotFoundException, SQLException, Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Sinhvien";
        Connection conn = DriverManager.getConnection(url, "sa", "");
        return conn;
    }
}
