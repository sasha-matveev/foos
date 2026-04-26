package org.am.t.gpt2_7;

public class SearchInRotatedSortedArray {

    /*
            [4,5,6,7,0,1,2]

            5


     */


    class Solution {
        public int search(int[] nums, int target) {

            int l = 0;
            int r = nums.length - 1;

            while (l <= r) {
                int m = l + (r - l) / 2;
                int val = nums[m];

                if (val == target) {
                    return m;
                }
                if (nums[l] == target) {
                    return l;
                }
                if (nums[r] == target) {
                    return r;
                }

                if (nums[l] < nums[r]) {
                    // regular subarray
                    if (target > val) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                } else {
                    // broken subarray
                    // search which part is monotonic
                    if (nums[l] <= nums[m]) {
                        // left half is sorted
                        if (nums[l] <= target && target < nums[m]) {
                            r = m - 1;
                        } else {
                            l = m + 1;
                        }
                    } else {
                        // right half is sorted
                        if (nums[m] < target && target <= nums[r]) {
                            l = m + 1;
                        } else {
                            r = m - 1;
                        }
                    }
                }
            }
            return -1;
        }
    }
}
