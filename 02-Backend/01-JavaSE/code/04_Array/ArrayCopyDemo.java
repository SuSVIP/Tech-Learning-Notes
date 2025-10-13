package com.itheima.array;

import java.util.Arrays;

/**
 * @author zzl
 * @date 2025年10月12日 21:55
 */
public class ArrayCopyDemo {
    public static void main(String[] args) {
        int[] OldArr={1,2,3};

        // 扩容：原长度3 → 新长度5，新增元素补默认值0
        int[] NewArr= Arrays.copyOf(OldArr,5);

        System.out.println("原数组：" + Arrays.toString(OldArr));  // [1,2,3]
        System.out.println("扩容后数组：" + Arrays.toString(NewArr));  // [1,2,3,0,0]
    }
}
