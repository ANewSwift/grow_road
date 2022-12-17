package com.hsl.algorithm.labuladong.array;

import java.util.Arrays;

/**
 * 26. 删除有序数组中的重复项
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 *
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
 *
 * 将最终结果插入 nums 的前 k 个位置后返回 k 。
 *
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        RemoveDuplicates main = new RemoveDuplicates();
        int[] nums1 = {0,0,1,1,1,2,2,3,3,4};
        int[] expect1 = {0,1,2,3,4};
        int i = main.removeDuplicates(nums1);
        System.out.println(i == expect1.length);
        for (int j = 0; j < expect1.length; j++) {
            System.out.println(nums1[j] == expect1[j]);
        }
    }

    public int simple(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int i=0,j=1;
        int k = nums.length;
        while (j < k) {
            // 遇到重复元素
            if ((nums[i] == nums[j])) {
                for (int a = j + 1; a < k; a++) {
                    nums[a-1] = nums[a];
                }
                k = k-1;
            } else {
                i++;
                j++;
            }
        }
        return k;
    }

    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }
}
