package com.shujie.thread.base;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程指的是程序在后台提供的一种通用服务线程，这种线程不属于程序中不可或缺的部分，当所有非后台线程结束时候，程序就结束了。
 *
 * @author linshujie
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            } catch (InterruptedException e) {
                System.out.println("e = " + e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);//必须在线程执行之前调用setDaemon方法，才能把它设置为后台线程
            daemon.start();
        }

        System.out.println("all daemons start");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
