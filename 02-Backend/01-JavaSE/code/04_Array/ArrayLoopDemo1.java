package com.itheima.array;

/**
 * @author zzl
 * @date 2025年10月12日 21:33
 */
public class ArrayLoopDemo1 {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 95, 88};

        // 遍历数组，计算总成绩
        int total = 0;
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
            System.out.println("第" + (i+1) + "个成绩：" + scores[i]);
        }
        System.out.println("总成绩：" + total);  // 输出：438
    }
}
