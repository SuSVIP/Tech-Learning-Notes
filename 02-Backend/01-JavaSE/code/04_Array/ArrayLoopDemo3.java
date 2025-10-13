package com.itheima.array;

import java.util.Arrays;

/**
 * @author zzl
 * @date 2025年10月12日 21:42
 */
public class ArrayLoopDemo3 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};

        // 直接打印数组引用（会输出内存地址，无意义）
        System.out.println(arr);//[I@7f31245a  （格式：[类型@哈希值）

        // 用Arrays.toString()打印数组元素
        System.out.println("数组元素：" + Arrays.toString(arr));// 输出：[1, 3, 5, 7, 9]
    }
}
