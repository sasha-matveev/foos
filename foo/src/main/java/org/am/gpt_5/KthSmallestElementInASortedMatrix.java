package org.am.gpt_5;

public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        int l = matrix[0][0];
        int r = matrix[n - 1][n - 1];
        while (l < r) {
            int mid = l + (r - l) / 2;
            int count = countLessOrEqual(matrix, mid);

            if (count < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    int countLessOrEqual(int[][] matrix, int m) {
        int n = matrix.length;
        int cnt = 0;
        int row = 0;
        int clm = n - 1;

        while (row < n && clm >= 0) {
            if (matrix[row][clm] <= m) {
                cnt += clm + 1;
                row++;
            } else {
                clm--;
            }
        }
        return cnt;
    }

}


/*

 * * * * * * *
 * * * * * * *
 * * * * * * *
 * * * * * * *
 * * * * * * *
 * * * * * * *
 * * * * * * *

 */
