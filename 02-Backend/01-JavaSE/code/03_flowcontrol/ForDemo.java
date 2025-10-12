package com.itheima.branch;

/**
 * 1到10递增的和
 * @author zzl
 * @date 2025年10月12日 20:03
 */
public class ForDemo {
    public static void main(String[] args) {
        int sum=0;
        for (int i = 0; i <= 10; i++) {
            sum+=i;
        }
        System.out.println(sum);
    }
}
