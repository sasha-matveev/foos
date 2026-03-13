package org.am.gpt_4;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement s = new LongestRepeatingCharacterReplacement();
//        System.out.println(s.characterReplacement("ABAB", 2));
//        System.out.println(s.characterReplacement("AABABBA", 1));
        System.out.println(s.characterReplacement("BAAAB", 2)); // !
    }


    public int characterReplacement(String s, int k) {
        int max = 0;

        int[] window = new int[26];
        int l = 0;
        int r = 0;
        while (r < s.length()) {
            window[s.charAt(r) - 'A']++;
            if (isValid(window, k)) {
                max = Math.max(r - l + 1, max);
            } else {
                window[s.charAt(l) - 'A']--;
                l++;
            }
            r++;
        }

        return max;
    }

    boolean isValid(int[] window, int k) {
        int maxIdx = 0;
        for (int i = 0; i < window.length; i++) {
            if (window[i] > window[maxIdx]) { // made a mistake here initially : window[i] > maxIdx
                maxIdx = i;
            }
        }
        int rest = k;
        for (int i = 0; i < window.length; i++) {
            if (i != maxIdx) {
                rest -= window[i];
                if (rest < 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
