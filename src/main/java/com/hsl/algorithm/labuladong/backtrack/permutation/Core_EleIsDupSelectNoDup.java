package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.LinkedList;

/**
 * 形式二、元素可重不可复选，即 nums 中的元素可以存在重复，每个元素最多只能被使用一次，
 * 其关键在于排序和剪枝
 */
public class Core_EleIsDupSelectNoDup {
//    Arrays.sort(nums);
    LinkedList<Integer> track = new LinkedList<>();
    /* 组合/子集问题回溯算法框架 */
    void backtrack_combine(int[] nums, int start) {
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 剪枝逻辑，跳过值相同的相邻树枝
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // 做选择
            track.addLast(nums[i]);
            // 注意参数
            backtrack_combine(nums, i + 1);
            // 撤销选择
            track.removeLast();
        }
    }


    boolean[] used = new boolean[10];
//Arrays.sort(nums);
    /* 排列问题回溯算法框架 */
    void backtrack_permute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 剪枝逻辑
            if (used[i]) {
                continue;
            }
            // 剪枝逻辑，固定相同的元素在排列中的相对位置
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 做选择
            used[i] = true;
            track.addLast(nums[i]);

            backtrack_permute(nums);
            // 撤销选择
            track.removeLast();
            used[i] = false;
        }
    }
}
