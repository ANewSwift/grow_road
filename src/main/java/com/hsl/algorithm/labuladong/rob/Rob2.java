package com.hsl.algorithm.labuladong.rob;

/**
 * 213. 打家劫舍 II
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [1,2,3]
 * 输出：3
 */
public class Rob2 {

    public static void main(String[] args) {
        Rob2 main = new Rob2();
        int rob1 = main.rob(new int[]{2,3,2});
        System.out.println(rob1 == 3);
        int rob2 = main.rob(new int[]{1,2,3,1});
        System.out.println(rob2 == 4);
        int rob3 = main.rob(new int[]{1,2,3});
        System.out.println(rob3 == 3);
        int rob4 = main.rob(new int[]{1});
        System.out.println(rob4 == 1);
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(this.rob_dp_opt(nums, 0,n-2),
                this.rob_dp_opt(nums, 1,n-1));
    }

    public int rob_dp_opt(int[] nums, int start, int end) {
        int x1 = 0, x2 = 0;
        for (int i = end; i >= start ; i--) {
            int t = x1;
            x1 = Math.max(x1, nums[i] + x2);
            x2 = t;
        }
        return x1;
    }
    /**
     * 动态规划，自底向上
     */
    public int rob_dp(int[] nums, int start, int end) {
        int[] dps = new int[nums.length+2];
        for (int i = end; i >= start ; i--) {
            dps[i] = Math.max(dps[i+1], nums[i] + dps[i+2]);
        }
        return dps[start];
    }
}
