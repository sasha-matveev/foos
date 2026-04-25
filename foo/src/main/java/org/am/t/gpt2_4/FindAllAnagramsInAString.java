package org.am.t.gpt2_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] pCnt = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pCnt[p.charAt(i) - 'a']++;
        }
        int l = 0;
        int[] wCnt = new int[26];
        for (int r = 0; r < s.length(); r++) {
            wCnt[s.charAt(r) - 'a']++;
            while (r - l + 1 > p.length()) {
                wCnt[s.charAt(l++) - 'a']--;
            }
            if (r - l + 1 == p.length() && Arrays.equals(pCnt, wCnt)) {
                res.add(l);
            }
        }
        return res;
    }
}
