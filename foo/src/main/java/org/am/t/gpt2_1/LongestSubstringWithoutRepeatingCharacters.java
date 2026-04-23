package org.am.t.gpt2_1;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int l = 0;
        int r = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (r < s.length()) {
            Integer was = map.put(s.charAt(r), r);
            if (was != null) {
                l = Math.max(l, was + 1);
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }
}
