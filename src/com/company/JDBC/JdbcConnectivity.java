package com.company.JDBC;
import java.sql.*;
public class JdbcConnectivity {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/testDB";
            Connection conn = DriverManager.getConnection(url,"root","Parth");
            Statement stmt = conn.createStatement();
            String query = "select * from Persons";

            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            String name = rs.getString("FirstName");
            System.out.println(name);
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
