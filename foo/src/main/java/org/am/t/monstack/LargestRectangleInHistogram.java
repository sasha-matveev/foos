package org.am.t.monstack;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        LargestRectangleInHistogram s = new LargestRectangleInHistogram();
        System.out.println(s.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(s.largestRectangleArea(new int[]{2, 4}));
        System.out.println(s.largestRectangleArea(new int[]{4, 2}));
        System.out.println(s.largestRectangleArea(new int[]{2,1,2}));

    }

    public int largestRectangleArea(int[] heights) {
        int[] res = new int[heights.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                Integer idx = stack.pop();
                res[idx] = heights[idx] * (i - (stack.isEmpty() ? -1 : stack.peek()) - 1);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer idx = stack.pop();
            res[idx] = heights[idx] * (heights.length - (stack.isEmpty() ? -1 : stack.peek()) - 1);
        }
        System.out.println(Arrays.toString(res));
        int max = 0;
        for (int s : res) {
            max = Math.max(max, s);
        }
        return max;
    }
}
