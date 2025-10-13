package com.itheima.array;

import java.util.Arrays;

/**
 * @author zzl
 * @date 2025年10月13日 09:34
 */
public class TwoDArrayDemo1 {
    public static void main(String[] args) {
        // 1. 声明二维数组（3行，列数暂不指定）
        int[][] matrix = new int[3][];

        // 2. 给每行（内层数组）赋值
        matrix[0] = new int[2];  // 第1行：2列
        matrix[1] = new int[3];  // 第2行：3列
        matrix[2] = new int[]{10, 20, 30, 40};  // 第3行：4列（静态初始化）

        // 3. 给元素赋值
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[1][1] = 5;

        // 打印二维数组（需用Arrays.deepToString()）
        System.out.println(Arrays.deepToString(matrix));
        // 输出：[[1, 2], [0, 5, 0], [10, 20, 30, 40]]

    }
}
