package com.mka.lesson1.dz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {

    }

    private <T> void swap(T[] arr, int first, int second) {
        T tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }

    private <T> ArrayList<T> arrayToList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }
}

interface Fruit {
    float getWeight();
}

class Apple implements Fruit {

    public float getWeight() {
        return 1.0f;
    }
}

class Orange implements Fruit {

    public float getWeight() {
        return 1.5f;
    }
}

class Box<T extends Fruit> {

    final List<T> listFruit = new ArrayList<T>();

    void add(T fruit) {
        listFruit.add(fruit);
    }

    float getWeight() {
        return listFruit.get(0).getWeight() * listFruit.size();
    }

    boolean compare(Box<?> box) {
        return getWeight() == box.getWeight();
    }

    void pour(Box<T> another) {
        another.listFruit.addAll(listFruit);
        listFruit.clear();
    }
}