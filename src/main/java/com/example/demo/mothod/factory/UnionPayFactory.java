package com.example.demo.mothod.factory;

/**
 * @author 胖虎
 * @date 2021/3/15 下午 6:40
 */
public class UnionPayFactory implements IPaymentFactory {
    @Override
    public IPayment create() {
        return new UnionPayPayMent();
    }
}
