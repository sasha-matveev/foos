package org.am.t.gpt_6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(ar -> ar[0]));

        PriorityQueue<Integer> running = new PriorityQueue<>();
        int max = 0;
        for (int[] interval : intervals) {
            while (!running.isEmpty() && running.peek() <= interval[0]) {
                running.poll();
            }
            running.add(interval[1]);
            max = Math.max(max, running.size());
        }
        return max;
    }
}
