package org.am.t.gpt2_2;

public class MinimumWindowSubstring {

    // time - O(n)
    // space - O(1)
    // t > s - cheked
    // empty t - any 1 symbol substring is valid
    // empty s and t - empty res
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        int alphabet = 'z' - 'A' + 1;
        int[] tCnt = new int[alphabet];
        for (int i = 0; i < t.length(); i++) {
            tCnt[t.charAt(i) - 'A']++;
        }

        int minLength = s.length();
        int minStartIdx = -1;
        int[] winCnt = new int[alphabet];
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            winCnt[s.charAt(r) - 'A']++;
            while (isValid(winCnt, tCnt)) {
                if (r - l + 1 <= minLength) {
                    minLength = r - l + 1;
                    minStartIdx = l;
                }
                winCnt[s.charAt(l++) - 'A']--;
            }
        }

        return minStartIdx == -1 ? "" : s.substring(minStartIdx, minStartIdx + minLength);
    }

    private boolean isValid(int[] winCnt, int[] tCnt) {
        for (int i = 0; i < winCnt.length; i++) {
            if (winCnt[i] < tCnt[i]) {
                return false;
            }
        }
        return true;
    }
}
