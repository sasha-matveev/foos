package org.am.gpt_3;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {

        // 4,5,6,7,0,1,2,3

        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        System.out.println(s.search(new int[]{3, 1}, 1));
        System.out.println(s.search(new int[]{5, 1, 3}, 5));

    }

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;
            int lN = nums[l];
            int rN = nums[r];
            int mN = nums[m];
            if (mN == target) {
                return m;
            }
            if (lN < rN) {
                // whole range monotonic
                if (target > mN) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            } else if (lN <= mN && (target > mN || target < lN) || mN < target && target <= rN) {
                // need to search to the right when
                //  a) left monotonic and NOT contains
                //  b) right monotonic and contains
                l = m + 1;
            } else {
                // need to search to the left when
                //  a) right monotonic and NOT contains
                //  b) left monotonic and contains
                r = m - 1;
            }
        }
        return -1;
    }

}
