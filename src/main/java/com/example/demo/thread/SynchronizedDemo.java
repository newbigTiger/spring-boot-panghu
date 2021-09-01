package com.example.demo.thread;

public class SynchronizedDemo {

    /*
    第一种修饰实例方法
    第二种修饰静态方法
    第三种修饰代码块
     */
    public synchronized void m1() {

    }
    Object lock = new Object();
    public void m2() {
        synchronized (lock) {

        }
    }

    /*
    抢占锁的标志：
    1.共享资源
    2.标记 0无锁，1有锁
    lock中存储了锁相关的信息
    MarkWord对象头
    lock对象中有
    对象标记 四个字节  markoop中有同步锁标记偏向锁标记
    类元信息
    实例数据
    对齐填充
     */
    public static void main(String[] args) {

        SynchronizedDemo sm1 = new SynchronizedDemo();
        SynchronizedDemo sm2 = new SynchronizedDemo();
        new Thread(() -> sm1.m1()).start();
        new Thread(() -> sm1.m2()).start();
        System.out.println();
    }
}