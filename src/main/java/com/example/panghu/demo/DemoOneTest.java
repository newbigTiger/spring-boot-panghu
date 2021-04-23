package com.example.panghu.demo;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class DemoOneTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
