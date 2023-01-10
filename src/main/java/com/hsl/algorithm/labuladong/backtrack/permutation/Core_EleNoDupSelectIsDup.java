package com.hsl.algorithm.labuladong.backtrack.permutation;

import java.util.LinkedList;

/**
 * 形式三、元素无重可复选，即 nums 中的元素都是唯一的，每个元素可以被使用若干次
 * 只要删掉去重逻辑即可
 */
public class Core_EleNoDupSelectIsDup {

    LinkedList<Integer> track = new LinkedList<>();
    /* 组合/子集问题回溯算法框架 */
    void backtrack_combine(int[] nums, int start) {
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            // 注意参数
            backtrack_combine(nums, i);
            // 撤销选择
            track.removeLast();
        }
    }


    /* 排列问题回溯算法框架 */
    void backtrack_permute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            backtrack_permute(nums);
            // 撤销选择
            track.removeLast();
        }
    }
}
