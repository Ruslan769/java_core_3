package com.mka.lesson3;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileApp {
    public static void main(String[] args) {
        createFile();
    }

    private static void getDir() {
        File mFile = new File(".");
        List<String> list = Arrays.asList(mFile.list());
        System.out.println(list);
        for (File fileEach : mFile.listFiles()) {
            System.out.println("name file = " + fileEach.getName() + ", is dir = " + fileEach.isDirectory());
        }
    }

    private static void createFolder() {
        final File mFile = new File("./src/main/java/com/mka/lesson3/myFolder");
        System.out.println(mFile.mkdir());
    }

    private static void createFile() {
        final File mFile = new File("./src/main/java/com/mka/lesson3/myFile.txt");
        try {
            System.out.println(mFile.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
