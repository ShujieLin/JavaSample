package com.shujie.thread.tools;

import com.shujie.thread.utils.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 * 演示CountDownLatch用法，
 * 共5个初始化子线程，6个闭锁扣除点，扣除完毕后，主线程和业务线程才能继续执行
 * Created by linshujie on 2022/3/26.
 */
public class UseCountDownLatch {
    static CountDownLatch latch = new CountDownLatch(6);

    /**
     * 初始化线程
     */
    private static class InitRunnable implements Runnable {

        public void run() {
            System.out.println("Thread_" + Thread.currentThread().getId()
                    + " ready init work......");
            latch.countDown();
            for (int i = 0; i < 2; i++) {
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " ........continue do its work");
            }
        }
    }

    /**
     * 业务线程等待latch的计数器为0完成
     */
    private static class BusiRunnable implements Runnable {

        public void run() {
            try {
                //业务线程工作
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("BusiRunnable_" + Thread.currentThread().getId()
                        + " do business-----");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //start一个初始化线程
        new Thread(new Runnable() {
            public void run() {
                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " ready init work step 1st......");
                latch.countDown();
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " begin step 2nd.......");
                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " ready init work step 2nd......");
                latch.countDown();
            }
        }).start();
        //start一个业务线程
        new Thread(new BusiRunnable()).start();
        //start 4个初始化线程
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new InitRunnable());
            thread.start();
        }
        //主线程工作
        latch.await();
        System.out.println("Main do ites work........");
    }
}
