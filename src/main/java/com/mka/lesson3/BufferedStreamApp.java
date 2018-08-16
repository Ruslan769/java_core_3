package com.mka.lesson3;

import java.io.*;

public class BufferedStreamApp {
    public static void main(String[] args) {
        bInputStream();
    }

    public static void bInputStream() {

        byte[] arr = new byte[20];
        try(BufferedInputStream bf = new BufferedInputStream(
                new FileInputStream("./src/main/java/com/mka/lesson3/bfs.dat"))) {
            bf.read(arr);
            for (byte b : arr) {
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bOutputStream() {
        OutputStream stream = null;
        BufferedOutputStream bf = null;
        byte[] arr = {1, 2, 3, 4, 5, 6, -1, -2, -3, 20, -30};
        try {
            stream = new FileOutputStream("./src/main/java/com/mka/lesson3/bfs.dat");
            bf = new BufferedOutputStream(stream);
            bf.write(arr);
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
                bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
