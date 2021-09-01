package com.example.demo.thread;

public class PangHuDemo {

    int i=0;
    //互斥锁排他锁
    private synchronized void incr(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        PangHuDemo pangHuDemo = new PangHuDemo();
        Thread [] threads = new Thread[2];
        for(int i=0;i<2;i++){
            threads[i] = new Thread(()->{
                for (int k=0;k<1000;k++) {
                    pangHuDemo.incr();
                }
            });
            threads[i].start();
        }
        threads[1].join();
        threads[0].join();
        System.out.println(pangHuDemo.i);
    }
}
