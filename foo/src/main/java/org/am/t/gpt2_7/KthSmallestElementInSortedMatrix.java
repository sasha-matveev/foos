package org.am.t.gpt2_7;

public class KthSmallestElementInSortedMatrix {

    /*

         1  5  9
        10 11 13
        12 13 15
     */

    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;

            int min = matrix[0][0];
            int max = matrix[n - 1][n - 1];

            while (min < max) {
                int val = min + (max - min) / 2;
                int cnt = lessOrEqualCnt(matrix, val);
                if (cnt < k) {
                    min = val + 1;
                } else {
                    max = val;
                }

            }
            return min;
        }

        private int lessOrEqualCnt(int[][] matrix, int val) {
            int n = matrix.length;
            int r = 0;
            int c = n - 1;
            int cnt = 0;
            while (r < n && c >= 0) {
                if (matrix[r][c] <= val) {
                    cnt += c + 1;
                    r++;
                } else{
                    c--;
                }
            }
            return cnt;
        }
    }
}
