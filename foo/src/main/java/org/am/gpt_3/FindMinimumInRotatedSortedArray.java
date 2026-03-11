package org.am.gpt_3;

public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray s = new FindMinimumInRotatedSortedArray();
        System.out.println(s.findMin(new int[]{3, 4, 5, 1, 2, 3}));
        System.out.println(s.findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println(s.findMin(new int[]{11,13,15,17}));
        System.out.println(s.findMin(new int[]{3,1,2}));
        System.out.println(s.findMin(new int[]{1}));
    }

    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int m = l + (r - l) / 2;
            int lN = nums[l];
            int mN = nums[m];
            int rN = nums[r];
            if (lN < rN) {
                return lN;
            }
            if (mN > rN) {
                // minimum to the right (not m!)
                l = m + 1;
            } else {
                // minimum to the left (or m!)
                r = m;
            }
        }
        return nums[l];
    }
}
