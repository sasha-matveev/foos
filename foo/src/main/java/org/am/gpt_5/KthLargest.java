package org.am.gpt_5;

import java.util.PriorityQueue;

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.addNum(val);
 */
public class KthLargest {

    private final PriorityQueue<Integer> queue = new PriorityQueue<>();
    private final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            addNum(num);
        }
    }

    public int add(int val) {
        addNum(val);
        return queue.peek();
    }

    private void addNum(int num) {
        queue.add(num);
        if (queue.size() > this.k) {
            queue.poll();
        }
    }
}
