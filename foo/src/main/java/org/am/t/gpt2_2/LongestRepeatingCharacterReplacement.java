package org.am.t.gpt2_2;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int res = 0;
        int[] frequencies = new int[26];
        int maxFreq = 0;
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            frequencies[s.charAt(r) - 'A']++;
            maxFreq = Math.max(maxFreq, frequencies[s.charAt(r) - 'A']);

            while (r - l + 1 - maxFreq > k) {
                frequencies[s.charAt(l++) - 'A']--;
            }

            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
