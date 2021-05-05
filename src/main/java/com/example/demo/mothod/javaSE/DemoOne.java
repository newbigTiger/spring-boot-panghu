package com.example.demo.mothod.javaSE;

/**
 * @author 胖虎
 * @date 2021/4/5 下午 12:46
 */
public class DemoOne {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}
