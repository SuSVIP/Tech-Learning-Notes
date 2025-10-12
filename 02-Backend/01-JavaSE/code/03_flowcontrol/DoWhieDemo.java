package com.itheima.branch;

import java.util.Scanner;

/**
 * do-while 循环区别于while循环的是，do-while循环首先执行的是循环体，执行完毕后才判断 boolean 表达式是否为true，为true继续，false退出。
 * 控制台输入验证，业务是押注，输入者输入的数<=0戒者>1000都不行，循环继续。
 * @author zzl
 * @date 2025年10月12日 18:54
 */
public class DoWhieDemo {
    public static void main(String[] args) {
/*        Scanner console=new Scanner(System.in);
        int balance=1000;//用户的钱
        int bet;//押注的钱
        do{
            System.out.print("请押注：");
            bet=console.nextInt();
        }while((bet<=0)||(bet>balance));
        System.out.println("押注的金额："+bet);*/
        Scanner console=new Scanner(System.in);
        int balance=1000;//用户的钱
        int bet;//押注的钱
        while (true){
            System.out.print("请押注：");
            bet=console.nextInt();
            if(bet>0&&bet<=balance){
                break;
            }
        }
        System.out.println("押注的金额："+bet);
    }
}
