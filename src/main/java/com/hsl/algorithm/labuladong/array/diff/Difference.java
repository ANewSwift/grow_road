package com.hsl.algorithm.labuladong.array.diff;

/**
 * 查分数组
 */
public class Difference {
    private final int[] diff;
    public Difference(int[] nums) {
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] = nums[i-1];
        }
    }

    /* 给闭区间 [i, j] 增加 val（可以是负数）*/
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    public int[] getRes(int[] res) {
        res[0] = diff[0];
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i-1] + diff[i];
        }
        return res;
    }
}
