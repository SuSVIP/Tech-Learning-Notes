package com.itheima.operator;

/**
 * @author zzl
 * @date 2025年10月09日 23:42
 */
public class MathOperations {
    public static void main(String[] args) {
        // 1. 计算圆的面积
        double radius = 5.0;
        double area = Math.PI * radius * radius;
        System.out.println("圆的面积: " + area);

        // 2. 两数的和、差、积、商
        double a = 10.5, b = 2.5;
        System.out.println("和: " + (a + b));
        System.out.println("差: " + (a - b));
        System.out.println("积: " + (a * b));
        System.out.println("商: " + (a / b));

        // 3. 三元运算符判断奇偶性
        int num = 17;
        String result = (num % 2 == 0) ? "偶数" : "奇数";
        System.out.println(num + " 是 " + result);
    }
}
