package com.example.demo.mothod.demo;

import java.util.HashMap;

/**
 * @author 胖虎
 * @date 2021/3/20 下午 12:37
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put(null,"null1");
        System.out.println(hashMap);
    }
}
