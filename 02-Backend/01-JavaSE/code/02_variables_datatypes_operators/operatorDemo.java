package com.itheima.operator;

/**
 * @author zzl
 * @date 2025年03月02日 02:42
 */
public class operatorDemo {
    public static void main(String[] args) {
        int a = 5;
        int b = 2;
        int c = -5;
        int d = -2;

        /**
         * 基本算术运算符
         * @author zzl
         * @date 2025-10-09 23:28
         * @param args
         */
        //`+`（加）、`-`（减）、`*`（乘）、`/`（除）、`%`（取余）。
        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(5 / 2);
        System.out.println(5 % 2);
        System.out.println(5.0 % 2);
        System.out.println(1.0 * 5 / 2);
        //取余运算结果的符号与被除数一致 5 % -2 = 1，-5 % 2 = -1
        System.out.println(a % d);
        System.out.println(c % b);

        /**
         * 自增、自减运算符
         * @author zzl
         * @date 2025-10-09 23:28
         * @param args
         */
        //`++`（自增）、`--`（自减）
        // 前置（如 `++a`）：先自增 / 自减，再参与运算。
        // 后置（如 `a++`）：先参与运算，再自增 / 自减。
        int e = 5;
        int f = ++a; // e 先变成 6，然后 f = 6
        int g = a--; // e 先参与运算，g = 6，然后 e 变成 5
        System.out.println(f);
        System.out.println(g);

        /**
         * 赋值运算符
         * @author zzl
         * @date 2025-10-09 23:36
         * @param args
         */
        System.out.println(a+=1);

        /**
         * 比较运算符
         * @author zzl
         * @date 2025-10-09 23:36
         * @param args
         */
        System.out.println(a>b);
        System.out.println(a<b);

        /**
         * 逻辑运算符
         * @author zzl
         * @date 2025-10-09 23:37
         * @param args
         */
        boolean h = true, i = false;
        System.out.println(h && i); // false
        System.out.println(h || i); // true
        System.out.println(!h); // false

    }
}
