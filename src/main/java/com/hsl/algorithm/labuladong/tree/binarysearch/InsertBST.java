package com.hsl.algorithm.labuladong.tree.binarysearch;

import com.hsl.algorithm.TreeNode;

import java.util.concurrent.TransferQueue;

public class InsertBST {

    public static void main(String[] args) {
        InsertBST main = new InsertBST();
        TreeNode root1 = main.insertBST(TreeNode.buildTree(new Integer[]{5,2,7,1,3,6,8}), 4);
        TreeNode.levelRetrieval(root1);
    }

    public TreeNode insertBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val > root.val) {
            root.right = this.insertBST(root.right, val);
        } else {
            root.left = this.insertBST(root.left, val);;
        }
        return root;
    }
}
