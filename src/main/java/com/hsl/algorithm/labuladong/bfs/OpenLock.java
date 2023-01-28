package com.hsl.algorithm.labuladong.bfs;

import java.util.*;

/**
 *
 * 752. 打开转盘锁
 *
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字：
 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 */
public class OpenLock {

    public static void main(String[] args) {
        OpenLock main = new OpenLock();
//        String[] deadends = new String[]{"0201","0101","0102","1212","2002"};
//        int step = main.openLock(deadends, "0202");
//        System.out.println(step == 6);
    //        String[] deadends = new String[]{"8888"};
    //        int step = main.openLock(deadends, "0009");
    //        System.out.println(step == 1);
        String[] deadends = new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"};
        int step = main.openLock(deadends, "8888");
        System.out.println(step == -1);
    }

    /**
     * 双向 BFS 优化
     */
    int openLock2(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // 用集合不用队列，可以快速判断元素是否存在
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
            Set<String> temp = new HashSet<>();

            /* 将 q1 中的所有节点向周围扩散 */
            for (String cur : q1) {
                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (q2.contains(cur))
                    return step;

                visited.add(cur);

                /* 将一个节点的未遍历相邻节点加入集合 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up))
                        temp.add(up);
                    String down = minusOne(cur, j);
                    if (!visited.contains(down))
                        temp.add(down);
                }
            }
            /* 在这里增加步数 */
            step++;
            // temp 相当于 q1
            // 这里交换 q1 q2，下一轮 while 就是扩散 q2
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    public int openLock(String[] deadends, String target) {
        Set<String> deadendSet = new HashSet<>(Arrays.asList(deadends));
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String node = queue.poll();
                if (node.equals(target)) {
                    return step;
                }
                if (deadendSet.contains(node)) {
                    continue;
                }
                char[] chars = node.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char[] nextNums = this.getNextNums(chars[j]);
                    char[] clone = chars.clone();
                    for (int k = 0; k < nextNums.length; k++) {
                        clone[j] = nextNums[k];
                        String newNode = String.valueOf(clone);
                        if (!visited.contains(newNode)) {
                            queue.offer(newNode);
                            visited.add(newNode);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private char[] getNextNums(char c) {
        char[] chars = new char[2];
        chars[0] = '0' == c ? '9' : (char) (c - 1);
        chars[1] = '9' == c ? '0' : (char) (c + 1);
        return chars;
    }

}
