package com.itheima.branch;

/**
 * @author zzl
 * @date 2025年10月12日 20:18
 */
public class ForEachArrayDemo {
    public static void main(String[] args) {
        int nums[]={1,2,3,4,5,6};
        System.out.println("for-each遍历int数组：");
        for (int num:nums) {
            System.out.print(num+" ");
        }

        System.out.println();

        // 对比：普通 for 循环（需手动管理索引）
        System.out.println("普通 for 循环遍历 int 数组：");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
