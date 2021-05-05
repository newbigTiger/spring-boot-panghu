package com.example.demo.mothod.factory;

/**
 * @author 胖虎
 * @date 2021/3/15 下午 6:39
 */
public class WeChatFactory implements IPaymentFactory {
    @Override
    public IPayment create() {
        return new WeChatPayMent();
    }
}
