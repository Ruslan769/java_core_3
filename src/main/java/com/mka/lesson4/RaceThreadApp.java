package com.mka.lesson4;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceThreadApp {

    public static void main(String[] args) throws InterruptedException {

        CounterApp counterApp = new CounterApp();

        Thread t1 = new Thread(new CounterThread(counterApp));
        Thread t2 = new Thread(new CounterThread(counterApp));
        Thread t3 = new Thread(new CounterThread(counterApp));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(counterApp.get());
    }
}

//class CounterApp {
//
//    private int count;
//
//    // блокировка на класс, т.е. на все его экземпляры.
//    public static synchronized void say() {
//        System.out.println("Salam");
//    }
//
//    // блокировка на объект this. Т.е. на экземпляр класса.
//    public synchronized void increase() {
//        count ++;
//    }
//
//    // блокировка на объект, который указан в блоке synchronized
///*    public void increase() {
//        synchronized(count) {
//            count ++;
//        }
//    }*/
//
//    public int get() {
//        return count;
//    }
//}

class CounterApp {

    private AtomicInteger count;

    public CounterApp() {
        this.count = new AtomicInteger();
    }

    public void increase() {
        count.incrementAndGet();
    }

    public int get() {
        return count.get();
    }
}

class CounterThread implements Runnable {

    CounterApp counter;

    public CounterThread(CounterApp counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            counter.increase();
        }
    }
}