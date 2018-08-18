package com.mka.lesson4;

public class PingPongApp {

    /*
    * метод wait из класса object он заставляет потоку спать и отпускает монитор,
    * обязательно должен быть в синхронизированном блоке
    * */


    public static void main(String[] args) {
        PingPong pp = new PingPong();
        new Thread(new PingThread(pp)).start();
        new Thread(new PongThread(pp)).start();
    }
}

class PingThread implements Runnable {

    private PingPong pingPong;

    public PingThread(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        while (true) {
            pingPong.ping();
        }
    }
}

class PongThread implements Runnable {

    private PingPong pingPong;

    public PongThread(PingPong pingPong) {
        this.pingPong = pingPong;
    }

    @Override
    public void run() {
        while (true) {
            pingPong.pong();
        }
    }
}

class PingPong {

    boolean flag = true;

    public synchronized void ping() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = false;
        System.out.println("PING");
        notify();
    }

    public synchronized void pong() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        System.out.println("PONG");
        notify();
    }
}
