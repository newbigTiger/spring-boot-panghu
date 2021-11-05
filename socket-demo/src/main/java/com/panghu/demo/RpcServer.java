package com.panghu.demo;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {
    private int port;
    private Map<String, Object> registeredServices = new HashMap<>();

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    public RpcServer(int port) {
        this.port = port;
    }

    public <T> void registerService(Class<T> rpcInterface, Object impl) {
        registeredServices.put(rpcInterface.getCanonicalName(), impl);
    }

    public void start() throws IOException {
        try (ServerSocket socket = new ServerSocket(port)) {
            for (; ; ) {
                Socket clientConnection = socket.accept();
                executorService.submit(() -> {
                    try {
                        InputStream inputStream = clientConnection.getInputStream();
                        OutputStream outputStream = clientConnection.getOutputStream();
                        // 读请求
                        byte[] requestBodyLengthBuffer = new byte[8];
                        int received = inputStream.read(requestBodyLengthBuffer);
                        if (received == -1) {
                            throw new RuntimeException("get request body length failed");
                        }
                        int requestBodyLength = Integer.parseInt(new String(requestBodyLengthBuffer));
                        byte[] requestBody = new byte[requestBodyLength];
                        received = inputStream.read(requestBody);
                        if (received != requestBodyLength) {
                            throw new RuntimeException("bad request body, received: " + received + ", need: " + requestBodyLength);
                        }
                        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(requestBody));
                        RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
                        String service = rpcRequest.getService();
                        Object impl = registeredServices.get(service);
                        if (impl == null) {
                            throw new RuntimeException("no service found: " + service);
                        }
                        for (Method method : impl.getClass().getMethods()) {
                            if (method.getName().equals(rpcRequest.getMethod())) {
                                // 找到的rpc方法并调用
                                Object returnValue = method.invoke(impl, rpcRequest.getArgs());
                                RpcResponse rpcResponse = RpcResponse.builder()
                                        .responseValue(returnValue)
                                        .build();
                                // 写响应
                                ByteArrayOutputStream bodyBuffer = new ByteArrayOutputStream();
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bodyBuffer);
                                objectOutputStream.writeObject(rpcResponse);
                                byte[] body = bodyBuffer.toByteArray();
                                outputStream.write(String.format("%08d", body.length).getBytes());
                                outputStream.write(body);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        IOUtils.closeQuietly(clientConnection);
                    }
                });
            }
        }
    }
}
