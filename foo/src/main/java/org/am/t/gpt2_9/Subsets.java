package org.am.t.gpt2_9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets {
    class Solution {
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            collectSubsets(nums, 0, new LinkedList<>());
            return res;
        }

        private void collectSubsets(int[] nums, int idx, List<Integer> subset) {
            if (idx == nums.length) {
                res.add(new ArrayList<>(subset));
                return;
            }
            // without idx element
            collectSubsets(nums, idx + 1, subset);
            subset.addLast(nums[idx]);
            collectSubsets(nums, idx + 1, subset);
            subset.removeLast();
        }
    }
}
