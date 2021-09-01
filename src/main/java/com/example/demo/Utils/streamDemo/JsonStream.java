package com.example.demo.Utils.streamDemo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonStream {

    public static void main(String[] args) {
        Timestamp modifiedEnd = Timestamp.valueOf("2021-05-10 08:55:09");
        System.out.println(modifiedEnd.getTime());
        String s = "{\n" +
                "\t\"page_size\": 3,\n" +
                "\t\"page_index\": 1,\n" +
                "\t\"data_count\": 3,\n" +
                "\t\"page_count\": 1,\n" +
                "\t\"has_next\": false,\n" +
                "\t\"orders\": [{\n" +
                "\t\t\"o_id\": 2998601,\n" +
                "\t\t\"shop_id\": 10157270,\n" +
                "\t\t\"so_id\": \"BJ190725101060090\",\n" +
                "\t\t\"order_date\": \"2019-07-25 08:40:54\",\n" +
                "\t\t\"shop_status\": \"0\",\n" +
                "\t\t\"question_type\": null,\n" +
                "\t\t\"question_desc\": null,\n" +
                "\t\t\"status\": \"WaitPay\",\n" +
                "\t\t\"shop_buyer_id\": \"王薇11920768\",\n" +
                "\t\t\"receiver_state\": \"北京\",\n" +
                "\t\t\"receiver_city\": \"石景山区\",\n" +
                "\t\t\"receiver_district\": \"石景山区5环内\",\n" +
                "\t\t\"receiver_address\": \"5环内鲁谷大街重兴园小区10号楼2单元502.\",\n" +
                "                \"receiver_town\": \"骆岗街道\",\n" +
                "\t\t\"receiver_name\": \"王薇\",\n" +
                "\t\t\"receiver_mobile\": \"13811920768\",\n" +
                "\t\t\"send_date\": null,\n" +
                "\t\t\"pay_amount\": 39.80,\n" +
                "\t\t\"freight\": 0.00,\n" +
                "\t\t\"buyer_message\": \"赤猿测试\",\n" +
                "\t\t\"remark\": null,\n" +
                "\t\t\"is_cod\": false,\n" +
                "\t\t\"items\": [{\n" +
                "\t\t\t\"sku_id\": \"0000032545513\",\n" +
                "\t\t\t\"shop_sku_id\": null,\n" +
                "\t\t\t\"properties_value\": null,\n" +
                "\t\t\t\"amount\": 39.80,\n" +
                "\t\t\t\"base_price\": 39.8000,\n" +
                "\t\t\t\"qty\": 1,\n" +
                "\t\t\t\"name\": \"【专仓直发包邮】采琪采本色软抽鲸鱼版米点压花1*30包\",\n" +
                "\t\t\t\"price\": 39.8000,\n" +
                "\t\t\t\"outer_oi_id\": \"BJ190725101060090_0000032545513\",\n" +
                "\t\t\t\"is_gift\": false,\n" +
                "\t\t\t\"refund_status\": null,\n" +
                "\t\t\t\"refund_id\": null,\n" +
                "\t\t\t\"item_status\": null,\n" +
                "\t\t\t\"i_id\": \"667805\",\n" +
                "\t\t\t\"raw_so_id\": \"BJ190725101060090\",\n" +
                "\t\t\t\"is_presale\": null,\n" +
                "\t\t\t\"oi_id\": 5902120\n" +
                "\t\t}],\n" +
                "\t\t\"type\": \"普通订单\",\n" +
                "\t\t\"paid_amount\": 39.80,\n" +
                "\t\t\"pay_date\": null,\n" +
                "\t\t\"outer_pay_id\": null,\n" +
                "\t\t\"modified\": \"2019-07-25 10:26:25\",\n" +
                "\t\t\"order_from\": \"ZLWMW\",\n" +
                "\t\t\"pays\": [],\n" +
                "\t\t\"shop_name\": \"红酒供应\",\n" +
                "\t\t\"l_id\": null,\n" +
                "\t\t\"wms_co_id\": 0,\n" +
                "\t\t\"logistics_company\": null,\n" +
                "\t\t\"free_amount\": \"0\",\n" +
                "\t\t\"co_id\": 12252,\n" +
                "\t\t\"drp_co_id_from\": null,\n" +
                "\t\t\"labels\": null,\n" +
                "\t\t\"currency\": null\n" +
                "\t}, {\n" +
                "\t\t\"o_id\": 2998602,\n" +
                "\t\t\"shop_id\": 10157270,\n" +
                "\t\t\"so_id\": \"BJ190725101062167\",\n" +
                "\t\t\"order_date\": \"2019-07-25 09:50:19\",\n" +
                "\t\t\"shop_status\": \"0\",\n" +
                "\t\t\"question_type\": null,\n" +
                "\t\t\"question_desc\": null,\n" +
                "\t\t\"status\": \"WaitPay\",\n" +
                "\t\t\"shop_buyer_id\": \"杨忠嘉44498088\",\n" +
                "\t\t\"receiver_state\": \"辽宁省\",\n" +
                "\t\t\"receiver_city\": \"大连市\",\n" +
                "\t\t\"receiver_district\": \"金州区\",\n" +
                "\t\t\"receiver_address\": \"保税区蔚蓝国际2号楼602室.\",\n" +
                "\t\t\"receiver_name\": \"杨忠嘉\",\n" +
                "\t\t\"receiver_mobile\": \"13944498088\",\n" +
                "\t\t\"send_date\": null,\n" +
                "\t\t\"pay_amount\": 25.80,\n" +
                "\t\t\"freight\": 0.00,\n" +
                "\t\t\"buyer_message\": null,\n" +
                "\t\t\"remark\": null,\n" +
                "\t\t\"is_cod\": false,\n" +
                "\t\t\"items\": [{\n" +
                "\t\t\t\"sku_id\": \"6950849192593\",\n" +
                "\t\t\t\"shop_sku_id\": null,\n" +
                "\t\t\t\"properties_value\": null,\n" +
                "\t\t\t\"amount\": 25.80,\n" +
                "\t\t\t\"base_price\": 25.8000,\n" +
                "\t\t\t\"qty\": 1,\n" +
                "\t\t\t\"name\": \"【专仓直发包邮】采琪采本色软抽纸（黄色）1*18包\",\n" +
                "\t\t\t\"price\": 25.8000,\n" +
                "\t\t\t\"outer_oi_id\": \"BJ190725101062167_6950849192593\",\n" +
                "\t\t\t\"is_gift\": false,\n" +
                "\t\t\t\"refund_status\": null,\n" +
                "\t\t\t\"refund_id\": null,\n" +
                "\t\t\t\"item_status\": null,\n" +
                "\t\t\t\"i_id\": \"664811\",\n" +
                "\t\t\t\"raw_so_id\": \"BJ190725101062167\",\n" +
                "\t\t\t\"is_presale\": null,\n" +
                "\t\t\t\"oi_id\": 5902121\n" +
                "\t\t}],\n" +
                "\t\t\"type\": \"普通订单\",\n" +
                "\t\t\"paid_amount\": 25.80,\n" +
                "\t\t\"pay_date\": null,\n" +
                "\t\t\"outer_pay_id\": null,\n" +
                "\t\t\"modified\": \"2019-07-25 10:26:26\",\n" +
                "\t\t\"order_from\": \"ZLWMW\",\n" +
                "\t\t\"pays\": [],\n" +
                "\t\t\"shop_name\": \"红酒供应\",\n" +
                "\t\t\"l_id\": null,\n" +
                "\t\t\"wms_co_id\": 0,\n" +
                "\t\t\"logistics_company\": null,\n" +
                "\t\t\"free_amount\": \"0\",\n" +
                "\t\t\"co_id\": 12252,\n" +
                "\t\t\"drp_co_id_from\": null,\n" +
                "\t\t\"labels\": null,\n" +
                "\t\t\"currency\": null\n" +
                "\t}, {\n" +
                "\t\t\"o_id\": 2998603,\n" +
                "\t\t\"shop_id\": 10157270,\n" +
                "\t\t\"so_id\": \"BJ190725101062157\",\n" +
                "\t\t\"order_date\": \"2019-07-25 09:48:44\",\n" +
                "\t\t\"shop_status\": \"0\",\n" +
                "\t\t\"question_type\": null,\n" +
                "\t\t\"question_desc\": null,\n" +
                "\t\t\"status\": \"WaitPay\",\n" +
                "\t\t\"shop_buyer_id\": \"艾伦05289907\",\n" +
                "\t\t\"receiver_state\": \"江苏省\",\n" +
                "\t\t\"receiver_city\": \"无锡市\",\n" +
                "\t\t\"receiver_district\": \"江阴市\",\n" +
                "\t\t\"receiver_address\": \"周庄镇龙西路106号欧伦.\",\n" +
                "\t\t\"receiver_name\": \"艾伦\",\n" +
                "\t\t\"receiver_mobile\": \"13205289907\",\n" +
                "\t\t\"send_date\": null,\n" +
                "\t\t\"pay_amount\": 104.40,\n" +
                "\t\t\"freight\": 0.00,\n" +
                "\t\t\"buyer_message\": null,\n" +
                "\t\t\"remark\": null,\n" +
                "\t\t\"is_cod\": false,\n" +
                "\t\t\"items\": [{\n" +
                "\t\t\t\"sku_id\": \"6950849191930-1\",\n" +
                "\t\t\t\"shop_sku_id\": null,\n" +
                "\t\t\t\"properties_value\": null,\n" +
                "\t\t\t\"amount\": 104.40,\n" +
                "\t\t\t\"base_price\": 104.4000,\n" +
                "\t\t\t\"qty\": 3,\n" +
                "\t\t\t\"name\": \"【专仓直发包邮】采琪采卷纸竹浆纸纸巾卫生纸无芯42卷 妇婴适用 超值装\",\n" +
                "\t\t\t\"price\": 34.8000,\n" +
                "\t\t\t\"outer_oi_id\": \"BJ190725101062157_6950849191930-1\",\n" +
                "\t\t\t\"is_gift\": false,\n" +
                "\t\t\t\"refund_status\": null,\n" +
                "\t\t\t\"refund_id\": null,\n" +
                "\t\t\t\"item_status\": null,\n" +
                "\t\t\t\"i_id\": \"664795\",\n" +
                "\t\t\t\"raw_so_id\": \"BJ190725101062157\",\n" +
                "\t\t\t\"is_presale\": null,\n" +
                "\t\t\t\"oi_id\": 5902122\n" +
                "\t\t}],\n" +
                "\t\t\"type\": \"普通订单\",\n" +
                "\t\t\"paid_amount\": 104.40,\n" +
                "\t\t\"pay_date\": null,\n" +
                "\t\t\"outer_pay_id\": null,\n" +
                "\t\t\"modified\": \"2019-07-25 10:26:26\",\n" +
                "\t\t\"order_from\": \"ZLWMW\",\n" +
                "\t\t\"pays\": [],\n" +
                "\t\t\"shop_name\": \"红酒供应\",\n" +
                "\t\t\"l_id\": null,\n" +
                "\t\t\"wms_co_id\": 0,\n" +
                "\t\t\"logistics_company\": null,\n" +
                "\t\t\"free_amount\": \"0\",\n" +
                "\t\t\"co_id\": 12252,\n" +
                "\t\t\"drp_co_id_from\": null,\n" +
                "\t\t\"labels\": null,\n" +
                "\t\t\"currency\": null\n" +
                "\t}],\n" +
                "\t\"code\": 0,\n" +
                "\t\"issuccess\": true,\n" +
                "\t\"msg\": null\n" +
                "}";
        getOrderInfo(JSONObject.fromObject(s));
    }

    public static Boolean getOrderInfo(JSONObject request){
        System.out.println(request);
        /*
          获取到这个核心订单内容的jsonArray
          data_count 返回值有几个数可以用来初始化
         */
        String keys = "orders";
        //订单的详细内容 是一个list
        JSONArray jsonArray = request.getJSONArray(keys);
        /*
          参数巨多
         */
        List<Map<String, Object>> shopOrderAssociationMapList = jsonChangeList(jsonArray);

        //这个用来存放需要添加的元素
        List<Map<String,Object>> shopOrderAssociationItemList = shopOrderAssociationMapList.stream()
                .flatMap((Map<String, Object> map) ->{
                            List<Map<String, Object>> list = jsonChangeList(JSONArray.fromObject(map.get("pays")));
                            list.stream().forEach(item->item.put("so_id",map.get("so_id")));
                            return list.stream();
                }
                ).collect(Collectors.toList());
        System.out.println(shopOrderAssociationItemList.isEmpty());
        List<String>stringList = new ArrayList<>();
        stringList.add("BJ190725101062167");
        shopOrderAssociationMapList = shopOrderAssociationMapList.stream().filter(item -> {
            return !stringList.contains(item.get("so_id"));
        }).collect(Collectors.toList());

        return true;
    }

    /**
     * 将jsonArray转为一个ListMap
     * @param jsonArray 需要转化的jsonArray参数
     * @return 返回一个jsonArray转成的list
     */
    public static List<Map<String,Object>> jsonChangeList(JSONArray jsonArray){
        List<Map<String,Object>> mapList = new ArrayList<>();
        if (jsonArray.size()>0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject resultObject = jsonArray.getJSONObject(i);
                mapList.add(resultObject);
            }
        }
        return mapList;
    }
}
