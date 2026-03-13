package org.am.gpt_4;

public class PermutationInString {
    public static void main(String[] args) {
        PermutationInString s = new PermutationInString();
        System.out.println(s.checkInclusion("ab", "eidbaooo"));
        System.out.println(s.checkInclusion("ab", "eidboaoo"));
    }

    public boolean checkInclusion(String s1, String s2) {
        int[] s1Pattern = new int[26];
        char[] s1Chars = s1.toCharArray();
        for (char s1Char : s1Chars) {
            s1Pattern[s1Char - 97]++;
        }
        int r = 0;
        int l = 0;
        char[] s2Chars = s2.toCharArray();
        int[] windowPattern = new int[26];
        while (r < s2Chars.length) {
            if (r - l + 1 > s1.length()) {
                windowPattern[s2.charAt(l) - 97]--;
                l++;
            }
            windowPattern[s2.charAt(r++) - 97]++;
            if (areEqual(s1Pattern, windowPattern)) {
                return true;
            }
        }
        return false;
    }

    private boolean areEqual(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }


}
