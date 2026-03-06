package org.am;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }

    public int lengthOfLIS(int[] nums) {
        int[] lenghts = new int[nums.length];
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            calc(nums, lenghts, i);
            if (lenghts[i] > max) {
                max = lenghts[i];
            }
        }
        return max;
    }

    int calc(int[] nums, int[] lenghts, int i) {
        if (lenghts[i] > 0) {
            return lenghts[i];
        }
        lenghts[i] = 1;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                int atJ = calc(nums, lenghts, j);
                if (lenghts[i] < atJ + 1) {
                    lenghts[i] = atJ + 1;
                }
            }
        }
        return lenghts[i];
    }
}
