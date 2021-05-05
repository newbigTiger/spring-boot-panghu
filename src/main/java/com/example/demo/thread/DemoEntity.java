package com.example.demo.thread;

/**
 * @author 胖虎
 * @date 2021/3/15 下午 10:35
 */
public class DemoEntity implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().getName();

    }
}
