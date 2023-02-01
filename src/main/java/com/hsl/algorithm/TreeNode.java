package com.hsl.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(Integer val) {
        this.val = val;
    }

    public TreeNode(Integer val, TreeNode leftNode, TreeNode rightNode) {
        this.val = val;
        this.left = leftNode;
        this.right = rightNode;
    }

//    public void testBuildTree() throws Exception {
//        String[] preNodes = "F,B,A,D,C,E,G,I,H".split(",");
//        String[] midNodes = "A,B,C,D,E,F,G,H,I".split(",");
////        String[] postNodes = "A,C,E,D,B,H,I,G,F".split(",");
//        TreeNode rootNode = buildTreeWithPreMidOrder(preNodes, midNodes);
//        postRetrieval(rootNode);
//    }
//
//    public static void main(String[] args) {
////        TreeNode root = TreeNode.buildTree(new String[]{"3", "9", "20", null, null, "15", "7"});
//        TreeNode root = TreeNode.buildTree(new String[]{"2", null, "3", null, "4", null, "5", null, "6"});
//        TreeNode.preRetrieval(root);
//    }

    /**
     * 完全二叉树数组，重建树
     */
    public static TreeNode buildTree(Integer[] nodes) {
        if (nodes == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nodes[0]);
        queue.offer(root);
        int idx = 0;
        while (idx+2 < nodes.length) {
            TreeNode node = queue.poll();
            if (nodes[++idx] != null) {
                node.left = new TreeNode(nodes[idx]);
                queue.offer(node.left);
            }
            if (nodes[++idx] != null) {
                node.right = new TreeNode(nodes[idx]);
                queue.offer(node.right);
            }
        }
        return root;
    }

    public static TreeNode buildTreeWithPreMidOrder(int[] preOrderNodes, int[] midOrderNodes) throws Exception {
        if (preOrderNodes == null || midOrderNodes == null || preOrderNodes.length < 1) {
            return null;
        }
        // 前序、中序节点值是否相等
        boolean allMatch = validateArrayValueEquals(preOrderNodes, midOrderNodes);
        if (!allMatch) {
            throw new Exception("two param value not equals");
        }

        // 根节点
        int rootVal = preOrderNodes[0];
        TreeNode rootNode = new TreeNode(rootVal);

        if (preOrderNodes.length == 1) {
            return rootNode;
        }

        // 中序的左右子节点的拆分 [0~rootIndex-1], [rootIndex+1~length-1]
        int rootIndexInMid = Arrays.asList(midOrderNodes).indexOf(rootVal);
        int leftEndIndexInMid = Math.max(0, rootIndexInMid - 1);
        int[] leftNodesInMid = Arrays.copyOfRange(midOrderNodes, 0, leftEndIndexInMid + 1);

        int rightStartIndexInMid = Math.min(rootIndexInMid + 1, midOrderNodes.length - 1);
        int[] rightNodesInMid = Arrays.copyOfRange(midOrderNodes, rightStartIndexInMid, midOrderNodes.length);

        // 前序左右子节点的拆分 [1~preIndex(midVal[rootIndex-1])], [preLeftEndIndex+1~length-1]
        int leftEndIndexInPre = Arrays.asList(preOrderNodes).indexOf(midOrderNodes[leftEndIndexInMid]);
        int[] leftNodesInPre = Arrays.copyOfRange(preOrderNodes, 1, leftEndIndexInPre + 1);
        int[] rightNodesInPre = Arrays.copyOfRange(preOrderNodes, leftEndIndexInPre+1, preOrderNodes.length);

        // 赋值
        rootNode.left = buildTreeWithPreMidOrder(leftNodesInPre, leftNodesInMid);
        rootNode.right = buildTreeWithPreMidOrder(rightNodesInPre, rightNodesInMid);

        return rootNode;
    }

    public static boolean validateArrayValueEquals(int[] preOrderNodes, int[] midOrderNodes) {
        for (int i = 0; i < preOrderNodes.length; i++) {
            boolean findFlag = false;
            for (int j = 0; j < midOrderNodes.length; j++) {
                if (preOrderNodes[i]==(midOrderNodes[j])) {
                    findFlag = true;
                    break;
                }
            }
            if (!findFlag) {
                return false;
            }
        }
        return true;
    }

    /**
     * 前序遍历
     * 根左右
     */
    public static void preRetrieval(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        if (node.left != null) {
            preRetrieval(node.left);
        }
        if (node.right != null) {
            preRetrieval(node.right);
        }
    }

    /**
     * 中序遍历
     * 左根右
     */
    public static void midRetrieval(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            midRetrieval(node.left);
        }
        System.out.println(node.val);
        if (node.right != null) {
            midRetrieval(node.right);
        }
    }

    /**
     * 后序遍历
     * 左右根
     */
    public static void postRetrieval(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            postRetrieval(node.left);
        }
        if (node.right != null) {
            postRetrieval(node.right);
        }
        System.out.println(node.val);
    }

    public static boolean twoTreeEquals(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null) {
            return (Objects.equals(left.val, right.val)) && twoTreeEquals(left.left, right.left) && twoTreeEquals(left.right, right.right);
        }
        return false;
    }

}
