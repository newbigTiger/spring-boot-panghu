package com.example.demo.thread;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 胖虎
 * @date 2021/3/15 下午 10:29
 */
@Component
public class ExeuctorsDemo {

    @Value("${jtw.corePoolSize}")
    private int corePoolSize ;

    /**
     * 最大池大小
     */
    @Value("${jtw.maximumPoolSize}")
    private int maximumPoolSize;
    /**
     * 维持时间
     */
    @Value("${jtw.keepAliveTime}")
    private int keepAliveTime;
    /**
     * 时间单位
     */
    private static TimeUnit unit = TimeUnit.MILLISECONDS;
    /**
     * 线程等待队列
     */
    private static LinkedBlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>();

    public  void getThreadPool(String[] args) throws InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, unit, runnables);
        for(int i=0;i<20;i++){
            poolExecutor.execute(new DemoEntity());
        }
        Thread.sleep(2000);
        for(int i = 0;i<20;i++){
            poolExecutor.execute(new DemoEntity());
        }
    }
}
