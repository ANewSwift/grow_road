package com.hsl.algorithm.labuladong.array.diff;

/**
 * 1094. 拼车
 *
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi]
 * 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。
 * 这些位置是从汽车的初始位置向东的公里数。
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 *
 * 示例 1：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 *
 * 示例 2：
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 */
public class CarPooling {

    public static void main(String[] args) {
        CarPooling main1 = new CarPooling();
        System.out.println(main1.carPooling(new int[][]{{2,1,5},{3,3,7}}, 4) == false);
        System.out.println(main1.carPooling(new int[][]{{2,1,5},{3,3,7}}, 5) == true);
        System.out.println(main1.carPooling(new int[][]{{4,5,6},{6,4,7},{4,3,5},{2,3,5}}, 13) == true);
    }

    /**
     * 差分数组
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int maxDistance = 0;
        for (int i = 0; i < trips.length; i++) {
            maxDistance = Math.max(maxDistance, trips[i][2]);
        }
        int[] nums = new int[maxDistance+1];
        Difference df = new Difference(nums);
        for (int[] trip : trips) {
            df.increment(trip[1], trip[2]-1, trip[0]);
        }
        int[] res = df.getRes(nums);
        for (int r : res) {
            if (r > capacity) {
                return false;
            }
        }
        return true;
    }

    /**
     * 未用差分数组
     */
    public boolean carPooling2(int[][] trips, int capacity) {
        int maxDistance = 0;
        for (int i = 0; i < trips.length; i++) {
            maxDistance = Math.max(maxDistance, trips[i][2]);
        }
        for (int i = 0; i <= maxDistance; i++) {
            for (int j = 0; j < trips.length; j++) {
                // 到达乘客上车位置
                if (i == trips[j][1]) {
                    capacity-=trips[j][0];
                }
                // 到达乘客下车位置
                if (i == trips[j][2]) {
                    capacity+=trips[j][0];
                }
            }
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }
}
