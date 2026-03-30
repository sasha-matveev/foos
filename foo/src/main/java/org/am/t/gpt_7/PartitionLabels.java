package org.am.t.gpt_7;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        int[] lastIdx = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIdx[s.charAt(i) - 'a'] = Math.max(lastIdx[s.charAt(i) - 'a'], i);
        }
        List<Integer> res = new ArrayList<>();
        int l = 0;
        int r = -1;
        for (int i = 0; i < s.length(); i++) {
            r = Math.max(r, lastIdx[s.charAt(i) - 'a']);
            if (i == r) {
                res.add(r - l + 1);
                l = i + 1;
                r = -1;
            }
        }
        return res;
    }
}
