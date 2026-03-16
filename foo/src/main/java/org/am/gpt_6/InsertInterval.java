package org.am.gpt_6;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> res = new ArrayList<>();
        boolean added = false;
        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) {
                res.add(interval);
            } else if (interval[0] > newInterval[1]) {
                if (!added) {
                    res.add(newInterval);
                    added = true;
                }
                res.add(interval);
            } else {
                newInterval = new int[]{
                        Math.min(interval[0], newInterval[0]),
                        Math.max(interval[1], newInterval[1])
                };
            }
        }
        // mistake, not considered empty array
        if (!added) {
            res.add(newInterval);
        }
        return res.toArray(l -> new int[l][]);
    }
}
