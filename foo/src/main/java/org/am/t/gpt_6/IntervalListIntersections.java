package org.am.t.gpt_6;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> res = new ArrayList<>();
        int f = 0;
        int s = 0;
        while (f < firstList.length && s < secondList.length) {
            if (firstList[f][1] < secondList[s][0]) {
                f++;
                continue;
            } else if (secondList[s][1] < firstList[f][0]) {
                s++;
            } else {
                int[] i =new int[]{
                        Math.max(firstList[f][0], secondList[s][0]),
                        Math.min(firstList[f][1], secondList[s][1])
                };
                res.add(i);
                // 1st mistake - forget to increase index
                if (firstList[f][1] < secondList[s][1]) {
                    f++;
                } else {
                    s++;
                }
            }
        }
        return res.toArray(l -> new int[l][]);
    }
}
