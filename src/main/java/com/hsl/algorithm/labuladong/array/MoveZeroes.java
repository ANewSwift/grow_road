package com.hsl.algorithm.labuladong.array;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
public class MoveZeroes {

    public static void main(String[] args) {
        MoveZeroes main = new MoveZeroes();
        int[] nums = {0,1,0,3,12};
        int[] expect = {1,3,12,0,0};
        main.moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i] == expect[i]);
        }
    }

    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast <nums.length ; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        if (slow > 0) {
            for (int i = slow; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }
}
