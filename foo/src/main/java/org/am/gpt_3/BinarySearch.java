package org.am.gpt_3;

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch s = new BinarySearch();
        System.out.println(s.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        System.out.println(s.search(new int[]{-1,0,3,5,9,12}, 2));
        System.out.println(s.search(new int[]{-1,0,3,5,9,12}, 12));
    }

    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int m =  l + (r - l)/2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

}
