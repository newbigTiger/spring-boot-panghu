package com.example.demo.Utils;

import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 胖虎
 * @date 2021/4/19 下午 2:15
 */
public class ListMapUtil {
    public static void main(String[] args) {
        demoOne();
    }

    public static void demoOne(){
        List<Map<String,Object>> checkInfoList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("status",1);
        map.put("code",1111);
        checkInfoList.add(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("status",2);
        map1.put("code",2222);
        checkInfoList.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("status",1);
        map2.put("code",13333);
        checkInfoList.add(map2);
        List<String> storeOwnerCard = checkInfoList.stream().filter(item->"1".equals(MapUtils.getString(item,"status")))
                .map(item ->
                         MapUtils.getString(item,"code")).collect(Collectors.toList());
        System.out.println(storeOwnerCard);
    }
}
