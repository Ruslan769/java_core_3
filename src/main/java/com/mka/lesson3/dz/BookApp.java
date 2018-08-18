package com.mka.lesson3.dz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BookApp {

    private static final String TEXT_EXIT = "exit";
    private static final String filePath = "./src/main/java/com/mka/lesson3/dz/book.txt";
    private static final int countSymbol = 1800;

    public static void main(String[] args) {

        while (true) {
            System.out.println("Напишите номер страницы");

            final Scanner scanner = new Scanner(System.in);
            final String strIn = scanner.nextLine();
            if (TEXT_EXIT.equals(strIn)) break;

            int position = 0;
            try {
                position = Integer.valueOf(strIn);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (position == 0) {
                System.out.println("Такой страницы нет");
                continue;
            }

            position = (position -1) * countSymbol;

            try {
                readBook(position);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void readBook(long position) throws IOException {
        System.out.println("position = " + position);

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        reader.skip(position);

        final StringBuffer page = new StringBuffer();

        int count = 0;
        String str;
        while ((str = reader.readLine()) != null) {
            count += str.length();
            page.append(str+"\n");
            if (count > countSymbol) break;
        }
        System.out.println(page.length());
        System.out.println(page.toString());
    }
}
