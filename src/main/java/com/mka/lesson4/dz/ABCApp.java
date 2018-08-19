package com.mka.lesson4.dz;

public class ABCApp {
    public static void main(String[] args) {
        final GenerationABC generation = new GenerationABC();
        new Thread(new myThread(generation)).start();
        new Thread(new myThread(generation)).start();
        new Thread(new myThread(generation)).start();
    }
}

class myThread implements Runnable {

    GenerationABC generation;

    public myThread(GenerationABC generation) {
        this.generation = generation;
    }

    @Override
    public void run() {
        generation.init();
    }
}

class GenerationABC {

    int position = 0;
    final String[] arr = {"A", "B", "C"};

    public synchronized void init() {

        for (int i = 0; i < 5; i++) {
            System.out.println(arr[position] + " thread name = " + Thread.currentThread().getName());
            position ++;
            if (arr.length == position) {
                position = 0;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
