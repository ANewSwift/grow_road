package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class PermuteUnique {

    public static void main(String[] args) {
        PermuteUnique main = new PermuteUnique();
        List<List<Integer>> lists = main.permuteUnique(new int[]{2,2,1,1});
        System.out.println(lists);
    }

    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.backtrack(nums, new boolean[nums.length], new LinkedList<>());
        return res;
    }

    private void backtrack(int[] nums, boolean[] used, LinkedList<Integer> route) {
        if (route.size() == nums.length) {
            res.add(new LinkedList<>(route));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 做选择
            route.addLast(nums[i]);
            used[i] = true;
            // 下一层决策树
            this.backtrack(nums, used, route);
            // 撤销选择
            route.removeLast();
            used[i] = false;
        }
    }
}
