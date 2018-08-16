package com.mka.lesson3;

import java.io.*;

public class DataStreamApp {
    public static void main(String[] args) {
        try {
            dInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dOutputStream() throws IOException {
        DataOutputStream stream = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream("./src/main/java/com/mka/lesson3/dfs_1")
        ));
        stream.writeFloat(1.1f);
        stream.flush();
        stream.close();
    }

    public static void dInputStream() throws IOException {
        DataInputStream stream = new DataInputStream(new FileInputStream("./src/main/java/com/mka/lesson3/dfs_1"));
        float f = stream.readFloat();
        System.out.println(f);
        stream.close();
    }
}
