package com.mka.lesson3.dz;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SequenceApp {

    private static final String filePath = "./src/main/java/com/mka/lesson3/dz/sequence_files/";

    public static void main(String[] args) {
        try {
            fiveFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fiveFiles() throws IOException {
        final List<FileInputStream> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final FileInputStream mFile = new FileInputStream(filePath + "fs_" + i);
            list.add(mFile);
        }

        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(list));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath + "fs_join"));

        int b;
        while ((b = sis.read()) != -1) {
            bos.write(b);
        }

        bos.flush();
        bos.close();
    }
}
