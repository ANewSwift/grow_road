package com.hsl.algorithm.labuladong.tree.binarysearch;

import com.hsl.algorithm.TreeNode;

/**
 * 98. 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *
 * 提示：
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 */
public class IsValidBST {

    public static void main(String[] args) {
        IsValidBST main = new IsValidBST();
//        Integer[] arr1 = {2,1,3};
//        TreeNode root1 = TreeNode.buildTree(arr1);
//        System.out.println(main.isValidBST(root1) == true);
//
//        Integer[] arr2 = {5,1,4,null,null,3,6};
//        TreeNode root2 = TreeNode.buildTree(arr2);
//        System.out.println(main.isValidBST(root2) == false);

        Integer[] arr3 = {4,3,6,1,2,5,7};
        TreeNode root3 = TreeNode.buildTree(arr3);
        System.out.println(main.isValidBST(root3) == false);
    }

    /**
     * 利用二叉树，根节点大于所有左子树，小于所有右子树，递归遍历
     */
    public boolean isValidBST(TreeNode root) {
        return this.traverse(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean traverse(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return this.traverse(root.left, min, root.val) && this.traverse(root.right, root.val, max);
    }

    long preVal = Long.MIN_VALUE;
    boolean stop = false;
    /**
     * 利用中序遍历有序的特点
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (stop) {
            return false;
        }
        this.isValidBST1(root.left);
        if (root.val <= preVal) {
            stop = true;
            return false;
        } else {
            preVal = root.val;
        }
        this.isValidBST1(root.right);
        return !stop;
    }

    /**
     * 时间复杂度太高
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!this.gtMaxLeft(root.left, root.val) || !this.ltMinRight(root.right, root.val)) {
            return false;
        }
        if (!this.isValidBST2(root.left) || !this.isValidBST2(root.right)) {
            return false;
        }
        return true;
    }

    private boolean gtMaxLeft(TreeNode root, int target) {
        while (root != null) {
            if (target < root.val) {
                return false;
            }
            root = root.right;
        }
        return true;
    }

    private boolean ltMinRight(TreeNode root, int target) {
        while (root != null) {
            if (target > root.val) {
                return false;
            }
            root = root.left;
        }
        return true;
    }

}
