package org.am.t.gpt2_3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.compute(num, (n, v) -> v == null ? 1 : (v + 1));
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                k,
                Comparator.comparingInt(i -> cnt.get(i))
        );
        for (int num : cnt.keySet()) {
            if (queue.size() < k) {
                queue.add(num);
            } else {
                int was = queue.remove();
                queue.add(cnt.get(num) > cnt.get(was) ? num : was);
            }
        }
        int[] res = new int[k];
        int i = 0;
        for (Integer num : queue) {
            res[i++] = num;
        }
        return res;
    }
}
