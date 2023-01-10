package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class SubsetsWithDup {

    public static void main(String[] args) {
        SubsetsWithDup main = new SubsetsWithDup();
        List<List<Integer>> lists = main.subsetsWithDup(new int[]{1, 2, 2});
        System.out.println(lists);
    }
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        this.backtrack(nums,0, new LinkedList<>());
        return res;
    }

    private void backtrack(int[] nums, int start, LinkedList<Integer> route) {
        res.add(new LinkedList<>(route));
        for (int i = start; i < nums.length; i++) {
            if (i - 1 >= start && nums[i] == nums[i - 1]) {
                continue;
            }
            route.addLast(nums[i]);
            this.backtrack(nums, i+1, route);
            route.removeLast();
        }
    }
}
