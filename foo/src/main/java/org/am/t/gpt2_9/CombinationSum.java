package org.am.t.gpt2_9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            combine(candidates, new LinkedList<>(), 0, 0, target);
            return res;
        }

        void combine(int[] candidates, LinkedList<Integer> chosen, int idx, int sum, int target) {
            if (sum > target) {
                return;
            }
            if (sum == target) {
                res.add(new ArrayList<>(chosen));
                return;
            }
            for (int i = idx; i < candidates.length - idx; i++) {
                int c = candidates[i];
                chosen.addLast(c);
                combine(candidates, chosen, i, sum + c, target);
                chosen.removeLast();
            }
        }
    }
}
