package com.hsl.algorithm.labuladong.array.doublepointer;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 *
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 *
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *
 * 你所设计的解决方案必须只使用常量级的额外空间。
 */
public class TwoSum {

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] nums = {2,7,11,15};
        int[] ints = twoSum.doublePoint(nums,9);
        System.out.println(ints[0] == 1);
        System.out.println(ints[1] == 2);
    }

    /**
     * 双指针，缩小空间
     */
    public int[] doublePoint(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left+1, right+1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 二分法
     */
    public int[] binaryMethod(int[] nums, int target) {
        for (int i = 0; i < nums.length-1; i++) {
            int val = target - nums[i];
            int idx = this.binarySearch(nums, i + 1, val);
            if (idx != -1) {
                return new int[]{i+1, idx+1};
            }
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int left, int target) {
        int right = nums.length-1;
        while (left <= right) {
            int mid = (left+right)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (target <nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
