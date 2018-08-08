package com.mka.lesson1;

public class WildCard {
    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4, 5};
        Stats<Integer> stats1 = new Stats<Integer>(nums);
        double avg1 = stats1.avg();

        Double[] num2 = {1.1, 2.2, 3.3, 4.4, 5.5};
        Stats<Double> stats2 = new Stats<Double>(num2);
        double avg2 = stats2.avg();

        System.out.println("v1 = " + avg1 + ", v2 = " + avg2);
        System.out.println(stats2.avs2(stats1));
    }
}

class Stats<T extends Number> {
    private T[] nums;

    public Stats(T[] nums) {
        this.nums = nums;
    }

    public double avg() {
        double sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i].doubleValue();
        }
        return sum / nums.length;
    }

    public boolean avs2(Stats<?> another) {
        return Math.abs(this.avg() - another.avg()) < 0.0001;
    }
}