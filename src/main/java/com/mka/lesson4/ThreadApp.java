package com.mka.lesson4;

public class ThreadApp {

    // volatile нет промежуточного значения, значение записывается сразу в main панять, не кэщируясь

    // 32 bit будет выполнено в одном потоке
    int a = 6;
    int b = 5;

    // 64 bit будет выполнено в одном потоке с volatile
    volatile long c = 45;
    volatile double d = 4.5;

    public static void main(String[] args) throws InterruptedException {
        // Thread extends
        FirstThread firstThread = new FirstThread();
        firstThread.start();
        firstThread.join(); // ждем
        // Runnable implements
        new Thread(new SecondThread()).start();
        // anonim class
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonim: started = " + Thread.currentThread().getName());
            }
        }).start();

        System.out.println("ThreadApp: started = " + Thread.currentThread().getName());
    }
}

class FirstThread extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FirstThread: started = " + Thread.currentThread().getName());
    }
}

class SecondThread implements Runnable {

    @Override
    public void run() {
        System.out.println("SecondThread: started = " + Thread.currentThread().getName());
    }
}