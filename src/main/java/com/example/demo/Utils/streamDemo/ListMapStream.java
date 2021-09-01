package com.example.demo.Utils.streamDemo;

import java.util.*;
import java.util.stream.Collectors;

public class ListMapStream {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map map = new HashMap();
            map.put("id", i);
            map.put("name", "张" + i);
            map.put("code", 10 + i);
            map.put("num",i/2);
            list.add(map);
        }
        Map<Object, List<Map<String, Object>>> num = list.stream().collect(Collectors.groupingBy(item -> item.get("num")));
        System.out.println(num);


        //List stream 按 Map 某个 key 合计 value 值
        int totalCode = list.stream().mapToInt(m -> (int) m.get("code")).sum();
        System.out.println("totalCode = " + totalCode);
        //List stream 按 Map 中某个 key 分组
        Map<String, List<Map>> map = list.stream().collect(Collectors.groupingBy(
                (Map m) -> (String)m.get("name"))
        );
        System.out.println(map);
        Map<String, List<Map<String, Object>>> code = list.stream().filter(notNull -> Objects.nonNull(notNull))
                .collect(
                        Collectors.groupingBy(
                                (Map<String, Object> map1) -> map1.get("code").toString()
                        )
                );
        System.out.println(code);

        List<Object> code1 = list.stream().map((Map<String, Object> mapA) ->
                mapA.get("code")
        ).collect(Collectors.toList());
        System.out.println(code1);
        Map<String, List<Map<String, Object>>> stringListMap = list.stream().collect(
                Collectors.groupingBy(map1 -> map1.get("id").toString()));
        System.out.println(stringListMap);
    }
}
