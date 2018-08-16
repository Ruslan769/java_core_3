package com.mka.lesson3;

import javafx.print.Collation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* объединение содержимых файлов
* */

public class SequenceApp {

    public static void main(String[] args) {
        try {
            threeFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toFiles() throws IOException {
        FileInputStream mFile = new FileInputStream("./src/main/java/com/mka/lesson3/fs_1");
        FileInputStream mFile2 = new FileInputStream("./src/main/java/com/mka/lesson3/fs_2");

        SequenceInputStream sis = new SequenceInputStream(mFile, mFile2);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./src/main/java/com/mka/lesson3/fs_1_2"));

        int b;
        while ((b = sis.read()) != -1) {
            bos.write(b);
        }

        bos.flush();
        bos.close();
    }

    public static void threeFiles() throws IOException {
        FileInputStream mFile = new FileInputStream("./src/main/java/com/mka/lesson3/fs_1");
        FileInputStream mFile2 = new FileInputStream("./src/main/java/com/mka/lesson3/fs_2");
        FileInputStream mFile3 = new FileInputStream("./src/main/java/com/mka/lesson3/fs_3");

        List<FileInputStream> list = new ArrayList<>();
        list.add(mFile);
        list.add(mFile2);
        list.add(mFile3);

        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(list));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./src/main/java/com/mka/lesson3/fs_1_2_3"));

        int b;
        while ((b = sis.read()) != -1) {
            bos.write(b);
        }

        bos.flush();
        bos.close();
    }
}
