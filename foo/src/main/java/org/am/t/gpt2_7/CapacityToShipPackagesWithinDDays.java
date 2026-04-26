package org.am.t.gpt2_7;

public class CapacityToShipPackagesWithinDDays {
    class Solution {
        public int shipWithinDays(int[] weights, int days) {
            int l = 0;
            int r = 0;
            for (int weight : weights) {
                l = Math.max(l, weight);
                r += weight;
            }
            while (l < r) {
                int m = l + (r - l) / 2;
                if (canShip(weights, days, m)) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            return l;
        }

        private boolean canShip(int[] weights, int days, int capacity) {
            int took = 0;
            int current = 0;
            for (int weight : weights) {
                if (current + weight > capacity) {
                    took++;
                    current = 0;
                }
                current += weight;
            }
            return took < days;
        }
    }
}
