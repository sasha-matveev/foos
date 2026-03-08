package org.am.gpt._1;

import org.am.Hard;

import java.util.Arrays;

@Hard
public class FirstMissingPositive {
    public static void main(String[] args) {
        FirstMissingPositive s = new FirstMissingPositive();
        System.out.println(3 == s.firstMissingPositive(new int[]{1, 2, 0})); // 3
        System.out.println(2 == s.firstMissingPositive(new int[]{3, 4, -1, 1})); // 2
        System.out.println(1 == s.firstMissingPositive(new int[]{7, 8, 9, 11, 12})); // 1
        System.out.println(2 == s.firstMissingPositive(new int[]{1})); // 2
        System.out.println(3 == s.firstMissingPositive(new int[]{1,2,0})); // 3
        System.out.println(2 == s.firstMissingPositive(new int[]{3,4,-1,1})); // 2
        System.out.println(4 == s.firstMissingPositive(new int[]{100000, 3, 4000, 2, 15, 1, 99999})); // 4
    }

    public int firstMissingPositive(int[] nums) {
        // -1 - free
        // idx == num -1 - present
        for (int i = 0; i < nums.length; i++) {
            // todo : instant while(nums[i] 1...n && num[i] != nums[num[i] - 1]) { swap (nums[i] <> nums[nums[i] -1] }

            int num = nums[i];
            if (num == i + 1) {
                // matches instantly
                continue;
            }
            nums[i] = -1;
            int idx = num - 1;
            while (idx >= 0 && idx < nums.length && nums[idx] != idx+1) {
                int next = nums[idx];
                nums[idx] = idx + 1;
                idx = next - 1;
            }
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
