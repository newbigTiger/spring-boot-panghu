package com.example.demo.mothod.factory;

/**
 * @author 胖虎
 * @date 2021/3/15 下午 6:34
 */
public class WeChatPayMent implements IPayment {
    @Override
    public void payment() {
        System.out.println("微信支付");
    }
}
