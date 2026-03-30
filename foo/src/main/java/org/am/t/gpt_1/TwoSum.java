package org.am.t.gpt_1;

import org.am.t.Easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Easy
public class TwoSum {
    public static void main(String[] args) {
        TwoSum s = new TwoSum();
        System.out.println(Arrays.toString(s.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(s.twoSum(new int[]{3,2,4}, 6))); // 1,2
        System.out.println(Arrays.toString(s.twoSum(new int[]{3,3}, 6)));
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer addIdx = map.get(target - nums[i]);
            if (addIdx != null) {
                return new int[]{addIdx, i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
