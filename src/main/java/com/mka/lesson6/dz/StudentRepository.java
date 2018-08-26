package com.mka.lesson6.dz;

import java.sql.*;

public class StudentRepository {

    private final String TABLE_NAME = "STUDENTS";
    private final String QUERY_ID = "ID";
    private final String QUERY_SURNAME = "SURNAME";
    private final String QUERY_SCORE = "SCORE";
    private final String QUERY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " +
            "(" + QUERY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            QUERY_SURNAME + " TEXT NOT NULL, " +
            QUERY_SCORE + " INT)";
    private final String QUERY_INSERT = "INSERT INTO " + TABLE_NAME + " (" + QUERY_SURNAME + ", " + QUERY_SCORE + ") VALUES (?, ?)";
    private final String QUERY_UPDATE = "UPDATE " + TABLE_NAME + " SET " + QUERY_SURNAME + " = ?, " + QUERY_SCORE + " = ? WHERE " + QUERY_ID + " = ?";
    private final String QUERY_SELECT_BY_SURNAME = "SELECT * FROM " + TABLE_NAME + " WHERE " + QUERY_SURNAME + " = ? LIMIT 1";

    private Connection conn;
    private Statement st;
    private PreparedStatement pSt;

    public StudentRepository() {
        createTable();
    }

    public void createTable() {
        try {
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute(QUERY_TABLE_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int addStudent(Student entity) {
        int result = 0;
        try {
            conn = MyConnection.getConnection();
            pSt = conn.prepareStatement(QUERY_INSERT);
            pSt.setString(1, entity.getSurname());
            pSt.setInt(2, entity.getScore());
            result = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pSt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int update(Student entity) {
        int result = 0;
        try {
            conn = MyConnection.getConnection();
            pSt = conn.prepareStatement(QUERY_UPDATE);
            pSt.setString(1, entity.getSurname());
            pSt.setInt(2, entity.getScore());
            pSt.setInt(3, entity.getId());
            result = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pSt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int getScore(final String surname) {
        int score = 0;

        try {
            conn = MyConnection.getConnection();
            pSt = conn.prepareStatement(QUERY_SELECT_BY_SURNAME);
            pSt.setString(1, surname);
            final ResultSet arTable = pSt.executeQuery();
            while (arTable.next()) {
                score = arTable.getInt(QUERY_SCORE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pSt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return score;
    }
}
