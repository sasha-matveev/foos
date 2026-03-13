package org.am.gpt_4;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    // nums = [1,3,-1,-3,5,3,6,7], k = 3
    // nums = [1,3,-1,-3,-5,3,6,7], k = 3

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[1 + nums.length - k];

        int l = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int r = 0; r < nums.length; r++) {
            if (r - l + 1 > k) {
                if (deque.getFirst() == l) {
                    deque.removeFirst();
                }
                l++;
            }
            while (!deque.isEmpty() && nums[deque.getLast()] < nums[r]) {
                deque.removeLast();
            }
            deque.addLast(r);
            if (r - l + 1 == k) {
                res[l] = nums[deque.getFirst()];
            }
        }

        return res;
    }


}
