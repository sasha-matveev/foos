package org.am.t.gpt2_5;

public class SetMatrixZeroes {
    class Solution {
        public void setZeroes(int[][] matrix) {
            boolean zeroInFirstRow = false;
            boolean zeroInFirstCol = false;

            for (int r = 0; r < matrix.length; r++) {
                if (matrix[r][0] == 0) {
                    zeroInFirstCol = true;
                    break;
                }
            }
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[0][c] == 0) {
                    zeroInFirstRow = true;
                    break;
                }
            }

            for (int r = 1; r < matrix.length; r++) {
                for (int c = 1; c < matrix[0].length; c++) {
                    if (matrix[r][c] == 0) {
                        matrix[r][0] = 0;
                        matrix[0][c] = 0;
                    }
                }
            }
            for (int r = 1; r < matrix.length; r++) {
                for (int c = 1; c < matrix[0].length; c++) {
                    if (matrix[0][c] == 0 || matrix[r][0] == 0) {
                        matrix[r][c] = 0;
                    }
                }
            }
            if (zeroInFirstCol) {
                for (int r = 0; r < matrix.length; r++) {
                    matrix[r][0] = 0;
                }
            }
            if (zeroInFirstRow) {
                for (int c = 0; c < matrix[0].length; c++) {
                    matrix[0][c] = 0;
                }
            }
        }
    }
}
