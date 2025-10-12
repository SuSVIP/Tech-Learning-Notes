package com.itheima.branch;

import java.util.Scanner;

/**
 * @author zzl
 * @date 2025年10月12日 18:14
 */
public class SwitchMonthDemo {
    public static void main(String[] args) {
        String season;
        System.out.print("请输入月份：");
        Scanner scanner=new Scanner(System.in);
        int month=scanner.nextInt();
        switch (month){
            case 3: case 4: case 5:
                season="春季";
                break;
            case 6: case 7: case 8:
                season="夏季";
                break;
            case 9: case 10: case 11:
                season="秋季";
                break;
            case 12: case 1: case 2:
                season="冬季";
                break;
            default:
                season="无效月份";
        }
        System.out.println(month + "月属于：" + season);
    }
}
