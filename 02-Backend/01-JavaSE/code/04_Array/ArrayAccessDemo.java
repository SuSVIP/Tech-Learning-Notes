package com.itheima.array;

/**
 * @author zzl
 * @date 2025年10月12日 21:29
 */
public class ArrayAccessDemo {
    public static void main(String[] args) {
        int[] array={10,20,30,40};

        // 访问第1个元素（索引0）
        System.out.println("第一个元素是："+array[0]);

        // 修改第3个元素（索引2）
        array[2]=35;
        System.out.println("修改后第3个元素：" + array[2]);//35
    }
}
