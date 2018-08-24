package com.mka.lesson5;

import java.util.concurrent.Semaphore;

/*
* Semaphore ​ограничивает количество потоков при работе с ресурсами. Для этого служит счетчик.
* Если его значение больше нуля, то потоку разрешается доступ, а значение уменьшается. Если
* счетчик равен нулю, то текущий поток блокируется до освобождения ресурса. Для получения доступа
* используется метод acquire()​, для освобождения – release()​.
* */

public class ParkingApp {

    public static final boolean[] PARKING_PLACE = new boolean[5];
    public static final Semaphore SEMAPHORE = new Semaphore(5);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 7; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(100);
        }
    }

    private static class Car implements Runnable {

        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            System.out.println("Автомобиль номер = " + carNumber + " подъехал к порковке");
            try {
                SEMAPHORE.acquire();

                int parcNumber = -1;
                synchronized (PARKING_PLACE) {
                    for (int i = 0; i < PARKING_PLACE.length; i++) {
                        if (!PARKING_PLACE[i]) {
                            PARKING_PLACE[i] = true;
                            parcNumber = i;
                            System.out.println("Автомобиль номер = " + carNumber + " припоркован к месту = " + parcNumber);
                            break;
                        }
                    }
                }
                Thread.sleep(5000);
                PARKING_PLACE[parcNumber] = false;
                System.out.println("Автомобиль номер = " + carNumber + " выехал с места = " + parcNumber);

                SEMAPHORE.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
