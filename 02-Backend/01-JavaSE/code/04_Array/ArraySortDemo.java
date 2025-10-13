package com.itheima.array;

import java.util.Arrays;

/**
 * @author zzl
 * @date 2025年10月12日 21:59
 */
public class ArraySortDemo {
    public static void main(String[] args) {
        int[] scores = {85, 72, 95, 68, 90};

        // 升序排序
        Arrays.sort(scores);

        System.out.println("排序后成绩：" + Arrays.toString(scores));  // [68,72,85,90,95]

        // 降序排序（需手动实现，或用包装类+Comparator）
        Integer[] scoreObj = {85, 72, 95, 68, 90};  // 包装类数组
        Arrays.sort(scoreObj, (a, b) -> b - a);  // lambda表达式实现降序
        System.out.println("降序排序成绩：" + Arrays.toString(scoreObj));  // [95,90,85,72,68]
    }
}
