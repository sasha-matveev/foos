package org.am.gpt_2;

public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int k = nums.length;
        int l = 0;
        int r = 1;
        while (r < nums.length) {
            if (nums[r] == nums[l]) {
                r++;
                k--;
            } else {
                nums[++l] = nums[r++];
            }
        }
        return k;
    }
}
