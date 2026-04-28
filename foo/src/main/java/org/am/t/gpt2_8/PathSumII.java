package org.am.t.gpt2_8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSumII {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>>  res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            path(res, root, new LinkedList<>(), 0, targetSum);
            return res;
        }

        private void path(List<List<Integer>> res, TreeNode root, LinkedList<Integer> path, int sum, int targetSum) {
            path.addLast(root.val);
            int sumUpd = sum + root.val;
            if (root.left != null) {
                path(res, root.left, path, sumUpd, targetSum);
            }
            if (root.right != null) {
                path(res, root.right, path, sumUpd, targetSum);
            }
            if (root.left == null && root.right == null) {
                if (sumUpd == targetSum) {
                    res.add(new ArrayList<>(path));
                }
            }
            path.removeLast();
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
