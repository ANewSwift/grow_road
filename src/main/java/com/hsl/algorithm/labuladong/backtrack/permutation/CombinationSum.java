package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 *
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。
 * 你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum main = new CombinationSum();
        List<List<Integer>> lists = main.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(lists);
    }

    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.backtrack(candidates,target, 0, new LinkedList<>(), 0);
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
            route.addLast(candidates[i]);
            this.backtrack(candidates, target, i, route, routeSum + candidates[i]);
            route.removeLast();
        }
    }
}
