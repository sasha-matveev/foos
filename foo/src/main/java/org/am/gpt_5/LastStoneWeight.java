package org.am.gpt_5;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(1);
        q.add(2);
        System.out.println(q.poll());

    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            queue.add(stone);
        }
        while (queue.size() >= 2) {
            int x = queue.poll();
            int y = queue.poll();
            int d = x - y;
            if (d > 0) {
                queue.add(d);
            }
        }
        return queue.size() == 1 ? queue.poll() : 0;
    }
}
