package com.example.panghu.common.demoTest;

import com.example.panghu.common.controller.HttpClientUtils;
import org.json.JSONException;
import org.json.JSONObject;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpsTest {

    public static void main(String[] args){
        //接口名称
        String method = "orders.wms.sent.upload";
        //授权码
        String token = "181ee8952a88f5a57db52587472c3798";
        //时间戳
        String ts = Long.toString(System.currentTimeMillis()/1000L);
        //合作方编号
        String partnerid = "ywv5jGT8ge6Pvlq3FZSPol345asd";
        //合作方签名拼接
        String partnerKey = "ywv5jGT8ge6Pvlq3FZSPol2323";
        //签名
        String sign = method+partnerid+"token"+token+"ts"+ts+partnerKey;
        sign = encrypt32(sign);

        LinkedHashMap<String,String > hashMap = new LinkedHashMap<>(5);
        hashMap.put("method",method);
        hashMap.put("partnerid",partnerid);
        hashMap.put("token",token);
        hashMap.put("ts",ts);
        hashMap.put("sign",sign);

        String requestUrl = "https://c.jushuitan.com/api/open/query.aspx";
        requestUrl = combinationString(hashMap,requestUrl);
        JSONObject rspObj = new JSONObject();
        try {
            rspObj.put("io_id", "213232");
            rspObj.put("lc_name", "顺丰快递");
            rspObj.put("l_id", "SF34343");
            rspObj.put("lc_id", "SFKD");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//...
        String reqStr = rspObj.toString();
        String s= HttpClientUtils
                .httpsRequest(requestUrl,"GET",reqStr);
        System.out.println(s);
    }
    /**
     * MD5 32位小写加密
     * @param encryptStr
     * @return
     */
    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
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
     * @return
     */
    public static String combinationString(LinkedHashMap<String,String > hashMap,String requestUrl){
        if(hashMap.isEmpty()){ return requestUrl; }
        for (Map.Entry entry : hashMap.entrySet()) {
            if ("method".equals(entry.getKey())) {
                requestUrl +="?"+entry.getKey()+"="+entry.getValue();
            }else{
                requestUrl +="&"+entry.getKey()+"="+entry.getValue();
            }
        }
        return requestUrl;
    }
}
