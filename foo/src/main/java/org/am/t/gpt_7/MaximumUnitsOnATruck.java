package org.am.t.gpt_7;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumUnitsOnATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, Comparator.<int[]>comparingInt(bt -> bt[1]).reversed());
        int units = 0;
        for (int i = 0; i < boxTypes.length && truckSize > 0; i++) {
            int[] bt = boxTypes[i];
            int take = Math.min(truckSize, bt[0]);
            truckSize -= take;
            units += take * bt[1];
        }
        return units;
    }
}
