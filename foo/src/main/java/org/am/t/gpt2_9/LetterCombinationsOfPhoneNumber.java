package org.am.t.gpt2_9;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {
    class Solution {
        List<String> res = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            combinations(digits, 0, new ArrayList<>());
            return res;
        }

        void combinations(String digits, int idx, ArrayList<String> text) {
            if (idx == digits.length()) {
                res.add(String.join("", text));
                return;
            }
            for (String next : map(digits.charAt(idx))) {
                text.add(next);
                combinations(digits, idx + 1, text);
                text.removeLast();
            }
        }

        List<String> map(Character num) {
            return switch (num) {
                case '2' -> List.of("a", "b", "c");
                case '3' -> List.of("d", "e", "f");
                case '4' -> List.of("g", "h", "i");
                case '5' -> List.of("j", "k", "l");
                case '6' -> List.of("m", "n", "o");
                case '7' -> List.of("p", "q", "r", "s");
                case '8' -> List.of("t", "u", "v");
                case '9' -> List.of("w", "x", "y", "z");
                default -> throw new IllegalStateException("Unexpected value: " + num);
            };
        }

    }
}
