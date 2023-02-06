package com.hsl.algorithm.labuladong.array.doublepointer;

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch main = new BinarySearch();
        int[] nums = {1,2,3,4,5,6};
        int idx = main.binarySearch(nums, 6);
        System.out.println(idx == 5);
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while (left <= right) {
            int mid = (left+right) /2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return -1;
    }
}
