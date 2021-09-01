package com.example.demo.Utils.streamDemo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringList {
    public static void main(String[] args) {
        String method = "12321/23232/dfsdfds/21";
        String[] split = method.split("/");
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println(split);
        domeOne(split);
    }
    public static void domeOne(String[] split){
        List<String> collect = Arrays.asList(split.clone());
        collect.stream().collect(Collectors.groupingBy(item->item));
        System.out.println(collect);
    }
}
