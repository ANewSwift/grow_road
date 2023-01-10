package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. 组合总和 II
 *
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        CombinationSum2 main = new CombinationSum2();
//        List<List<Integer>> lists = main.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
//        System.out.println(lists);
        List<List<Integer>> lists = main.combinationSum2(new int[]{2,5,2,1,2}, 5);
        System.out.println(lists);
    }
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.backtrack(candidates, target, 0, new LinkedList<>(), 0);
        return res;
    }
    private void backtrack(int[] candidates, int target, int start, LinkedList<Integer> route, int routeSum) {
        if (routeSum >= target) {
            if (routeSum == target) {
                res.add(new LinkedList<>(route));
            }
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i>start && candidates[i] == candidates[i-1]) {
                continue;
            }
            // 选择
            route.addLast(candidates[i]);
            //
            this.backtrack(candidates, target, i+1, route, routeSum + candidates[i]);
            // 撤销选择
            route.removeLast();
        }
    }
}
