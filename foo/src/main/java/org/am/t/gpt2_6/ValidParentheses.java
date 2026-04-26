package org.am.t.gpt2_6;

import java.util.Stack;

public class ValidParentheses {
    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char v = s.charAt(i);
                boolean isOpenB = v == '(' || v == '{' || v == '[';
                if (isOpenB) {
                    stack.push(v);
                } else if (stack.isEmpty()) {
                    return false;
                } else {
                    Character was = stack.pop();
                    if (!switch (was) {
                        case '{' -> v == '}';
                        case '[' -> v == ']';
                        case '(' -> v == ')';
                        default -> throw new IllegalStateException("Unexpected value: " + was);
                    }) {
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }
    }
}
