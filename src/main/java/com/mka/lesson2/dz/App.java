package com.mka.lesson2.dz;

import java.util.List;
import java.util.Scanner;

public class App {

    public static final String TEXT_EXIT = "exit";
    public static final String TEXT_COST = "/цена";
    public static final String TEXT_CHANGE_COST = "/сменитьцену";
    public static final String TEXT_GET_COST = "/товарыпоцене";

    public static void main(String[] args) {
        final ProductRepository productRepository = new ProductRepository();
        //productRepository.createTable(); // создаем таблицу
        //productRepository.clearTable(); // очищаем таблицу
        //productRepository.insertBatch(); // добавляем 10 000 элементов
        //System.out.println(productRepository.get()); // получить все элементы

        while (true) {
            System.out.println("Поле для ввода");

            final Scanner scanner = new Scanner(System.in);
            final String strIn = scanner.nextLine();
            if (TEXT_EXIT.equals(strIn)) break;

            final String[] arStrIn = strIn.split("\\s");
            if (TEXT_COST.equals(arStrIn[0]) && arStrIn[1] != null) {
                System.out.println(productRepository.getCost(arStrIn[1].trim()));
            } else if (TEXT_CHANGE_COST.equals(arStrIn[0]) && arStrIn[1] != null && arStrIn[2] != null) {
                productRepository.updateCost(arStrIn[1].trim(), Integer.valueOf(arStrIn[2].trim()));
            } else if (TEXT_GET_COST.equals(arStrIn[0]) && arStrIn[1] != null && arStrIn[2] != null) {
                List<Product> arPr = productRepository.getCostRange(Integer.valueOf(arStrIn[1].trim()), Integer.valueOf(arStrIn[2].trim()));
                System.out.println(arPr);
            } else {
                System.out.println("УПС! нет такой команды :(");
            }
        }

    }
}
