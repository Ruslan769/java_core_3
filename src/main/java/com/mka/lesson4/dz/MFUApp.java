package com.mka.lesson4.dz;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MFUApp {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyPrinter.setDocument(new int[]{7,2,8,57,4});
                MyPrinter.scan();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyPrinter.setDocument(new int[]{9,3,1,97,20});
                MyPrinter.scan();
            }
        }).start();
    }
}

class MyPrinter {

    private static int[] arrNotPrint;
    private static List arPrint;

    public static synchronized void setDocument(@NotNull int[] arr) {
        arrNotPrint = arr;
        arPrint = new ArrayList();
    }

    public static synchronized void scan() {
        for (int i = 0; i < arrNotPrint.length; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arPrint.add(arrNotPrint[i] + " print");
            print();
        }
    }

    public static void print() {
        final int i = arPrint.size();
        if (i > 1) {
            if (i < 5) {
                System.out.println("Опечатано " + i + " страницы");
            } else {
                System.out.println("Опечатано " + i + " страниц");
            }
        } else {
            System.out.println("Опечатано 1 страница");
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println(arPrint);
    }
}
