package com.mka.lesson1.dz;

import java.util.ArrayList;
import java.util.List;

public class AppFruit {
    public static void main(String[] args) {
        Box<Apple> box1 = new Box<Apple>(1.0);
        box1.set(new Apple());
        box1.set(new Apple());
        box1.set(new Apple());

        Box<Orange> box2 = new Box<Orange>(1.5);
        box2.set(new Orange());
        box2.set(new Orange());
        box2.set(new Orange());

        /*System.out.println(box1.getWeight());
        System.out.println(box2.getWeight());*/

        /*System.out.println(box2.compare(box1));
        System.out.println(box2.compare(box2));*/

        //box2.broadcastFruit(box1); // нельзя ;)

        Box<Orange> box3 = new Box<Orange>(1.5);
        box3.set(new Orange());
        box3.set(new Orange());
        box3.set(new Orange());
        box3.set(new Orange());

        System.out.println(box3.getWeight());
        System.out.println(box2.getWeight());

        box2.broadcastFruit(box3);

        System.out.println("------");
        System.out.println(box3.getWeight()); // добавляем
        System.out.println(box2.getWeight()); // забираем
    }
}

class Box<T> {

    private double weightFruit;
    private final List<T> arFruit = new ArrayList();

    public Box(double weightFruit) {
        this.weightFruit = weightFruit;
    }

    void set(T fruit) {
        arFruit.add(fruit);
    }

    double getWeight() {
        return arFruit.size() * weightFruit;
    }

    boolean compare(Box<?> another) {
        return getWeight() == another.getWeight();
    }

    void broadcastFruit(Box<T> another) {
        for (T fruit : arFruit) {
            another.set(fruit);
        }
        arFruit.clear();
    }
}