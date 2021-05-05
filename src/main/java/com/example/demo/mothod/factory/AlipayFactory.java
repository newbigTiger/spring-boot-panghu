package com.example.demo.mothod.factory;

/**
 * @author 胖虎
 * @date 2021/3/15 下午 6:38
 */
public class AlipayFactory implements IPaymentFactory {
    @Override
    public IPayment create() {
        return new AlipayPayment();
    }
}
