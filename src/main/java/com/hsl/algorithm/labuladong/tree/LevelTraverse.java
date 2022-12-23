package com.hsl.algorithm.labuladong.tree;

import com.hsl.algorithm.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树层序遍历
 */
public class LevelTraverse {

    public static void main(String[] args) throws Exception {
        LevelTraverse main = new LevelTraverse();
        String[] preOrders = {"A","B","D","E","F","G","C"};
        String[] midOrders = {"D","B","F","E","G","A","C"};
        main.levelTraverse(TreeNode.buildTreeWithPreMidOrder(preOrders, midOrders));
    }

    void levelTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.println(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }
}
