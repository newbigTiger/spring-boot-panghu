package com.example.demo.mothod.javaSE;

/**
 * @author 胖虎
 * @date 2021/4/5 下午 2:52
 */
public class DemoTwo {
    public static void main(String[] args) {
        int number = number(5);
        System.out.println(number);
    }
    public static int number(int f){
        if(f<1){
            try {
                throw  new IllegalAccessException("最小值必须大于1");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(f == 1 || f == 2){
            return f;
        }
        return number(f-2)+number(f-1);
    }
}
