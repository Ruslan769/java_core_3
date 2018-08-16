package com.mka.lesson3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStream {
    public static void main(String[] args) {
        fInputStream();
    }

    public static void fOutputStream() {
        FileOutputStream stream = null;
        byte[] arr = {-1, -2, -3, -4};
        try {
            stream = new FileOutputStream("./src/main/java/com/mka/lesson3/myFile.txt", true);
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

    public static void fInputStream() {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("./src/main/java/com/mka/lesson3/myFile.txt");
            int c;
            while ((c = stream.read()) != -1) {
                System.out.println(c);
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
