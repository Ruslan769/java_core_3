package com.mka.lesson2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PersonRepository implements IRepository<Person> {

    private final String TABLE_NAME = "PERSON";
    private final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private final String QUERY_INSERT = "INSERT INTO " + TABLE_NAME + " (ID, NAME, AGE, SALARY) VALUES (?, ?, ?, ?)";
    private final String QUERY_SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
    private final String QUERY_SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ? LIMIT 1";
    private final String QUERY_UPDATE = "UPDATE " + TABLE_NAME + " set NAME = ?, AGE = ?, SALARY = ? WHERE id = ?";
    private final String QUERY_DELETE_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

    private Connection conn;
    private PreparedStatement st;

    @Override
    public void insert(Person entity) {
        try {
            conn = MyConnection.getConnection();
            st = conn.prepareStatement(QUERY_INSERT);
            st.setInt(1, entity.getId());
            st.setString(2, entity.getName());
            st.setInt(3, entity.getAge());
            st.setFloat(4, entity.getSalary());

            st.executeUpdate();
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

    @Override
    public void update(Person entity) {
        try {
            conn = MyConnection.getConnection();
            st = conn.prepareStatement(QUERY_UPDATE);
            st.setString(1, entity.getName());
            st.setInt(2, entity.getAge());
            st.setFloat(3, entity.getSalary());
            st.setInt(4, entity.getId());
            st.executeUpdate();
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

    @Override
    public void delete(int id) {
        try {
            conn = MyConnection.getConnection();
            st = conn.prepareStatement(QUERY_DELETE_BY_ID);
            st.setInt(1, id);
            st.executeUpdate();
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

    @Override
    public Person get(int id) {
        Person person = null;
        try {
            conn = MyConnection.getConnection();
            st = conn.prepareStatement(QUERY_SELECT_BY_ID);
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery(); // execute return boolean, executeQuery return ResultSet
            while (resultSet.next()) {
                person = new Person(resultSet.getInt("ID"),
                        resultSet.getInt("AGE"),
                        resultSet.getString("NAME"),
                        resultSet.getFloat("Salary"));
            }
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
        return person;
    }

    @Override
    public List<Person> get() {
        final List<Person> arPerson = new LinkedList<>();
        try {
            conn = MyConnection.getConnection();
            st = conn.prepareStatement(QUERY_SELECT_ALL);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                final Person person = new Person(resultSet.getInt("ID"),
                        resultSet.getInt("AGE"),
                        resultSet.getString("NAME"),
                        resultSet.getFloat("Salary"));
                arPerson.add(person);
            }
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
        return arPerson;
    }

    void insertBatch() {
        try {
            // JDBC делает commit в каждой операции для того чтобы отключить вызываем метод setAutoCommit ставим false
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);

            st = conn.prepareStatement(QUERY_INSERT);
            for (int i = 1; i < 10000; i++) {
                st.setInt(1, i);
                st.setString(2, "Name_" + i);
                st.setInt(3, i + 10);
                st.setFloat(4, i + 2000.f);
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.commit(); // сохраняем изменения в БД
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void dropTable() {
        try {
            conn = MyConnection.getConnection();
            st = conn.prepareStatement(TABLE_DROP);
            st.execute();
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
}
