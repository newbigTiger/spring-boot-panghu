package com.example.demo.mothod.factory;

/**
 * @author 胖虎
 * @date 2021/3/15 下午 6:41
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        IPaymentFactory iPaymentFactory = new WeChatFactory();
        IPayment iPayment = iPaymentFactory.create();
        iPayment.payment();
    }
}
