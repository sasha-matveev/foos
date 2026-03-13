package org.am.gpt_4;

public class MaximumAverageSubarrayI {
    public static void main(String[] args) {
        MaximumAverageSubarrayI s = new MaximumAverageSubarrayI();
        System.out.println(s.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.println(s.findMaxAverage(new int[]{-1}, 1));
    }

    public double findMaxAverage(int[] nums, int k) {
        int l = 0;
        int r = l;
        int sum = 0;
        double maxAvg = -Double.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r];
            if (r - l + 1 == k) {
                maxAvg = Math.max(maxAvg, ((double) sum) / k);
                sum -= nums[l];
                l++;
            }
            r++;
        }
        return maxAvg;
    }

}
