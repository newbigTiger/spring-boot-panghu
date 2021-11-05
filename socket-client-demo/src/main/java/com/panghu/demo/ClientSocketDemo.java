package com.panghu.demo;

import java.io.*;
import java.net.Socket;

/**
 * @author 胖虎
 */
public class ClientSocketDemo {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8080);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("客户端发起的请求对象\n");
            bufferedWriter.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readLine = bufferedReader.readLine();
            System.out.println("接收到的响应："+readLine);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
