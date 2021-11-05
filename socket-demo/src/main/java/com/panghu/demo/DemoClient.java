package com.panghu.demo;

public class DemoClient {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient("127.0.0.1:1234");
        DemoService demoService = rpcClient.createRpcImpl(DemoService.class);
        for (int i = 0; i < 100; i++) {
            System.out.println(demoService.getUser("test" + i));
        }
    }
}
