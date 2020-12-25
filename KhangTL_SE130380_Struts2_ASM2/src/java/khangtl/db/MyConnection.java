package khangtl.db;


import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Peter
 */
public class MyConnection {

    public static Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;database=BlogDB";
        Connection conn = DriverManager.getConnection(url, "sa", "");
        return conn;
    }
}
