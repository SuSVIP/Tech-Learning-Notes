package com.itheima.branch;

import java.util.Scanner;

/**
 * 用户输入购买数量，程序从控制台接收数据，当用户输入错误时即当用户输入的数量>5戒者<0时，提示"购买数量：1~5"，程序继续让用户输 入。
 * @author zzl
 * @date 2025年10月12日 18:40
 */
public class WhileDemo {
    public static void main(String[] args) {
        Scanner console=new Scanner(System.in);
        int a;
        while (true){
            System.out.print("输入购买数量：");
            a=console.nextInt();
            if(a>=0&&a<=5){
                break;
            }
            System.out.println("购买数量：1~5");
        }
        System.out.println("输入的购买数量是："+a);
    }
}
