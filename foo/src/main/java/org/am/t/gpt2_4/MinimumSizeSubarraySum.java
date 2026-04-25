package org.am.t.gpt2_4;

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int res = 0;
        int sum = 0;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= target) {
                int windowSize = r - l + 1;
                res = res == 0 ? windowSize : Math.min(res, windowSize);
                sum -= nums[l++];
            }
        }
        return res;
    }
}
