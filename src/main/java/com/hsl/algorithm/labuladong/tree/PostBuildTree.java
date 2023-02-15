package com.hsl.algorithm.labuladong.tree;

import com.hsl.algorithm.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 *
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
 * postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 *
 * 示例 1:
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入：inorder = [-1], postorder = [-1]
 * 输出：[-1]
 *
 * 提示:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder 和 postorder 都由 不同 的值组成
 * postorder 中每一个值都在 inorder 中
 * inorder 保证是树的中序遍历
 * postorder 保证是树的后序遍历
 */
public class PostBuildTree {

    public static void main(String[] args) {
        PostBuildTree main = new PostBuildTree();
        TreeNode root1 = main.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
        TreeNode.levelRetrieval(root1);
    }

    Map<Integer,Integer> inmap = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inmap.put(inorder[i], i);
        }
        return this.build(inorder, 0, inorder.length-1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int ileft, int iright,
                           int[] postorder, int pleft, int pright) {
        TreeNode root = new TreeNode(postorder[pright]);
        int inIdx = inmap.get(postorder[pright]);
        int leftLen = inIdx - ileft;
        int rightLen = iright - inIdx;
        root.left = leftLen == 0 ? null : this.build(inorder, ileft, inIdx - 1,
                postorder, pleft, pleft+leftLen-1);
        root.right = rightLen == 0 ? null : this.build(inorder, inIdx+1, iright,
                postorder, pright-rightLen, pright-1);
        return root;
    }
}
