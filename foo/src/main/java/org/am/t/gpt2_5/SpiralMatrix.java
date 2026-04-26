package org.am.t.gpt2_5;

import org.am.t.Utils;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        new SpiralMatrix().run();
    }
    private void run() {
        System.out.println(new Solution().spiralOrder(Utils.matrix("[[1,2,3],[4,5,6],[7,8,9]]")));
    }

    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            if (matrix.length == 0 || matrix[0].length == 0) {
                return res;
            }
            int[] borders = new int[]{matrix[0].length, matrix.length, -1, 0};

            int r = 0;
            int c = 0;

            Direction dir = Direction.R; // 0 -r , 1 - b, 2 - l , 3 - up
            while (res.size() < matrix.length * matrix[0].length - 1) {
                while (dir.canMoveFurther(borders, r, c)) {
                    res.add(matrix[r][c]);
                    switch (dir) {
                        case R -> c++;
                        case B -> r++;
                        case L -> c--;
                        case T -> r--;
                    }
                }
                dir = dir.nextDir(borders, r, c);
            };
            res.add(matrix[r][c]);
            return res;
        }

        enum Direction {
            R, B, L, T;

            boolean canMoveFurther(int[] borders, int r, int c) {
                return switch (this) {
                    case R -> c + 1 < borders[0];
                    case B -> r + 1 < borders[1];
                    case L -> c - 1 > borders[2];
                    case T -> r - 1 > borders[3];
                };
            }

            public Direction nextDir(int[] borders, int r, int c) {
                return switch (this) {
                    case R -> {
                        borders[0] = c;
                        yield B;
                    }
                    case B -> {
                        borders[1] = r;
                        yield L;
                    }
                    case L -> {
                        borders[2] = c;
                        yield T;
                    }
                    case T -> {
                        borders[3] = r;
                        yield R;
                    }
                };
            }
        }
    }
}
