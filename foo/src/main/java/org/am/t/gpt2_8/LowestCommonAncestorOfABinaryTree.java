package org.am.t.gpt2_8;

import java.util.LinkedList;
import java.util.Optional;

public class LowestCommonAncestorOfABinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) {
                return null;
            }

            var qPath = path(root, q);
            var pPath = path(root, p);
            TreeNode target = null;
            for (int i = 0; i < Math.min(qPath.size(), pPath.size()); i++) {
                if (qPath.get(i).val == pPath.get(i).val) {
                    target = qPath.get(i);
                } else {
                    break;
                }
            }
            if (target == null) {
                throw new IllegalArgumentException();
            }
            return target;
        }

        LinkedList<TreeNode> path(TreeNode root, TreeNode target) {
            if (root == null) {
                return null;
            }
            LinkedList<TreeNode> path;
            if (root.val == target.val) {
                path = new LinkedList<>();
            } else {
                path = Optional.<LinkedList<TreeNode>>empty()
                        .or(() -> Optional.ofNullable(path(root.left, target)))
                        .or(() -> Optional.ofNullable(path(root.right, target)))
                        .orElse(null);
            }
            if (path != null) {
                path.addFirst(root);
            }
            return path;
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
