package com.hsl.algorithm.labuladong.tree;

import com.hsl.algorithm.base.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 226. 翻转二叉树
 *
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 * 示例 2：
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 */
public class InvertTree {

    public static void main(String[] args) {
        InvertTree main = new InvertTree();
        int[] ints = {4, 2, 7, 1, 3, 6, 9};
        String[] arr = new String[ints.length];
        for (int i = 0; i < ints.length; i++) {
            arr[i] = String.valueOf(ints[i]);
        }
        TreeNode root = TreeNode.buildTree(arr);
        main.invertTree(root);
        TreeNode.levelRetrieval(root);

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        this.invertTree(root.left);
        this.invertTree(root.right);
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        return root;
    }

    /**
     * 遍历思维解决
     */
    public TreeNode invertTree2(TreeNode root) {
        this.traverse(root);
        return root;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        this.traverse(root.left);
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        this.traverse(root.right);

    }
}
