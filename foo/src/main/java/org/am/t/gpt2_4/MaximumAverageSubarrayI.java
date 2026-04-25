package org.am.t.gpt2_4;

public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        double res = - Double.MAX_VALUE;

        int left = 0;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (right - left + 1 > k) {
                sum -= nums[left++];
            }
            if (right - left + 1 == k) {
                double avg = ((double) sum) / k;
                res = Math.max(res, avg);
            }
        }
        return res;
    }
}
