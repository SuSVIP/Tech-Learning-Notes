package com.itheima.branch;

import java.util.Scanner;

/**
 * 从控制台输入一个数，如果大于0输出"正整数"，如果小于0输出"负整数"，如果等于0输出"零"。
 * @author zzl
 * @date 2025年10月12日 18:02
 */
public class IfStarementDemo {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double i =scanner.nextDouble();
        if(i < 0){
            System.out.println("输入的数小于0");
        }else if (i > 0){
            System.out.println("输入的数大于0");
        }else if(i == 0){
            System.out.println("输入的数等于0");
        }else{
            System.out.println("输入的不是数字");
        }

    }
}
