package org.am.t.gpt2_4;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int[] cnts = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            cnts[s1.charAt(i) - 'a']++;
        }

        int[] windowCnts = new int[26];
        int need = s1.length();
        int l = 0;
        for (int r = 0; r < s2.length(); r++) {
            int rIdx = s2.charAt(r) - 'a';
            windowCnts[rIdx]++;
            if (windowCnts[rIdx] <= cnts[rIdx]) {
                need--;
            } else {
                need++;
            }
            while (r - l + 1 > s1.length()) {
                int lIdx = s2.charAt(l++) - 'a';
                windowCnts[lIdx]--;
                if (windowCnts[lIdx] < cnts[lIdx]) {
                    need++;
                } else {
                    need--;
                }
            }
            if (need == 0) {
                return true;
            }
        }
        return false;
    }
}
