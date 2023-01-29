package com.hsl.algorithm.labuladong.binarysearch;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */
public class SearchRange {

    public static void main(String[] args) {
        SearchRange main = new SearchRange();
//        int[] ints = main.searchRange(new int[]{5,7,7,8,8,10} , 8);
//        System.out.println(ints[0] == 3);
//        System.out.println(ints[1] == 4);
        int[] ints = main.searchRange(new int[]{5,7,7,8,8,10} , 6);
        System.out.println(ints[0] == -1);
        System.out.println(ints[1] == -1);
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1,-1};
        }
        int left = 0;
        int right = nums.length-1;
        int findIdx = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (target == nums[mid]) {
                findIdx = mid;
                break;
            } else if (target > nums[mid]) {
                left++;
            } else {
                right--;
            }
        }
        // 未找到
        if (findIdx == -1) {
            return new int[]{-1,-1};
        }
        // left、right重新定义为搜索范围的左右边界
        for (left = findIdx-1; left >= 0; left--) {
            if (nums[left] != target) {
                break;
            }
        }
        for (right = findIdx+1; right < nums.length; right++) {
            if (nums[right] != target) {
                break;
            }
        }
        return new int[]{left+1, right-1};
    }
}
