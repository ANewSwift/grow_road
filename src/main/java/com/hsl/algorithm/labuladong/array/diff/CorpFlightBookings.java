package com.hsl.algorithm.labuladong.array.diff;

import java.util.Arrays;

/**
 * 1109. 航班预订统计
 *
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 *
 * 示例 1：
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 *
 * 示例 2：
 * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
 * 输出：[10,25]
 * 解释：
 * 航班编号        1   2
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       15
 * 总座位数：      10  25
 * 因此，answer = [10,25]
 */
public class CorpFlightBookings {

    public static void main(String[] args) {
        CorpFlightBookings main = new CorpFlightBookings();
//        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
//        int[] ints = main.corpFlightBookings(bookings, 5);
//        Arrays.stream(ints).forEach(System.out::println);
        int[][] bookings = {{1,2,10},{2,2,15}};
        int[] ints = main.corpFlightBookings(bookings, 2);
        Arrays.stream(ints).forEach(System.out::println);
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        Difference df = new Difference(nums);
        for (int[] booking : bookings) {
            df.increment(booking[0] - 1,booking[1] - 1,booking[2]);
        }
        return df.getRes(nums);
    }
}