package com.company.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static Connection conn = null;
    public static Connection getConnection()
    {
        try {
            if(conn == null || conn.isClosed())
            {
                connectionInitialize();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    private static void connectionInitialize()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/CricketMatches";
            conn = DriverManager.getConnection(url, "root", "Parth@01257");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection()
    {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
