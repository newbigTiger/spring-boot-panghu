package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo1 {
    public static <T extends Map> boolean compare(List<T> a, List<T> b) {
        if(a.size() != b.size())
            return false;
        List<T> collect = a.stream().sorted((i, j) -> j.get("shopId").toString().compareTo(i.get("shopId").toString())).collect(Collectors.toList());
        List<T> collect1 = b.stream().sorted((i, j) ->j.get("shopId").toString().compareTo(i.get("shopId").toString())).collect(Collectors.toList());
        for(int i=0;i<collect.size();i++){
            if(!collect.get(i).equals(collect1.get(i)))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Map<String,String> hashMap1 = new HashMap<>();
        hashMap1.put("shopId","41321343");
        hashMap1.put("idCard","1233412222223");
        hashMap1.put("tradingCard",null);
        Map<String,String> hashMap2 = new HashMap<>();
        hashMap2.put("shopId","9787678");
        hashMap2.put("idCard",null);
        hashMap2.put("tradingCard","dfsfds213");
        Map<String,String> hashMap3 = new HashMap<>();
        hashMap3.put("shopId","35243531");
        hashMap3.put("idCard","23123234");
        hashMap3.put("tradingCard",null);
        Map<String,String> hashMap4 = new HashMap<>();
        hashMap4.put("shopId","352435231");
        hashMap4.put("idCard","23123234");
        hashMap4.put("tradingCard",null);
        List<Map<String,String>> list1 = new ArrayList<>();
        list1.add(hashMap1);
        list1.add(hashMap2);
        list1.add(hashMap3);
        list1.add(hashMap4);


        Map<String,String> hashMap5 = new HashMap<>();
        hashMap5.put("shopId","41321343");
        hashMap5.put("idCard","1233412222223");
        hashMap5.put("tradingCard",null);
        Map<String,String> hashMap6 = new HashMap<>();
        hashMap6.put("shopId","9787678");
        hashMap6.put("idCard",null);
        hashMap6.put("tradingCard","dfsfds213");
        Map<String,String> hashMap7 = new HashMap<>();
        hashMap7.put("shopId","35243531");
        hashMap7.put("idCard","23123234");
        hashMap7.put("tradingCard",null);
        Map<String,String> hashMap8 = new HashMap<>();
        hashMap8.put("shopId","352435231");
        hashMap8.put("idCard","23123234");
        hashMap8.put("tradingCard",null);
        List<Map<String,String>> list2 = new ArrayList<>();
        list2.add(hashMap8);
        list2.add(hashMap6);
        list2.add(hashMap7);
        list2.add(hashMap5);
        System.out.println(compare(list1,list2));
    }
}
