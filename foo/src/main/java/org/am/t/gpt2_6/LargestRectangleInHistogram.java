package org.am.t.gpt2_6;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {
    class Solution {
        public int largestRectangleArea(int[] heights) {
            int res = 0;

            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < heights.length; i++) {
                while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    Integer pop = stack.pop();
                    int height = heights[pop];
                    int rightBorder = i;
                    int leftBorder = stack.isEmpty() ? -1 : stack.peek();
                    // 1 2 3 4 1
                    // 0 1 2 3 4
                    //   * * *   - 4 - 0 - 1
                    // * * * *   - 4 - (-1) -1
                    int width = rightBorder - leftBorder - 1;
                    res = Math.max(res, height * width);
                }
                stack.push(i);
            }

            while (!stack.isEmpty()) {
                Integer pop = stack.pop();
                int height = heights[pop];
                int rightBorder = heights.length;
                int leftBorder = stack.isEmpty() ? -1 : stack.peek();
                int width = rightBorder - leftBorder - 1;
                res = Math.max(res, height * width);
            }

            return res;
        }
    }
}
