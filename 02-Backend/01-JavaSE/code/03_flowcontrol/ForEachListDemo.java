package com.itheima.branch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzl
 * @date 2025年10月12日 20:23
 */
public class ForEachListDemo {
    public static void main(String[] args) {
        List<String> fruits=new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        //for-each遍历List
        for (String fruit:fruits) {
            System.out.print(fruit+" ");
        }
    }
}
