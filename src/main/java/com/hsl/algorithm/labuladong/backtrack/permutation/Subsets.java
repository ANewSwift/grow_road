package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class Subsets {

    public static void main(String[] args) {
        Subsets main = new Subsets();
        List<List<Integer>> subsets = main.subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }


    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        this.backtrack(nums, 0, new LinkedList<>());
        return res;
    }

    private void backtrack(int[] nums, int start, LinkedList<Integer> route) {
        // 前序遍历位置，把之前的节点路径记录下来
        res.add(new LinkedList<>(route));
        for (int i = start; i < nums.length; i++) {
            // 做选择
            route.addLast(nums[i]);
            // 进入下一层决策树
            // 使用start控制树枝，避免重复
            this.backtrack(nums, i+1, route);
            // 撤销选择
            route.removeLast();
        }
    }


}
