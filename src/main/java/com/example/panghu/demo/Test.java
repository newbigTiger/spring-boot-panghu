package com.example.panghu.demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory(String namePrefix) {
            SecurityManager s = System.getSecurityManager();
            this.group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    static class WaitPolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 10, 10, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1), new DefaultThreadFactory("consumer-"), new WaitPolicy());

        ThreadPoolExecutor threadPoolExecutorProducer = new ThreadPoolExecutor(4, 10, 10, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1), new DefaultThreadFactory("producer-"), new WaitPolicy());

        long count = 0;
        for (; ; ) {
            long now = count++;
            threadPoolExecutorProducer.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " produce " + now);
                threadPoolExecutor.submit(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName() + " consume  " + now);
                });
            });
        }
    }
}
