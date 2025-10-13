package com.itheima.array;

/**
 * @author zzl
 * @date 2025年10月12日 21:47
 */
public class ArrayMaxMinDemo {
    public static void main(String[] args) {
        int[] nums = {15, 8, 23, 7, 30, 12};

        // 求最大值
        int max=nums[0];// 初始值设为第一个元素
        for (int i = 0; i < nums.length; i++) {
            max=nums[i]<max?max:nums[i];
        }

        // 求最小值
        int min=nums[0];// 初始值设为第一个元素
        for (int i = 0; i < nums.length; i++) {
            min=nums[i]<min?nums[i]:min;
        }

        System.out.println("最大值："+max);
        System.out.println("最小值："+min);
    }
}
