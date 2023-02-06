package com.hsl.algorithm.labuladong.array.diff;

/**
 * 查分数组
 */
public class Difference {
    private final int[] diff;
    Difference(int[] nums) {
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] = nums[i-1];
        }
    }

    public void inc(int[][] updates) {
        for (int i = 0; i < updates.length; i++) {
            int f = updates[i][0] - 1;
            int t = updates[i][1] - 1;
            int v = updates[i][2];
            diff[f] += v;
            if (t+1 < diff.length) {
                diff[t+1] -= v;
            }
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
