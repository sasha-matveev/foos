package org.am.t.gpt2_1;

public class SubarrayProductLessThanK {

    /*
    100
    [10,5,2,6]
        *
          *
    10 +
    10 5 +
       5 +
       5 2 +
       5 2 6 +
         2 6
         2
           6
     */


    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // assume
        // 1 <= nums[i] <= 1_000
        // 1 <= nums.length <= 3 * 10^4
        // 0 <= k <= 10^6
        int n = 0;
        int l = 0;
        int r = 0;

        int product = 1; // will not be overflown due to max(k) * max(nums[i])) = 10^9 < 10^32
        while (r < nums.length) {
            product *= nums[r];
            while (product >= k && l <= r) {
                product /= nums[l++];
            }
            if (product < k && r >= l) {
                n += r - l + 1;
            }
            r++;
        }

        return n;
    }

}
