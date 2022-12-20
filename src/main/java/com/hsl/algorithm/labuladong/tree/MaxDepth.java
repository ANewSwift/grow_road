package com.hsl.algorithm.labuladong.tree;

import com.hsl.algorithm.base.TreeNode;

/**
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class MaxDepth {

    public static void main(String[] args) throws Exception {
        MaxDepth main = new MaxDepth();
        String[] preOrders = {"3","9","20","15","7"};
        String[] midOrders = {"9","3","15","20","7"};
        TreeNode root = TreeNode.buildTreeWithPreMidOrder(preOrders, midOrders);
        int maxDepth = main.traverse2(root);
        System.out.println(maxDepth == 3);
    }

    public int maxDepth1(TreeNode root) {
        return this.traverse1(root, 0);
    }

    private int traverse1(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        return Math.max(traverse1(root.left, depth), traverse1(root.right, depth));
    }

    public int maxDepth2(TreeNode root) {
        return this.traverse2(root);
    }

    private int traverse2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(traverse2(root.left), traverse2(root.right)) + 1;
    }

}
