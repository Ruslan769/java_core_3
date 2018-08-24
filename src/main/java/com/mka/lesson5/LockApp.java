package com.mka.lesson5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockApp {

    public static void main(String[] args) {
        //Lock lock = new ReentrantLock();
        final DeadLock deadLock = new DeadLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                deadLock.lock1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                deadLock.lock2();
            }
        }).start();
    }
}

class DeadLock {
    Object obj1 = new Object();
    Object obj2 = new Object();

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    void lock1() {
        if (lock1.tryLock()) {
            try {
                obj1 = "lock1";
                System.out.println(Thread.currentThread().getName() + " получила блокировку на obj1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " пытается зайти в obj2");
                if (lock2.tryLock()) {
                    try {
                        obj2 = "lock1";
                        System.out.println(Thread.currentThread().getName() + " получила блокировку на obj2");
                    } finally {
                        lock2.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " не получил монитор obj2");
                }
            } finally {
                lock1.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " не получил монитор obj1");
        }
    }

    void lock2() {
        if (lock2.tryLock()) {
            try {
                obj2 = "lock2";
                System.out.println(Thread.currentThread().getName() + " получила блокировку на obj2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " пытается зайти в obj1");
                if (lock1.tryLock()) {
                    try {
                        obj1 = "lock2";
                        System.out.println(Thread.currentThread().getName() + " получила блокировку на obj1");
                    } finally {
                        lock1.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " не получил монитор obj1");
                }
            } finally {
                lock2.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " не получил монитор obj2");
        }
    }
}
