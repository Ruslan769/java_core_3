package com.mka.lesson5.dz;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;


public class MainApp {

    public static final int CARS_COUNT = 4;
    public static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(CARS_COUNT, new RaceStartNotif());
    public static final Semaphore SEMAPHORE = new Semaphore(CARS_COUNT / 2);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        Thread[] th = new Thread[cars.length];
        for (int i = 0; i < cars.length; i++) {
            th[i] = new Thread(cars[i]);
        }
        for (int i = 0; i < th.length; i++) {
            th[i].start();
        }
        for (int i = 0; i < th.length; i++) {
            th[i].join();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    public static final class RaceStartNotif implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
