package org.am.t;

public class LcaOfBinarySearchTree {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode left = p.val < q.val ? p : q;
        TreeNode right = p.val > q.val ? p : q;

        if (left.val <= root.val && root.val <= right.val) {
            return root;
        } else {
            if (right.val <= root.val) {
                return lowestCommonAncestor(root.left, p, q);
            } else {
                return lowestCommonAncestor(root.right, p, q);
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
