package org.am.t.gpt_7;

import java.util.Arrays;

public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int k = 0;
        int c = 0;
        int matches = 0;
        while (k < g.length && c < s.length) {
            if (g[k] <= s[c]) {
                matches++;
                k++;
                c++;
            } else {
                c++;
            }
        }
        return matches;
    }
}
