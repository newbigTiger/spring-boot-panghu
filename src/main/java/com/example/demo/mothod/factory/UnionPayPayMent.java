package com.example.demo.mothod.factory;

/**
 * @author 胖虎
 * @date 2021/3/15 下午 6:35
 */
public class UnionPayPayMent implements IPayment {
    @Override
    public void payment() {
        System.out.println("银联支付");
    }
}
