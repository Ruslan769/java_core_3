package com.mka.lesson3;

import java.io.*;

public class SerializeStreamApp {

    public static void main(String[] args) {
        serial();
        dSerial();
    }

    public static void serial() {
        Salary salary = new Salary(90000);
        Person person = new Person("Sergey", 23, salary);
        FileOutputStream fStream = null;
        ObjectOutputStream oStream = null;
        try {
            fStream = new FileOutputStream("./src/main/java/com/mka/lesson3/personObj");
            oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(person);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dSerial() {
        FileInputStream fStream = null;
        ObjectInputStream oStream = null;
        try {
            fStream = new FileInputStream("./src/main/java/com/mka/lesson3/personObj");
            oStream = new ObjectInputStream(fStream);
            Person person = (Person) oStream.readObject();
            oStream.close();
            System.out.println(person.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Salary implements Serializable {
    int amount;

    public Salary(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "amount=" + amount +
                '}';
    }
}

class Person implements Serializable {

    private String name;
    private transient int age; // не будет сериализован
    private Salary salary;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Salary salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
