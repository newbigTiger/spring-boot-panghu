package com.example.demo.Utils.erpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

public class ERPEnumUtils {
    public static void main(String[] args) {

        JSONObject jsonObject = JSONObject.fromObject("{\"page_size\":30,\"page_index\":1,\"data_count\":162,\"page_count\":6,\"has_next\":true,\"datas\":[{\"lc_id\":\"TTKDEX\",\"lc_name\":\"天天快递\",\"modified\":\"2014-07-21 09:46:34\"},{\"lc_id\":\"QFKD\",\"lc_name\":\"全峰快递\",\"modified\":\"2014-07-21 09:47:18\"},{\"lc_id\":\"CNEX\",\"lc_name\":\"佳吉快运\",\"modified\":\"2014-07-21 09:47:21\"},{\"lc_id\":\"FEDEX\",\"lc_name\":\"联邦快递\",\"modified\":\"2014-07-21 09:47:40\"},{\"lc_id\":\"GDEMS\",\"lc_name\":\"广东EMS\",\"modified\":\"2014-07-21 09:51:30\"},{\"lc_id\":\"UAPEX\",\"lc_name\":\"全一快递\",\"modified\":\"2014-07-21 09:51:48\"},{\"lc_id\":\"GTO\",\"lc_name\":\"国通快递\",\"modified\":\"2014-07-21 09:52:06\"},{\"lc_id\":\"HOAU\",\"lc_name\":\"天地华宇\",\"modified\":\"2014-07-25 08:40:34\"},{\"lc_id\":\"DUMMY\",\"lc_name\":\"无需物流\",\"modified\":\"2014-08-04 15:01:11\"},{\"lc_id\":\"BJCS\",\"lc_name\":\"城市100\",\"modified\":\"2014-08-08 11:42:12\"},{\"lc_id\":\"OTHER1\",\"lc_name\":\"其他1\",\"modified\":\"2014-08-23 15:24:38\"},{\"lc_id\":\"OTHER2\",\"lc_name\":\"其他2\",\"modified\":\"2014-08-23 15:24:48\"},{\"lc_id\":\"OTHER3\",\"lc_name\":\"其他3\",\"modified\":\"2014-08-23 15:25:00\"},{\"lc_id\":\"LB\",\"lc_name\":\"龙邦速递\",\"modified\":\"2014-09-12 12:40:55\"},{\"lc_id\":\"QRT\",\"lc_name\":\"增益速递\",\"modified\":\"2014-09-26 13:09:16\"},{\"lc_id\":\"DBL\",\"lc_name\":\"德邦物流\",\"modified\":\"2014-10-10 11:12:44\"},{\"lc_id\":\"GZFY\",\"lc_name\":\"凡宇速递\",\"modified\":\"2014-11-18 08:50:35\"},{\"lc_id\":\"KXTX\",\"lc_name\":\"卡行天下\",\"modified\":\"2014-12-17 14:51:53\"},{\"lc_id\":\"FKDWL\",\"lc_name\":\"飞康达物流\",\"modified\":\"2014-12-17 14:52:03\"},{\"lc_id\":\"QRTKD\",\"lc_name\":\"全日通快递\",\"modified\":\"2014-12-17 14:52:37\"},{\"lc_id\":\"CCES\",\"lc_name\":\"CCES快递\",\"modified\":\"2014-12-17 14:52:47\"},{\"lc_id\":\"SYHY\",\"lc_name\":\"苏粤货运\",\"modified\":\"2014-12-17 14:53:12\"},{\"lc_id\":\"GPWL\",\"lc_name\":\"贵平物流\",\"modified\":\"2014-12-17 14:53:24\"},{\"lc_id\":\"JYWL\",\"lc_name\":\"佳怡物流\",\"modified\":\"2014-12-17 14:53:35\"},{\"lc_id\":\"GZND\",\"lc_name\":\"港中能达\",\"modified\":\"2014-12-17 14:53:48\"},{\"lc_id\":\"CSZX\",\"lc_name\":\"城市之星\",\"modified\":\"2014-12-17 15:10:13\"},{\"lc_id\":\"XSDTC\",\"lc_name\":\"新时代通成\",\"modified\":\"2014-12-17 15:10:36\"},{\"lc_id\":\"GSDWL\",\"lc_name\":\"共速达物流\",\"modified\":\"2014-12-17 15:10:47\"},{\"lc_id\":\"DTLY\",\"lc_name\":\"大田陆运\",\"modified\":\"2014-12-17 15:10:57\"},{\"lc_id\":\"EMS_SH_ZX_US\",\"lc_name\":\"DHL Global Mail\",\"modified\":\"2015-01-17 09:53:18\"}],\"code\":0,\"issuccess\":true,\"msg\":\"执行成功\"}\n");

        erpLogistics(jsonObject);
    }

    //lc_id("lc_id","lc_name"),
    public static String erpLogistics(JSONObject jsonObject){
        JSONArray datas = jsonObject.getJSONArray("datas");
        List<LinkedHashMap<String, Object>> linkedHashMaps = jsonChangeList(datas);
        linkedHashMaps.forEach(item->{
            System.out.println(item.get("lc_id")+"(\""+item.get("lc_id")+"\""+",\""+item.get("lc_name")+"\"),");
        });
        return null;
    }

    public static List<LinkedHashMap<String,Object>> jsonChangeList(JSONArray jsonArray){
        List<LinkedHashMap<String,Object>> mapList = new ArrayList<>();
        if (jsonArray.size()>0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject resultObject = jsonArray.getJSONObject(i);
                LinkedHashMap<String,Object> map = new LinkedHashMap<>();
                resultObject.forEach((k,v)->{
                        map.put(k.toString(),v);
                });
                mapList.add(map);
            }
        }
        return mapList;
    }

}
