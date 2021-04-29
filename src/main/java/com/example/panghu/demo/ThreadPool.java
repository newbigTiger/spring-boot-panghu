//package com.example.panghu.demo;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//import java.security.MessageDigest;
//import java.util.*;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.stream.Collectors;
//
//public class ThreadPool {
//    package com.leimingtech.admin.module.external.controller;
//
//import com.leimingtech.admin.utils.HttpClientUtils;
//import com.leimingtech.core.utils.map.MapUtils;
//import com.leimingtech.service.module.trade.service.ShopAssociationService;
//import lombok.extern.slf4j.Slf4j;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import org.apache.commons.collections.ListUtils;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//import java.util.concurrent.*;
//import java.security.MessageDigest;
//import java.util.*;
//import java.util.stream.Collectors;
//
//    @Component
//    @RequestMapping("/erp/api")
//    @Slf4j
//    public class ERPController{
//        @Resource
//        ShopAssociationService shopAssociationService;
//
//        /**
//         * 物流信息同步方法
//         * 到底用哪一个还需要考虑
//         * @return 返回是否成功
//         */
//        @RequestMapping("/createOrder")
//        public static String createOrer(){
//            //查询到的物流信息
//            List<Map<String,String>> logisticsListMap = new ArrayList<>();
//
//            //存放该接口的配置信息
//            HashMap<String, String> hashMap = new HashMap<>(8);
//
//            hashMap.put("method","orders.wms.sent.upload");
//            hashMap.put("token","181ee8952a88f5a57db52587472c3798");
//            hashMap.put("partnerid","ywv5jGT8ge6Pvlq3FZSPol345asd");
//            hashMap.put("partnerKey","ywv5jGT8ge6Pvlq3FZSPol2323");
//            hashMap.put("requestUrl","https://c.jushuitan.com/api/open/query.aspx");
//            String string = JSONArray.fromObject(logisticsListMap).toString();
//            return orderUtils(hashMap,string);
//        }
//
//        /**
//         * 根据入参决定调用方法
//         * @param jsonObject 物流信息Map集合
//         * @return 返回调用结果
//         */
//        public static String orderUtils(Map<String,String> paramMap,String jsonObject){
//
//            //接口名称
//            String method = MapUtils.getString(paramMap,"method");
//            //授权码
//            String token = MapUtils.getString(paramMap,"token");
//            //时间戳
//            String ts = Long.toString(System.currentTimeMillis()/1000L);
//            //合作方编号
//            String partnerid = MapUtils.getString(paramMap,"partnerid");
//            //合作方签名拼接
//            String partnerKey = MapUtils.getString(paramMap,"partnerKey");
//            //签名
//            String sign = method+partnerid+"token"+token+"ts"+ts+partnerKey;
//            sign = encrypt32(sign);
//
//            LinkedHashMap<String,String > hashMap = new LinkedHashMap<>(5);
//            hashMap.put("method",method);
//            hashMap.put("partnerid",partnerid);
//            hashMap.put("token",token);
//            hashMap.put("ts",ts);
//            hashMap.put("sign",sign);
//
//            String requestUrl = MapUtils.getString(paramMap,"method");
//            requestUrl = combinationString(hashMap,requestUrl);
//
//            String request= HttpClientUtils.httpsRequest(requestUrl,"POST",jsonObject);
//            System.out.println(request);
//            return request;
//        }
//
//        /**
//         * todo 这里稍后需要优化，线程池最好明确每一个参数
//         */
//        private ExecutorService getOrderInfoExecutor = Executors.newFixedThreadPool(10);
//
//        /**
//         * 同步商城订单，同步那些的商城订单
//         * 通过电商贷同步的信息来查询这些商户的店铺
//         */
//        @RequestMapping("/getOrderInfoTask")
//        @Scheduled(fixedDelay = 1000)
//        public void getOrderInfoTask() {
//            HashMap<String,Object> paramMap = new HashMap<>();
//            //查询全部的需要去调用的店铺的编码以及他们需要同步信息的时间
//            List<Map<String,Object>> storeOtherIds = shopAssociationService.selectAllStore(paramMap);
//            if(storeOtherIds.isEmpty()){}else {
//                final int size = storeOtherIds.size();
//                System.out.println("---------------------跑起来了");
//                List<Future<String>> futureList = storeOtherIds.stream()
//                        .map(shopMap -> getOrderInfoExecutor.submit(() ->
//                                getOrderInfo(
//                                        MapUtils.getString(shopMap, "storeId"),
//                                        MapUtils.getString(shopMap, "storeOtherId"),
//                                        MapUtils.getString(shopMap, "modifiedBegin"),
//                                        MapUtils.getString(shopMap, "modifiedEnd"))))
//                        .collect(Collectors.toList());
//
//                for (Future<String> future : futureList) {
//                    try {
//                        future.get();
//                    } catch (InterruptedException e) {
//
//
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//
//        /**
//         * 用来同步商铺的订单
//         * @param storeOtherId 需要查询的第三方的商铺Id
//         * @return 返回参数
//         */
//        public static String getOrderInfo(String storeId,String storeOtherId,String startTime,String endTime){
//
//            //查询到的商铺编号
//
//            //存放该接口的配置信息
//            HashMap<String, String> hashMap = new HashMap<>(8);
//
//            hashMap.put("method","orders.single.query");
//            hashMap.put("token","181ee8952a88f5a57db52587472c3798");
//            hashMap.put("partnerid","ywv5jGT8ge6Pvlq3FZSPol345asd");
//            hashMap.put("partnerKey","ywv5jGT8ge6Pvlq3FZSPol2323");
//            hashMap.put("requestUrl","https://c.jushuitan.com/api/open/query.aspx");
//            //todo      需要存放到 shopOrder 订单详情表必须有订单的 购物车表，保存订单支付表，，，，，保存订单的相关信息
//            // 买方账号	下单时间	平台名称	商品名称	金额 供应商全称	下单时间	金额	收货人	收货地址	联系电话	物流公司	物流单号
//            String jsonObject = getJsonObject(storeOtherId, startTime,endTime);
//            System.out.println("---------需要查询的参数有------"+jsonObject);
//            //拿到返回的订单信息，需要同步到咋们的数据库表中
//            String s = orderUtils(hashMap, jsonObject);
//
//            return orderUtils(hashMap,jsonObject);
//        }
//
//        /**
//         * MD5 32位小写加密
//         * @param encryptStr
//         * @return
//         */
//        public static String encrypt32(String encryptStr) {
//            MessageDigest md5;
//            try {
//                md5 = MessageDigest.getInstance("MD5");
//                byte[] md5Bytes = md5.digest(encryptStr.getBytes());
//                StringBuffer hexValue = new StringBuffer();
//                for (int i = 0; i < md5Bytes.length; i++) {
//                    int val = ((int) md5Bytes[i]) & 0xff;
//                    if (val < 16) {
//                        hexValue.append("0");
//                    }
//                    hexValue.append(Integer.toHexString(val));
//                }
//                encryptStr = hexValue.toString();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            return encryptStr;
//        }
//
//        /**
//         *
//         * @param hashMap 入参的有序键值对
//         * @param requestUrl 链接地址
//         * @return
//         */
//        public static String combinationString(LinkedHashMap<String,String > hashMap,String requestUrl){
//            if(hashMap.isEmpty()){ return requestUrl; }
//            for (Map.Entry entry : hashMap.entrySet()) {
//                if ("method".equals(entry.getKey())) {
//                    requestUrl +="?"+entry.getKey()+"="+entry.getValue();
//                }else{
//                    requestUrl +="&"+entry.getKey()+"="+entry.getValue();
//                }
//            }
//            return requestUrl;
//        }
//
//        /**
//         * 凭借Json串用来同步订单信息
//         * @param storeOtherId 需要同步的店铺Id
//         * @param startTime 需要同步的店铺的时间
//         * @param endTime 需要同步到额订单的结束时间
//         * @return 凭借完成的json串
//         */
//        public static String getJsonObject(String storeOtherId,String startTime,String endTime){
//            JSONObject jsonObject = new JSONObject();
//            //店铺编号
//            jsonObject.put("shop_id",storeOtherId);
//            //是否查询线下店铺单据
//            jsonObject.put("is_offline_shop",false);
//            //线上单号非必填 与时间不能同时位空。
//            jsonObject.put("so_ids",null);
//            //起始时间 yyyy-MM-dd HH:mm:ss
//            jsonObject.put("modified_begin",startTime);
//            //终止时间
//            jsonObject.put("modified_end",endTime);
//            /**
//             * 待付款：WaitPay；发货中：Delivering；被合并：Merged；异常：Question；被拆分：Split；
//             * 等供销商|外仓发货：WaitOuterSent；已付款待审核：WaitConfirm；已客审待财审：WaitFConfirm；已发货：Sent；取消：Cancelled
//             */
//            jsonObject.put("status","Sent");
//            jsonObject.put("page_index",1);
//            jsonObject.put("page_size",50);
//            return jsonObject.toString();
//        }
//    }
//
//}
