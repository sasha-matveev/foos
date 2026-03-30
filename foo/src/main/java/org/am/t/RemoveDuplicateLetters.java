package org.am.t;

import java.util.Stack;

public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        RemoveDuplicateLetters s = new RemoveDuplicateLetters();
        System.out.println(s.removeDuplicateLetters("bcabc")); // abc
        System.out.println(s.removeDuplicateLetters("cbacdcbc")); // acdb
        System.out.println(s.removeDuplicateLetters("babababa"));
        System.out.println(s.removeDuplicateLetters("abaacdcbc"));
        System.out.println(s.removeDuplicateLetters("babababa"));
        System.out.println(s.removeDuplicateLetters("abacb")); // abc
    }

    public String removeDuplicateLetters(String s) {
        char[] str = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : str) {
            cnt[c - 'a']++;
        }
        boolean[] present = new boolean[26];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            cnt[str[i] - 'a']--;
            if (present[str[i] - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && str[stack.peek()] > str[i] && cnt[str[stack.peek()] - 'a'] > 0) {
                present[str[stack.pop()] - 'a'] = false;
            }
            stack.push(i);
            present[str[i] - 'a'] = true;
        }
        char[] res = new char[stack.size()];
        for (int i = res.length -1; i >= 0; i--) {
            res[i] = str[stack.pop()];
        }
        return new  String(res);
    }
}
