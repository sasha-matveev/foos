package org.am.t.gpt2_5;

import org.am.t.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class RottingOranges {
    public static void main(String[] args) {
        new RottingOranges().run();
    }

    private void run() {
        System.out.println(new Solution().orangesRotting(Utils.matrix("[[2,1,1],[1,1,0],[0,1,1]]")));
    }

    class Solution {
        public int orangesRotting(int[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) {
                return -1;
            }
            Queue<Orange> rottenOranges = new LinkedList<>();
            int freshOranges = 0;

            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[r].length; c++) {
                    int v = grid[r][c];
                    if (v == 2) {
                        rottenOranges.add(new Orange(r, c));
                    } else if (v == 1) {
                        freshOranges++;
                    }
                }
            }
            if (freshOranges == 0) {
                return 0;
            }
            int iteration = -1;
            while (!rottenOranges.isEmpty()) {
                int size = rottenOranges.size();
                for (int i = 0; i < size; i++) {
                    rottenOranges.remove().neighbours().stream()
                            .flatMap(o -> o.tryRot(grid).stream())
                            .forEach(o -> rottenOranges.add(o));
                }
                freshOranges -= rottenOranges.size();
                iteration++;
            }
            return freshOranges > 0 ? -1 : iteration;
        }

        record Orange(int r, int c) {
            int state(int[][] grid) {
                return grid[r][c];
            }

            List<Orange> neighbours() {
                return List.of(
                        new Orange(r + 1, c),
                        new Orange(r - 1, c),
                        new Orange(r, c + 1),
                        new Orange(r, c - 1)
                );
            }

            Optional<Orange> tryRot(int[][] grid) {
                if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || state(grid) != 1) {
                    return Optional.empty();
                }
                return Optional.of(rot(grid));
            }

            Orange rot(int[][] grid) {
                grid[r][c] = 2;
                return this;
            }
        }
    }
}
