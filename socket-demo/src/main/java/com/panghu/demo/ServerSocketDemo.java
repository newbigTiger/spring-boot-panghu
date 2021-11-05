package com.panghu.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 胖虎
 */
public class ServerSocketDemo {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket accept = serverSocket.accept();
            System.out.println(accept.getPort());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            String readLine = bufferedReader.readLine();
            System.out.println("服务器接收到了消息："+readLine);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
            bufferedWriter.write("服务器的响应\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
