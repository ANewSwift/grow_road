package com.hsl.algorithm.labuladong.array;

/**
 * 303. 区域和检索 - 数组不可变
 *
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，
 * 包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 *
 * 示例 1：
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 *
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 */
public class NumArray {

    public static void main(String[] args) {
        NumArray main1 = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(main1.sumRange(0, 2) == 1);
        System.out.println(main1.sumRange(2, 5) == -1);
        System.out.println(main1.sumRange(0, 5) == -3);
    }

    int[] preNums;

    public NumArray(int[] nums) {
        preNums = new int[nums.length+1];
        for (int i = 1; i < preNums.length; i++) {
            preNums[i] = preNums[i-1] + nums[i-1];
        }
    }

    public int sumRange(int left, int right) {
        return preNums[right+1] - preNums[left];
    }
}
