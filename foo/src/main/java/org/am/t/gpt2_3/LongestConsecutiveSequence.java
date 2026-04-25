package org.am.t.gpt2_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        // support set num -- duplicates ignored - no problems
        // final map num => length of consequence ended in this idx.
        // calc(num) {
        //  if map[num] != null -- continue
        //  if map [num - 1] != null  => map[num] = map[num - 1] + 1
        //  else calc(num-1)
        // }
        Set<Integer> uniq = new HashSet<>();
        for (int num : nums) {
            uniq.add(num);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, calcLength(uniq, map, num));
        }
        int max = 0;
        for (Integer length : map.values()) {
            max = Math.max(max, length);
        }
        return max;
    }

    private int calcLength(Set<Integer> uniq, Map<Integer, Integer> map, int num) {
        if (!uniq.contains(num)) {
            return 0;
        }
        Integer res = map.get(num);
        if (res != null) {
            return res;
        }
        res = calcLength(uniq, map, num - 1) + 1;
        map.put(num, res);
        return res;
    }
}
