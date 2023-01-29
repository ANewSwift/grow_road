package com.hsl.algorithm.labuladong.binarysearch;

/**
 * 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，
 * 如果目标值存在返回下标，否则返回 -1。
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch main = new BinarySearch();
        int search = main.search(new int[]{-1,0,3,5,9,12}, 9);
        System.out.println(search == 4);
//        int search = main.search(new int[]{-1,0,3,5,9,12}, 2);
//        System.out.println(search == -1);
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return -1;
    }
}
