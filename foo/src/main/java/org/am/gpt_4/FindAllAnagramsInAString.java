package org.am.gpt_4;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {
    public static void main(String[] args) {

    }

    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> res = new ArrayList<>();

        int[] target = new int[26];
        for (char c : p.toCharArray()) {
            target[c - 'a']++;
        }

        int l = 0;
        int r = 0;
        int[] current = new int[26];
        while (r < s.length()) {
            if (r - l + 1 > p.length()) {
                current[s.charAt(l++) - 'a']--;
            }
            current[s.charAt(r++) - 'a']++;
            if (areEqual(current, target)) {
                res.add(l);
            }
        }

        return res;
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
