package com.hsl.algorithm.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] ints = new int[]{3, 5, 4, 1, 2, 6};
        bubbleSort.bubbleSort1(ints);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    /**
     * 冒泡排序
     * 每次遍历，相邻元素间比较大小，按有序方向交换
     * 这样每次遍历至少会有一个元素放在正确位置
     * 技巧：不用遍历n次，如果某次遍历无交换发生，说明没有可交换的元素，证明已有序
     */
    public int[] bubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            boolean flag = false;
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    flag = true;
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
            // 无交换则证明已有序
            if (!flag) {
                break;
            }
        }
        return a;
    }

    /**
     * 复习
     */
    public void bubbleSort1(int[] a) {
        // N = arr.length
        // 外循环控制排序次数，总共排N-1次
        // 内循环进行冒泡操作（相邻元素比大小交换），终止条件：j < N-i
        for (int i = 0; i < a.length - 1; i++) {
            // flag代表是否进行过交换，无交换发生代表全部有序
            boolean flag = false;
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j+1]) {
                    int tmp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}
