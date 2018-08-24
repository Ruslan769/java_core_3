package com.mka.lesson5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
* CyclicBarrier​ выполняет ​синхронизацию заданного количества потоков в одной точке. Как только
* заданное количество потоков заблокировалось (вызовами метода await()​), с них одновременно
* снимается блокировка.
* */

public class FerryApp {

    public static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(3, new FerryBoat());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(1000);
        }
    }

    public static final class FerryBoat implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Паром переправил автомобили");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static final class Car implements Runnable {

        private int numCar;

        public Car(int numCar) {
            this.numCar = numCar;
        }

        @Override
        public void run() {
            try {
                System.out.println("Автомобиль номер = " + numCar + " подъехал к паромной переправе");
                CYCLIC_BARRIER.await();
                System.out.println("Автомобиль номер = " + numCar + " продолжил движение");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
