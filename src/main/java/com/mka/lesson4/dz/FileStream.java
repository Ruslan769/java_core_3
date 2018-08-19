package com.mka.lesson4.dz;

import java.io.*;

public class FileStream {

    public static void main(String[] args) throws InterruptedException {
        final MyFileOutputStream myFileOutputStream = new MyFileOutputStream();
        Thread th1 = new Thread(new MyThreadOutputStream(myFileOutputStream));
        Thread th2 = new Thread(new MyThreadOutputStream(myFileOutputStream));
        Thread th3 = new Thread(new MyThreadOutputStream(myFileOutputStream));

        th1.start();
        th2.start();
        th3.start();

        th1.join();
        th2.join();
        th3.join();

        myFileOutputStream.closeFile(); // только для варианта 2
        //dInputStream();
    }

    public static void dInputStream() {
        DataInputStream stream = null;
        try {
            stream = new DataInputStream(new FileInputStream("./src/main/java/com/mka/lesson4/dz/dfs"));
            while (stream.available() > 0) {
                final String line = stream.readUTF();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// Какой вариант лучше? я оставил вариант 2

// вариант 2
class MyFileOutputStream {

    DataOutputStream stream = null;

    public MyFileOutputStream() {
        try {
            this.stream = new DataOutputStream(new BufferedOutputStream(
                    new FileOutputStream("./src/main/java/com/mka/lesson4/dz/dfs")
            ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void dOutputStream() {

        try {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i = " + i + ", thread name = " + Thread.currentThread().getName());
                stream.writeUTF("i = " + i + ", thread name = " + Thread.currentThread().getName());
            }
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// вариант 1
/*class MyFileOutputStream {

    // даже без синхронизации данные выводятся в нужном порядре почему?
    // это происходит потому что flush делается только после заполнения данными файла?
    // реализация не правильная? или можно пользоваться такой реализацией, но только убрав слово synchronized
    // так как она роли не играет и без него быстрее идет запись
    public synchronized void dOutputStream() {
        DataOutputStream stream = null;
        try {
            stream = new DataOutputStream(new BufferedOutputStream(
                    new FileOutputStream("./src/main/java/com/mka/lesson4/dz/dfs", true)
            ));
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i = " + i + ", thread name = " + Thread.currentThread().getName());
                stream.writeUTF("i = " + i + ", thread name = " + Thread.currentThread().getName());
            }
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}*/

class MyThreadOutputStream implements Runnable {

    MyFileOutputStream mFOS;

    public MyThreadOutputStream(MyFileOutputStream mFOS) {
        this.mFOS = mFOS;
    }

    @Override
    public void run() {
        mFOS.dOutputStream();
    }
}
