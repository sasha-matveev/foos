package org.am.t.gpt_6;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        int k = intervals[0][1];
        int c = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= k) {
                k = intervals[i][1];
            } else {
                c++;
                k = Math.min(k, intervals[i][1]);
            }
        }
        return c;
    }

}
