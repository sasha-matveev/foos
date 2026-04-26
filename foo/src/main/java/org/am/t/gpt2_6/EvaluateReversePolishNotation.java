package org.am.t.gpt2_6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiFunction;

public class EvaluateReversePolishNotation {
    class Solution {
        public int evalRPN(String[] tokens) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (String token : tokens) {
                switch (token) {
                    case "+" -> evaluate(stack, (a, b) -> b + a);
                    case "-" -> evaluate(stack, (a, b) -> b - a);
                    case "/" -> evaluate(stack, (a, b) -> b / a);
                    case "*" -> evaluate(stack, (a, b) -> b * a);
                    default -> stack.push(Integer.parseInt(token));
                }
            }
            return stack.pop();
        }

        private static void evaluate(Deque<Integer> stack, BiFunction<Integer, Integer, Integer> op) {
            int a = stack.pop();
            int b = stack.pop();
            stack.push(op.apply(a, b));
        }
    }
}
