package com.panghu.demo;

import java.io.IOException;

public class DemoServer {
    public static void main(String[] args) throws IOException {
        RpcServer rpcServer = new RpcServer(1234);
        rpcServer.registerService(DemoService.class, new DemoService() {
            @Override
            public User getUser(String name) {
                System.out.println("do method get user:" + name);
                return User.builder().name(name).build();
            }
        });
        rpcServer.start();
    }
}
