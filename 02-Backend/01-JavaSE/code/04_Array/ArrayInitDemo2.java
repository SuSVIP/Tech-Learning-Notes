package com.itheima.array;

/**
 * @author zzl
 * @date 2025年10月12日 21:21
 */
public class ArrayInitDemo2 {
    public static void main(String[] args) {
        //1.静态初始化数组
        String[] names=new String[]{"Alice","Bob","Charlie"};

        // 2. 简化静态初始化（仅声明时可用）
        int[] ages={18,21,19};

        // 3. 访问元素
        System.out.println("第一个名字是："+names[0]);
        System.out.println("第三个年龄是："+ages[2]);
        System.out.println("名字数组长度是："+names.length);
    }
}
