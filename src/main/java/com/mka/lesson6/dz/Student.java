package com.mka.lesson6.dz;

public class Student {

    private int id;
    private String surname;
    private int score;

    public Student(String surname, int score) {
        this.surname = surname;
        this.score = score;
    }

    public Student(int id, String surname, int score) {
        this.id = id;
        this.surname = surname;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public int getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", score=" + score +
                '}';
    }
}
