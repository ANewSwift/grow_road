package com.hsl.algorithm.labuladong.tree;

import com.hsl.algorithm.base.TreeNode;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 */
public class DiameterOfBinaryTree {

    private int maxDiameter = 0;

    public static void main(String[] args) throws Exception {
        DiameterOfBinaryTree main = new DiameterOfBinaryTree();
        String[] perOrders = {"1","2","4","5","3"};
        String[] midOrders = {"4","2","5","1","3"};
        TreeNode root = TreeNode.buildTreeWithPreMidOrder(perOrders, midOrders);
        main.diameterOfBinaryTree(root);
        System.out.println(main.maxDiameter == 3);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        this.maxDepth(root);
        return maxDiameter;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // 更新最大直径
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        // 返回左右子树的最大深度
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
