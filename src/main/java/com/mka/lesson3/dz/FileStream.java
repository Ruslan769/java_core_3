package com.mka.lesson3.dz;

import java.io.*;

public class FileStream {

    private static final String filePath = "./src/main/java/com/mka/lesson3/dz/dfs.txt";

    public static void main(String[] args) {
        readFile();
    }

    private static void createFile() {
        FileOutputStream stream = null;
        final byte[] arr = new byte[50];

        boolean b = true;
        for (byte i = 0; i < 50; i++) {
            if (b) {
                arr[i] = i;
                b = false;
            } else {
                arr[i] = (byte) -i;
                b = true;
            }
        }

        try {
            stream = new FileOutputStream(filePath);
            stream.write(arr);
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

    private static void readFile() {
        FileInputStream stream = null;
        byte[] arr = new byte[50];
        try {
            stream = new FileInputStream(filePath);
            stream.read(arr);
            // print
            for (byte b : arr) {
                System.out.println(b);
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
