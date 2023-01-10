package com.hsl.algorithm.labuladong.backtrack.permutation;

import org.checkerframework.checker.units.qual.C;

import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 *
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 */
public class Combine {

    public static void main(String[] args) {
        Combine main = new Combine();
        List<List<Integer>> combine = main.combine(4, 2);
        System.out.println(combine);
    }

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        this.backtrack(n, k, 1, new LinkedList<>());
        return res;
    }

    private void backtrack(int n, int k, int start, LinkedList<Integer> route) {
        // base case
        if (route.size() == k) {
            res.add(new LinkedList<>(route));
            return;
        }
        for (int i = start; i <= n; i++) {
            // 选择
            route.addLast(i);
            // 进入下一层决策树
            this.backtrack(n, k, i+1, route);
            // 撤销选择
            route.removeLast();
        }
    }
}
