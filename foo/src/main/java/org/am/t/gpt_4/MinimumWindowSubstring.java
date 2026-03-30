package org.am.t.gpt_4;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring s = new MinimumWindowSubstring();
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(s.minWindow("a", "a"));
        System.out.println(s.minWindow("a", "aa"));

    }

    public String minWindow(String s, String t) {
        if (t.isEmpty()) {
            return "";
        }

        int alphabet = 'z' - 'A' + 1;
        int[] target = new int[alphabet]; // 2nd error - was considering only upper case (26) but there are both (52) // 5th error and even more - no more constants, added int alphabet=..
        for (char c : t.toCharArray()) {
            target[c - 'A']++;
        }
        int[] window = new int[alphabet];
        int right = 0;
        int left = 0;
        String res = "";
        while (true) {
            while (!isIncluded(window, target) && right < s.length()) {
                window[s.charAt(right++) - 'A']++;
            }
            if (!isIncluded(window, target) && right == s.length()) { // 6th error - exiting only on right == s.length() (even with correct window)
                return res;
            }
            while (isIncluded(window, target)) {
                window[s.charAt(left++) - 'A']--;
            }
            // 3rd error - no check for invalid window
            String x = s.substring(left - 1, right);
            res = res.isEmpty() || x.length() < res.length() ? x : res;
            // right++; 4th error - excessive right move
        }
    }

    boolean isIncluded(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) { // made error was checkign equality first : a[i] != b[i]
                return false;
            }
        }
        return true;
    }
}
