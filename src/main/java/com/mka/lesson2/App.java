package com.mka.lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    // протокол:подпротокол:имя_бд
    // протокол:подпротокол://localhost:3306/имя_бд?
    // IF NOT EXISTS если нет таблицы создавай

    private static Connection conn;
    private static Statement st;

    public static void main(String[] args) {

        //createTable();

        PersonRepository personRepository = new PersonRepository();

        // insert
        /*Person personInsert = new Person(11, 22, "Sergey", 4000.20f);
        personRepository.insert(personInsert);*/

        // update
        /*Person personGet = personRepository.get(10);
        System.out.println(personGet);
        personGet.setAge(29);
        personGet.setName("Nikita");
        personGet.setSalary(4900.20f);
        personRepository.update(personGet);

        Person personGet2 = personRepository.get(10);
        System.out.println(personGet2);*/

        // delete
        //personRepository.delete(11);

        // get All
        //System.out.println(personRepository.get().size());

        // drop table
        //personRepository.dropTable();

        // insertBatch
        //personRepository.insertBatch();
    }

    public static void createTable() {
        // автозакрытие
        /*try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db_java")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:db_java");
            final String query = "CREATE TABLE IF NOT EXISTS PERSON " +
                    "( ID INT PRIMARY KEY NOT NULL, " +
                    "NAME TEXT NOT NULL, " +
                    "AGE INT NOT NULL, " +
                    "SALARY REAL )";

            st = conn.createStatement();
            st.execute(query);

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
