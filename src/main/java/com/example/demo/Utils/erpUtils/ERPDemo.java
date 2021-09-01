package com.example.demo.Utils.erpUtils;

import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;

import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.Map;

public class ERPDemo {

    public static void main(String[] args) {

        JSONObject object = new JSONObject();
        object.put("page_index",1);
        object.put("page_size",30);
        JSONObject jsonObject = orderUtils(object.toString());
        System.out.println(jsonObject.toString());
    }

    /**
     * 根据入参决定调用方法,目前确认的可变参数仅仅只有method，仅需要传入method即可
     * @param jsonObject 接口的api需求，以及入参（Json格式还是String格式，好像都可以)
     * @return 返回调用结果
     */
    public static JSONObject orderUtils(String jsonObject){
        //接口名称
        String method = "logisticscompany.query";
        //授权码
        String token = "79c6081fee6a4dc0043f6b028e0e50db";
        //时间戳
        String ts = Long.toString(System.currentTimeMillis()/1000L);
        //合作方编号
        String partnerid = "8b84d6dfcf48859a5cadf011600cff04";
        //合作方签名拼接
        String partnerKey ="a3b463b005c859ce7544b0d6c885f534";
        //签名
        String sign = method+partnerid+"token"+token+"ts"+ts+partnerKey;
        sign = encrypt32(sign);

        LinkedHashMap<String,String > hashMap = new LinkedHashMap<>(5);
        hashMap.put("method",method);
        hashMap.put("partnerid",partnerid);
        hashMap.put("token",token);
        hashMap.put("ts",ts);
        hashMap.put("sign",sign);

        String requestUrl = "https://open.erp321.com/api/open/query.aspx";
        requestUrl = combinationString(hashMap,requestUrl);

        return JSONObject.fromObject(HttpClientUtils.httpsRequest(requestUrl,"POST",jsonObject));
    }

    /**
     * MD5 32位小写加密
     * @param encryptStr 加密内容
     * @return 需要加密的内容部分
     */
    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    /**
     *
     * @param hashMap 入参的有序键值对
     * @param requestUrl 链接地址
     * @return 凭借完毕的最终地址url
     */
    public static String combinationString(LinkedHashMap<String,String > hashMap,String requestUrl){
        if(hashMap.isEmpty()){ return requestUrl; }
        StringBuilder requestUrlBuilder = new StringBuilder(requestUrl);
        for (Map.Entry entry : hashMap.entrySet()) {
            if ("method".equals(entry.getKey())) {
                requestUrlBuilder.append("?").append(entry.getKey()).append("=").append(entry.getValue());
            }else{
                requestUrlBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        requestUrl = requestUrlBuilder.toString();
        return requestUrl;
    }

}
