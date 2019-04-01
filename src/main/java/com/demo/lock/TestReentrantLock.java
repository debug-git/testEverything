package com.demo.lock;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    //保证原子操作的两个类 LongAdder AtomicInteger ,前者效率较高
    private static int count = 0;
//    private static AtomicInteger count2 = new AtomicInteger(0);
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ThreadPoolExecutor executor = null;
        try {
            System.out.println("程序开始");
            long time = System.currentTimeMillis();
            executor = new ThreadPoolExecutor(50, Integer.MAX_VALUE, 30L, TimeUnit.SECONDS, new LinkedTransferQueue<>());
            int countNum = 100;   //线程数量
//            executor.execute(daemonThread);

            //开启N个线程
            for (int i = 0; i < countNum; i++) {
                //经检验,使用lamda表达式不如传入Thread对象效率高
                //使用线程池会复用已有线程,效率较高
                executor.execute(
                    TestReentrantLock :: run
//                    run();
//                    System.out.println("线程" + Thread.currentThread().getId() + "开始");

//                    System.out.println("线程" + Thread.currentThread().getId() + "结束");
//                System.out.println("线程[" + Thread.currentThread().getId() + "]count=" + count);
                );
            }

//            int sum = 0;
            //此处设置自旋5次,若超过5次仍未等到线程执行完则放弃
            //executor.getCompletedTaskCount()返回已完成执行的近似任务总数

            while (executor.getCompletedTaskCount() < countNum) {
//                System.out.println("临时结果:" + count);
                Thread.sleep(10);
            }
            System.out.println("最终结果:count=" + count);
//            System.out.println("最终结果:count2=" + count2);
            System.out.println("耗时:" + (System.currentTimeMillis() - time) + "毫秒");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            if(executor != null){
                executor.shutdown();
            }
        }
        System.exit(0);
    }


        public static void run() {
            lock.lock();
            try{
                for (int j = 0; j < 1000 ; j++) {
                    count++;
//                    count2.getAndAdd(1);
                }
            }finally {
                lock.unlock();
            }

        }


}
