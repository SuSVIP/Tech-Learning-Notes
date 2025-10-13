package com.itheima.array;

/**
 * @author zzl
 * @date 2025年10月12日 21:10
 */
public class ArrayInitDemo1 {
    public static void main(String[] args) {
        //1.声明+动态初始化（一步完成）
        int[] scores =new int[5];// 长度为5的int数组，元素默认0

        // 2. 给数组元素赋值（通过索引）
        scores[0]=90;//第1个元素（索引0）
        scores[1]=85;//第2个元素（索引1）
        scores[2]=98;//第3个元素（索引2）
        scores[3]=87;//第4个元素（索引3）
        scores[4]=92;//第5个元素（索引4）

        // 3. 访问元素
        System.out.println("第3个成绩是："+scores[2]);
        System.out.println("数组的长度是："+scores.length); // 输出：5（length是数组的属性，非方法）
    }
}
