package com.mka.lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private static Connection conn;

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db_java");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
