package org.am.t.gpt2_9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
    class Solution {
        List<List<Integer>> res;
        public List<List<Integer>> permute(int[] nums) {
            res = new ArrayList<>();

            LinkedList<Integer> options = new LinkedList<>();
            for (int num : nums) {
                options.add(num);
            }
            permute(new LinkedList<>(), options);

            return res;
        }

        private void permute(LinkedList<Integer> permutation, LinkedList<Integer> options) {
            if (options.isEmpty()) {
                res.add(new ArrayList<>(permutation));
                return;
            }
            for (int i = 0; i < options.size(); i++) {
                Integer num = options.removeFirst();
                permutation.addLast(num);
                permute(permutation, options);
                permutation.removeLast();
                options.addLast(num);
            }
        }
    }
}
