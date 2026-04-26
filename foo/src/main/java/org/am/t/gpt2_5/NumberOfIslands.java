package org.am.t.gpt2_5;

public class NumberOfIslands {
    class Solution {
        public int numIslands(char[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int res = 0;
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[r].length; c++) {
                    if (grid[r][c] == '1') {
                        res++;
                        markIsland(grid, r, c);
                    }
                }
            }
            return res;
        }

        private void markIsland(char[][] grid, int r, int c) {
            if (r < 0 || r >= grid.length || c < 0 || c >= grid[r].length) {
                return;
            }
            char v = grid[r][c];
            if (v == '0' || v == 'x') {
                return;
            }
            grid[r][c] = 'x';
            markIsland(grid, r + 1, c);
            markIsland(grid, r - 1, c);
            markIsland(grid, r, c + 1);
            markIsland(grid, r, c - 1);
        }
    }
}
