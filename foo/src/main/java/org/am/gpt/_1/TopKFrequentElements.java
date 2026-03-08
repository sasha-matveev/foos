package org.am.gpt._1;

import org.am.Medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

@Medium
public class TopKFrequentElements {
    public static void main(String[] args) {
        TopKFrequentElements s = new TopKFrequentElements();
        System.out.println(Arrays.toString(s.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2))); // [1,2]
        System.out.println(Arrays.toString(s.topKFrequent(new int[]{1}, 1))); // 1
        System.out.println(Arrays.toString(s.topKFrequent(new int[]{1,2,1,2,1,2,3,1,3,2}, 2))); // [1,2]
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        // todo : replace pq with List[] where List[x] - elements with freq=x, iterate from max, collect elems up to k
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.<Integer>comparingInt(key -> freq.get(key)).reversed());
        for (int num : freq.keySet()) {
            // todo : pq.poll when pq.size > k
            pq.offer(num);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

}
