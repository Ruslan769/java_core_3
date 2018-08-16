package com.mka.lesson3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonApp {

    private static Connection instance;

    private SingletonApp() {}

    public static Connection getInstanse() {
        if (instance == null) {
            try {
                instance = DriverManager.getConnection("jdbc:sqlite:db_java");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
