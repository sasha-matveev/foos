package org.am.t.gpt2_3;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{6,4 ,3, 1}, 10));
    }

    public int subarraySum(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> sumsCnt = new HashMap<>();
        sumsCnt.put(0, 1);
        int sum = 0;
        for (int num : nums) {
            sum += num;
            res += sumsCnt.getOrDefault(sum - k, 0);
            sumsCnt.put(sum, sumsCnt.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
