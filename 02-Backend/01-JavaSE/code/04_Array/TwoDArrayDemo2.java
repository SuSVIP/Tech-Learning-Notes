package com.itheima.array;

/**
 * @author zzl
 * @date 2025年10月13日 09:37
 */
public class TwoDArrayDemo2 {
    public static void main(String[] args) {
        // 静态初始化：3行2列的二维数组
        int[][] scores = {
                {85, 92},   // 第1行（学生1的语文、数学成绩）
                {78, 88},   // 第2行（学生2）
                {95, 90}    // 第3行（学生3）
        };

        // 遍历二维数组（双层for循环）
        System.out.println("学生成绩表：");
        for (int i = 0; i < scores.length; i++) {  // 外层循环：行
            for (int j = 0; j < scores[i].length; j++) {  // 内层循环：列
                System.out.print(scores[i][j] + "\t");
            }
            System.out.println();  // 换行
        }
    }
}
