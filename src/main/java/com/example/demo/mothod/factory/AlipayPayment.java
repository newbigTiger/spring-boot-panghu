package com.example.demo.mothod.factory;

/**
 * @author 胖虎
 * @date 2021/3/15 下午 6:35
 */
public class AlipayPayment implements IPayment {
    @Override
    public void payment() {
        System.out.println("支付宝支付");
    }
}
