package org.am.t.arrays2;

import java.util.Arrays;

public class HowManyNumbersAreSmallerThanTheCurrentNumber {
    public static void main(String[] args) {
        HowManyNumbersAreSmallerThanTheCurrentNumber s = new HowManyNumbersAreSmallerThanTheCurrentNumber();
        System.out.println(Arrays.toString(s.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
        System.out.println(Arrays.toString(s.smallerNumbersThanCurrent(new int[]{6, 5, 4, 8})));
        System.out.println(Arrays.toString(s.smallerNumbersThanCurrent(new int[]{7, 7, 7, 7})));
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] counts = new int[101]; // 0 <= n <= 100
        for (int num : nums) {
            counts[num]++;
        }
        int[] acc = new int[101];
        acc[0] = 0;
        acc[1] = counts[0];
        for (int i = 2; i < counts.length; i++) {
            acc[i] = acc[i - 1] + counts[i - 1];
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = acc[nums[i]];
        }
        return res;
    }
}
