package com.hsl.algorithm.labuladong.tree;

import com.hsl.algorithm.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 */
public class BuildTree {

    public static void main(String[] args) {
        BuildTree main = new BuildTree();
        TreeNode root1 = main.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        TreeNode.levelRetrieval(root1);
//        TreeNode root2 = main.buildTree(new int[]{-1}, new int[]{-1});
//        TreeNode.levelRetrieval(root2);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return this.build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int pleft, int pright,
                           int[] inorder, int ileft, int iright) {
        TreeNode root = new TreeNode(preorder[pleft]);
        int inIdx = ileft;
        for (int i = ileft; i <= iright; i++) {
            if (preorder[pleft] == inorder[i]) {
                inIdx = i;
                break;
            }
        }
        int leftLen = inIdx - ileft;
        int rightLen = iright - inIdx;
        root.left = leftLen == 0 ? null : this.build(preorder, pleft+1, pleft+leftLen,
                inorder, ileft, inIdx-1);
        root.right = rightLen == 0 ? null : this.build(preorder, pright - rightLen + 1, pright,
                inorder, inIdx+1, iright);
        return root;
    }
}
