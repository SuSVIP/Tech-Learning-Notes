package com.itheima.array;

/**
 * @author zzl
 * @date 2025年10月12日 21:37
 */
public class ArrayLoopDemo2 {
    public static void main(String[] args) {
        String[] fruits = {"Apple", "Banana", "Orange"};

        // for-each遍历，打印所有水果
        System.out.println("水果列表：");
        for(String fruit:fruits){
            System.out.println(fruit);
        }

        // 注意：for-each无法修改原数组元素
        for(String fruit:fruits){
            fruit="Grape";// 仅修改副本，原数组不变
        }
        System.out.println("修改后第一个水果："+fruits[0]);// 仍为Apple

    }
}
