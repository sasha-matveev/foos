package org.am.t.gpt2_6;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] res = new int[temperatures.length];
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i < temperatures.length; i++) {
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                    Integer idx = stack.pop();
                    res[idx] = i - idx;
                }
                stack.push(i);
            }
            return res;
        }
    }
}
