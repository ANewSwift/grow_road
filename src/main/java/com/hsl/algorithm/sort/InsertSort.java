package com.hsl.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        InsertSort main = new InsertSort();
        int[] ints = new int[]{3, 5, 4, 1, 2, 6};
        main.insertSort(ints);
        Arrays.stream(ints).forEach(System.out::println);
    }

    /**
     * 插入排序
     * 1、假定遍历过的数列已有序，拿每个元素去找其已遍历有序数列的相应位置
     * 2、依次向后挪动元素，然后把该元素放到相应位置。
     * 3、接着遍历下个元素，执行以上1、2两步，直到遍历结束
     */
    public int[] insertSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int t = a[i];
            int j = i - 1;
            while (j >= 0 && t < a[j]) {
                a[j+1] = a[j];
                j--;
            }
            if (j != i-1) {
                a[j+1] = t;
            }
        }
        return a;
    }

    /**
     * 复习
     */
    public void insertSort1(int[] arr) {
        // N = arr.length
        // 外循环从1-N遍历找未排序元素，往[0,i-1]区间插入
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            // 内循环找>=j的元素j，不符合的全部往后拨
            int j = i-1;
            while (j >= 0 && key < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            // 边界条件j=-1
            // 放到j元素后
            arr[j+1] = key;
        }
    }
}
