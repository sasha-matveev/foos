package org.am.t.gpt_3;

public class FindPeakElement {

    public static void main(String[] args) {
        FindPeakElement s = new FindPeakElement();
        System.out.println(s.findPeakElement(new int[]{1,2,3,5,6,7,1}));
        System.out.println(s.findPeakElement(new int[]{1,2,1,3,5,6,4}));
    }

    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length- 1;

        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[m + 1]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

}
