package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
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
