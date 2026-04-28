package org.am.t.gpt2_8;

public class DiameterOfBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        public int diameterOfBinaryTree(TreeNode root) {
            return height(root).d;
        }

        Res height(TreeNode root) {
            if (root == null) {
                return new Res(0, 0);
            }
            var l = height(root.left);
            var r = height(root.right);

            return new Res(
                    1 + Math.max(l.h, r.h),
                    Math.max(l.h + r.h, Math.max(l.d, r.d))
            );
        }

        record Res(int h, int d) {
        }

    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
