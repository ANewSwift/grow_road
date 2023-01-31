package com.hsl.algorithm.labuladong.rob;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class Rob1 {

    public static void main(String[] args) {
        Rob1 main = new Rob1();
        int rob = main.rob_dp_opt(new int[]{1, 2, 3, 1});
        System.out.println(rob == 4);
//        int rob = main.rob_dp_opt(new int[]{2,7,9,3,1});
//        System.out.println(rob == 12);
    }

    //        dp[i] = Math.max(dp[i+1], nums[i] + dp[i+2])

    public int rob_dp_opt(int[] nums) {
        int x1 = 0, x2 = 0;
        for (int i = nums.length -1; i >= 0 ; i--) {
            int t = x1;
            x1 = Math.max(x1, nums[i] + x2);
            x2 = t;
        }
        return x1;
    }
    /**
     * 动态规划，自底向上
     */
    public int rob_dp(int[] nums) {
        int[] dps = new int[nums.length+2];
        for (int i = nums.length -1; i >= 0 ; i--) {
            dps[i] = Math.max(dps[i+1], nums[i] + dps[i+2]);
        }
        return dps[0];
    }

    int[] dp;
    /**
     * 动态规划，自顶向下
     */
    public int rob_dp_up_down(int[] nums) {
        dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return this.recursion(nums, 0);
    }

    /**
     * 从指定房间(start)开始，偷得最大钱
     * @param nums 房间钱数组
     * @param start 房间编号
     * @return 从指定房间(start)开始，偷得最大钱
     */
    private int recursion(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        if (dp[start] != -1) {
            return dp[start];
        }
        int res =  Math.max(
                // 不偷该房，从下间开始选择
                this.recursion(nums, start+1),
                // 偷该房，从下下间开始选择
                nums[start] + this.recursion(nums, start+2));
        dp[start] = res;
        return res;
    }

    /**
     * 垃圾！！！动态规划解法
     */
    public int rob_dp_opt_2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int x1 = nums[0];
        int x2 = 0;
        int x3 = 0;
        int x4 = 0;
        if (n >= 2) {
            x2 = nums[1];
            if (n == 2) {
                return Math.max(x1,x2);
            }
        }
        if (n >= 3) {
            x3 = nums[2] + nums[0];
            if (n == 3) {
                return Math.max(x2,x3);
            }
        }
        for (int i = 3; i < nums.length; i++) {
            x4 = nums[i] + Math.max(x1,x2);
            x1 = x2;
            x2 = x3;
            x3 = x4;
        }
        return Math.max(x2,x4);
    }

    /**
     * 垃圾！！！动态规划解法
     */
    public int rob_dp_2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (nums.length >= 2) {
            dp[1] = nums[1];
        }
        if (nums.length >= 3) {
            dp[2] = nums[2] + nums[0];
            // dp[2] = nums[2] + Math.max(nums[0],0);
        }
        for (int i = 3; i < nums.length; i++) {
            dp[i] = nums[i] + Math.max(dp[i-2],dp[i-3]);
        }
        return Math.max(dp[nums.length-1], dp[nums.length-2]);
    }
}
