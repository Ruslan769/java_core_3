package com.mka.lesson5;

import java.util.concurrent.CountDownLatch;

/*
 * CountDownLatch ​позволяет потоку ожидать завершения операций, выполняющихся в других потоках.
 * Режим ожидания запускается методом await()​. При создании объекта определяется количество
 * требуемых операций, после чего уменьшается при вызове метода countDown()​. Как только счетчик
 * доходит до нуля, с ожидающего потока снимается блокировка.
 */

public class CarApp {

    public static final CountDownLatch LATCH = new CountDownLatch(8);
    public static final int LENGTH_TRACK = 500_000;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            new Thread(new Car(i, (int) Math.random() * 100 + 50)).start();
        }

        Thread.sleep(1000);
        System.out.println("На старт");
        LATCH.countDown();

        Thread.sleep(1000);
        System.out.println("Внимание");
        LATCH.countDown();

        Thread.sleep(1000);
        System.out.println("Марш");
        LATCH.countDown();
    }

    private static final class Car implements Runnable {

        private int numCar;
        private int speedCar;

        public Car(int numCar, int speedCar) {
            this.numCar = numCar;
            this.speedCar = speedCar;
        }

        @Override
        public void run() {
            try {
                System.out.println("Автомобиль номер = " + numCar + " подъехал к стартовой прямой");
                LATCH.countDown();

                LATCH.await();
                Thread.sleep(LENGTH_TRACK / speedCar);
                System.out.println("Автомобиль номер = " + numCar + " финишировал");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
