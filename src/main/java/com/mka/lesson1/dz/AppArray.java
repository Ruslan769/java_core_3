package com.mka.lesson1.dz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppArray {
    final private Integer[] arrNum = {22, 33, 44, 55, 66, 77};
    final private List<Integer> listNum = new ArrayList(Arrays.asList(arrNum)); // 2

    public static void main(String[] args) {
        final AppArray appArray = new AppArray();
        appArray.checkSwap(0, 2);
        System.out.println(appArray.listNum); // 1 (почему приватная переменная видна?)
    }

    void checkSwap(int pos1, int pos2) {
        final Integer num1 = listNum.get(pos1);
        listNum.set(pos1, listNum.get(pos2));
        listNum.set(pos2, num1);
    }
}
