package org.am.t.gpt2_1;

public class MoveZeroes {

    // 1 2 3 4 0 0
    //         *
    //           *

    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (++fast < nums.length) {
            if (nums[slow] == 0) {
                nums[slow] = nums[fast];
                nums[fast] = 0;
            }
            if (nums[slow] != 0) {
                slow++;
            }
        }
    }
}
