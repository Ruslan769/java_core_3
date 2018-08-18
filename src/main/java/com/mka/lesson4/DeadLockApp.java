package com.mka.lesson4;

public class DeadLockApp {

    Account account;

    {
        account = new Account();
    }

    public static void main(String[] args) {

        DeadLockApp deadLockApp = new DeadLockApp();
        deadLockApp.init();
    }

    public void init() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposite();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposite2();
            }
        }).start();
    }
}

class Account {

    final Object object1 = new Object();
    final Object object2 = new Object();

    public void deposite() {
        synchronized (object1) {
            System.out.println("Нить 1 захватила первый ресурс");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Нить 1 пытается захватить второй ресурс");
            synchronized (object2) {
                System.out.println("Нить 1 захватила второй ресурс");
            }
        }
    }

    public void deposite2() {
        synchronized (object2) {
            System.out.println("Нить 2 захватила второй ресурс");
            System.out.println("Нить 2 пытается захватить первый ресурс");
            synchronized (object1) {
                System.out.println("Нить 2 захватила первый ресурс");
            }
        }
    }
}
