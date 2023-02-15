package com.hsl.algorithm.labuladong.tree;

import com.hsl.algorithm.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 889. 根据前序和后序遍历构造二叉树
 *
 * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，
 * postorder 是同一棵树的后序遍历，重构并返回二叉树。
 *
 * 如果存在多个答案，您可以返回其中 任何 一个。
 *
 * 示例 1：
 * 输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *
 * 示例 2:
 * 输入: preorder = [1], postorder = [1]
 * 输出: [1]
 *
 * 提示：
 * 1 <= preorder.length <= 30
 * 1 <= preorder[i] <= preorder.length
 * preorder 中所有值都 不同
 * postorder.length == preorder.length
 * 1 <= postorder[i] <= postorder.length
 * postorder 中所有值都 不同
 * 保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历
 */
public class PrePostBuildTree {

    public static void main(String[] args) {
        PrePostBuildTree main = new PrePostBuildTree();
        TreeNode root1 = main.constructFromPrePost(new int[]{1,2,4,5,3,6,7}, new int[]{4,5,2,6,7,3,1});
        TreeNode.levelRetrieval(root1);
    }

    Map<Integer, Integer> preMap = new HashMap<>();
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < preorder.length; i++) {
            preMap.put(preorder[i], i);
        }
        return this.build(preorder, 0,preorder.length-1,
                postorder, 0, postorder.length-1);
    }

    private TreeNode build(int[] preorder, int preLeft, int preRight,
                           int[] postorder, int postLeft, int postRight) {
        TreeNode root = new TreeNode(preorder[preLeft]);
        int leftLen = 0;
        int rightLen = 0;
        int postRightRootIdx = postRight - 1;
        if (postRightRootIdx < postLeft) {
            leftLen = preRight - preLeft;
        } else {
            int preRightRootIdx = preMap.get(postorder[postRightRootIdx]);
            leftLen = preRightRootIdx - preLeft - 1;
            rightLen = preRight - preRightRootIdx + 1;
        }
        root.left = leftLen == 0 ? null :
                this.build(preorder, preLeft+1, preLeft+leftLen,
                        postorder, postLeft, postLeft + leftLen - 1
                );
        root.right = rightLen == 0 ? null :
                this.build(preorder, preRight - rightLen + 1, preRight,
                        postorder, postLeft + leftLen, postRightRootIdx);
        return root;
    }
}
