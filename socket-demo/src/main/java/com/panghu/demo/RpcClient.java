package com.panghu.demo;

import java.io.*;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class RpcClient {
    private String serverAddress;

    public RpcClient(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @SuppressWarnings("unchecked")
    public <T> T createRpcImpl(Class<T> rpcInterface) {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{rpcInterface},
                (proxy, method, args) -> {
                    String serviceName = rpcInterface.getCanonicalName();
                    String methodName = method.getName();
                    RpcRequest rpcRequest = RpcRequest.builder()
                            .service(serviceName)
                            .method(methodName)
                            .args(args)
                            .build();

                    String[] hostAndPort = serverAddress.split(":");
                    try (Socket socket = new Socket(hostAndPort[0], Integer.parseInt(hostAndPort[1]))) {
                        // 写请求
                        // 协议 8个字节十进制字符串表body长度 + 序列化之后的request
                        ByteArrayOutputStream bodyBuffer = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bodyBuffer);
                        objectOutputStream.writeObject(rpcRequest);
                        byte[] body = bodyBuffer.toByteArray();
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(String.format("%08d", body.length).getBytes());
                        outputStream.write(body);
                        // 读响应
                        InputStream inputStream = socket.getInputStream();
                        byte[] responseBodyLengthBuffer = new byte[8];
                        int received = inputStream.read(responseBodyLengthBuffer);
                        if (received == -1) {
                            throw new RuntimeException("get response body length failed");
                        }
                        int responseBodyLength = Integer.parseInt(new String(responseBodyLengthBuffer));
                        byte[] responseBody = new byte[responseBodyLength];
                        received = inputStream.read(responseBody);
                        if (received != responseBodyLength) {
                            throw new RuntimeException("bad response body, received: " + received + ", need: " + responseBodyLength);
                        }
                        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(responseBody));
                        RpcResponse rpcResponse = (RpcResponse) objectInputStream.readObject();
                        return rpcResponse.getResponseValue();
                    }
                });
    }
}
