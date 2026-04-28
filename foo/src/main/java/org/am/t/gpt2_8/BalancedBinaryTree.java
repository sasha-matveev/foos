package org.am.t.gpt2_8;

public class BalancedBinaryTree {
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
        public boolean isBalanced(TreeNode root) {
            return h(root) != -1;
        }

        int h(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int l = h(root.left);
            int r = h(root.right);
            if (l == -1 || r == -1) {
                return -1;
            }
            if (Math.abs(l - r) <= 1) {
                return Math.max(l, r) + 1;
            } else {
                return -1;
            }
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
