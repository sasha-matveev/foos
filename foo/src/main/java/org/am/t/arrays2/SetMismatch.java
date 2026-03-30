package org.am.t.arrays2;

import java.util.Arrays;

public class SetMismatch {

    public static void main(String[] args) {
        SetMismatch s = new SetMismatch();
        System.out.println(Arrays.toString(s.findErrorNums(new int[]{1, 2, 2, 4})));
        System.out.println(Arrays.toString(s.findErrorNums(new int[]{1, 1})));
    }

    public int[] findErrorNums(int[] nums) {
        int[] correct = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            correct[i] = i + 1;
        }
        int duplicate = 0;
        int missing = 0;
        for (int i = 0; i < nums.length; i++) {
            int e = nums[i];
            if (correct[e - 1] > 0) {
                correct[e - 1] = 0;
            } else {
                // e  - duplicate
                duplicate = e;
            }
        }
        for (int j : correct) {
            if (j != 0) {
                missing = j;
                break;
            }
        }
        return new int[]{duplicate, missing};
    }
}
