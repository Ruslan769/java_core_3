package com.mka.lesson6.dz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayApp {

    private final int SEARCH_NUM_ONE = 1;
    private final int SEARCH_NUM_FOUR = 4;

    public static void main(String[] args) {
    }

    public final int[] searchNumToArray(Integer[] arrIn) {

        int[] arrOut = new int[0];
        final List arList = new ArrayList(Arrays.asList(arrIn));
        int indexSearch = arList.lastIndexOf(SEARCH_NUM_FOUR);

        if (indexSearch != -1) {
            indexSearch++;
            int capacity = arList.size() - indexSearch;
            arrOut = new int[capacity];
            int count = 0;
            for (int i = indexSearch; i < arList.size(); i++) {
                arrOut[count] = (int) arList.get(i);
                count++;
            }
        } else {
            throw new RuntimeException();
        }

        return arrOut;
    }

    public final boolean filterArray(Integer[] arrIn) {

        final List arList = new ArrayList(Arrays.asList(arrIn));

        if (!arList.contains(SEARCH_NUM_ONE) || !arList.contains(SEARCH_NUM_FOUR)) {
            //System.out.println("нет первого или второго");
            return false;
        }

        int countNum = 0;
        countNum += Collections.frequency(arList, SEARCH_NUM_ONE);
        countNum += Collections.frequency(arList, SEARCH_NUM_FOUR);

        if (arList.size() != countNum) {
            //System.out.printf("тут не только %d или %d%n", SEARCH_NUM_ONE, SEARCH_NUM_FOUR);
            return false;
        }

        return true;
    }
}
