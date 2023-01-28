package com.hsl.algorithm.labuladong.bfs;

import com.hsl.algorithm.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 示例1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 */
public class MinDepth {

    public static void main(String[] args) {
        MinDepth main = new MinDepth();
//        TreeNode root = TreeNode.buildTree(new String[]{"3", "9", "20", null, null, "15", "7"});
//        System.out.println(main.minDepth(root) == 2);
//        TreeNode root = TreeNode.buildTree(new String[]{"2", null, "3", null, "4", null, "5", null, "6"});
//        System.out.println(main.minDepth(root) == 5);
        TreeNode root = TreeNode.buildTree(new String[]{"1", "2", "3", "4", null, null, "5"});
        System.out.println(main.minDepth2(root) == 3);
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int depth = 1;
        if (this.isLeafNode(root)) {
            return depth;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (this.isLeafNode(node.left)) {
                        return depth;
                    }
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    if (this.isLeafNode(node.right)) {
                        return depth;
                    }
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }

    private boolean isLeafNode(TreeNode node) {
        if (node == null) {
            throw new RuntimeException("node is null");
        }
        return node.left == null && node.right == null;
    }
}
