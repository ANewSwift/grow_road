package com.hsl.algorithm.labuladong.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 *
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 *
 * 示例 2:
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点的数量在 [0, 212 - 1] 范围内
 * -1000 <= node.val <= 1000
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
public class ConnectRightNode {

    public static void main(String[] args) {
        ConnectRightNode main = new ConnectRightNode();
        Integer[] arr = {1,2,3,4,5,6,7};
        Node node = Node.buildTree(arr);
        main.connect(node);
        System.out.println(node);
    }

    public Node connect(Node root) {
        this.traverse(root.left, root.right);
        return root;
    }

    private void traverse(Node node1, Node node2) {
        if (node1 == null) {
            return;
        }
        node1.next = node2;
        this.traverse(node1.left, node1.right);
        this.traverse(node2.left, node2.right);
        // 跨越两个不同父级树相连
        this.traverse(node1.right, node2.left);

    }

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node p = new Node();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                p.next = cur;
                p = p.next;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return root;
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public static Node buildTree(Integer[] nodes) {
        if (nodes == null) {
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        Node root = new Node(nodes[0]);
        queue.offer(root);
        int idx = 0;
        while (idx+2 < nodes.length) {
            Node node = queue.poll();
            if (nodes[++idx] != null) {
                node.left = new Node(nodes[idx]);
                queue.offer(node.left);
            }
            if (nodes[++idx] != null) {
                node.right = new Node(nodes[idx]);
                queue.offer(node.right);
            }
        }
        return root;
    }

}
