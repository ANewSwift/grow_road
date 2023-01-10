package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.LinkedList;

/**
 * 形式一、元素无重不可复选，即 nums 中的元素都是唯一的，每个元素最多只能被使用一次
 */
public class Core_EleNoDupSelectNoDup {

    LinkedList<Integer> track = new LinkedList<>();
    /* 组合/子集问题回溯算法框架 */
    void backtrack_combine(int[] nums, int start) {
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            // 注意参数
            backtrack_combine(nums, i + 1);
            // 撤销选择
            track.removeLast();
        }
    }

    boolean[] used = new boolean[10];
    /* 排列问题回溯算法框架 */
    void backtrack_permute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 剪枝逻辑
            if (used[i]) {
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
