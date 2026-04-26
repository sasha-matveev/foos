package org.am.t.gpt2_6;

import java.util.ArrayDeque;
import java.util.Deque;

public class NextGreaterElementII {
    // [1,2,3,4,3]
    // 2 3 4 -1 4
    // 4

    class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int[] res = new int[nums.length];

            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    Integer pop = stack.pop();
                    res[pop] = nums[i];
                }
                stack.push(i);
            }
            for (int num : nums) {
                while (!stack.isEmpty() && nums[stack.peek()] < num) {
                    Integer pop = stack.pop();
                    res[pop] = num;
                }
            }
            stack.forEach(idx -> res[idx] = -1);
            return res;
        }
    }
}
