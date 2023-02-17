package com.hsl.algorithm.labuladong.tree.binarysearch;

import com.hsl.algorithm.TreeNode;

public class DeleteBST {

    public static void main(String[] args) {
        DeleteBST main = new DeleteBST();
        // 情况一：删除叶子节点
        TreeNode root1 = main.deleteBST(TreeNode.buildTree(new Integer[]{4,2,6,1,3,5,7}), 3);
        TreeNode.levelRetrieval(root1);
        System.out.println("+++++++++++++++++++++");
        // 情况二：删除单节点的根节点
        TreeNode root2 = main.deleteBST(TreeNode.buildTree(new Integer[]{4,2,6,1,null,5,7}), 2);
        TreeNode.levelRetrieval(root2);
        System.out.println("+++++++++++++++++++++");
        // 情况二：删除左右子树都存在的根节点
        TreeNode root3 = main.deleteBST(TreeNode.buildTree(new Integer[]{4,2,6,1,3,null,7}), 4);
        TreeNode.levelRetrieval(root3);
        System.out.println("+++++++++++++++++++++");
    }

    public TreeNode deleteBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            // 分三种情况
            // 如果为叶子节点，则直接删除（用）
            if (root.left == null && root.right == null) {
                return null;
            }
            // 如果只有一个子节点，则返回该子节点
            if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            }
            // 如果都不为空，则找右子树最小值(或左子树最大值)交换
            TreeNode rightMinNode = this.findMin(root.right);
            // 删除右子树最小节点
            root.right = this.deleteBST(root.right, rightMinNode.val);
            // root与右子树最小节点交换
            rightMinNode.left = root.left;
            rightMinNode.right = root.right;
            root = rightMinNode;
        }else if (val > root.val) {
            root.right = this.deleteBST(root.right, val);
        } else {
            root.left = this.deleteBST(root.left, val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode p) {
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

}
