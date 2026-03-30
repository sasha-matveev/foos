package org.am.t.gpt_5;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        KClosestPointsToOrigin s = new KClosestPointsToOrigin();
        System.out.println(Arrays.deepToString(s.kClosest(new int[][]{new int[]{1, 3}, new int[]{-2, 2}}, 1)));
    }

    public int[][] kClosest(int[][] points, int k) {
        int[][] res = new int[k][];

        int l = 0;
        int r = points.length - 1;
        int targetIdx = k - 1;
        while (l <= r) {
            int[] prt = partition(points, l, r);
            if (prt[0] <= targetIdx && targetIdx <= prt[1]) {
                break;
            } else if (targetIdx < prt[0]) {
                r = prt[0] - 1;
            } else {
                l = prt[1] + 1;
            }
        }
        System.arraycopy(points, 0, res, 0, res.length);
        return res;
    }

    private int[] partition(int[][] ar, int l, int r) {
        int val = distI(ar[r]);
        int idxL = l;
        int idxR = r;
        int i = l;
        while (i <= idxR) {
            int d = distI(ar[i]);
            if (d < val) {
                swap(ar, i++, idxL++);
            } else if (d > val) {
                swap(ar, i, idxR--);
            } else {
                i++;
            }
        }
        return new int[]{idxL, idxR};
    }

    private int distI(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }

    private void swap(int[][] ar, int a, int b) {
        int[] t = ar[a];
        ar[a] = ar[b];
        ar[b] = t;
    }





    public int[][] kClosest_queue(int[][] points, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, (int[] a1, int[] a2) -> Double.compare(dist(a2), dist(a1)));

        for (int[] point : points) {
            queue.add(point);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[][] res = new int[k][];
        for (int i = 0; i < res.length; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

    private double dist(int[] point) {
        return Math.sqrt(point[0] * point[0] + point[1] * point[1]);

    }

}
