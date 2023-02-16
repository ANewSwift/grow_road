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

    boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        // base case
        if (root == null) return true;
        // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }


    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!this.gtMaxLeft(root.left, root.val) || !this.ltMinRight(root.right, root.val)) {
            return false;
        }
        if (!this.isValidBST(root.left) || !this.isValidBST(root.right)) {
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
