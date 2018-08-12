package com.mka.lesson2.dz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static final String DB_NAME = "jdbc:sqlite:dbproduct";
    private static Connection conn;

    public static Connection DBConnection() {
        try {
            conn = DriverManager.getConnection(DB_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
